package com.example.movice_ticket.controller;


import com.example.movice_ticket.dao.FilmReviewDAO;
import com.example.movice_ticket.dao.ReplyDAO;
import com.example.movice_ticket.dao.UserDAO;
import com.example.movice_ticket.model.FilmReview;
import com.example.movice_ticket.model.Reply;
import com.example.movice_ticket.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 影评管理
* */
@Controller
public class FilmReviewController {

	@Autowired
	private FilmReviewDAO filmReviewDao;
	
	@Autowired
	private UserDAO userDao;
	@Autowired
	private ReplyDAO replyDAO;

	//创建影评
	@RequestMapping("/user/createFilmReview")
	@ResponseBody
	public List<FilmReview> CreateFilmReview(FilmReview filmReview ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = userDao.login(userName);
		int userId = user.getUserId();
		filmReview.setUserId(userId);

		boolean b = filmReviewDao.createFilmReview(filmReview);
		Map<String,Object> paramMap = new HashMap<String,Object>() ;
		paramMap.put("filmReview", filmReview) ;
		List<FilmReview> filmReviews = filmReviewDao.queryList(paramMap);

		return filmReviews;
	}
	//创建回复
	@RequestMapping("/user/createReply")
	@ResponseBody
	public Reply createReply(Reply reply) {
		Map<String,Object> paramMap = new HashMap<String,Object>() ;
		boolean b = replyDAO.createReplay(reply);
//		List<Reply> replys =new ArrayList<>();
		 if (b){
//			 replys = replyDAO.moreReply(reply);
			 paramMap.put("num",1);
			 paramMap.put("filmReviewId",reply.getFilmReviewId());
			 //记录每条影评的回复数量
			 boolean s = filmReviewDao.addReplyNum(paramMap);
		 }
		return reply;
	}

	//修改点赞
	@RequestMapping("/user/updateLikeNum")
	@ResponseBody
	public FilmReview updateLikeNum(FilmReview filmReview) {
		Map<String, Object> map = new HashMap<>();
		map.put("num",filmReview.getLikeNumber());
		map.put("filmReviewId", filmReview.getFilmReviewId());
		boolean b = filmReviewDao.addlikeNum(map);

		if (b){
			filmReview = filmReviewDao.byfilmReviewId(filmReview.getFilmReviewId());
		}
		return filmReview;
	}

	//分页查询所有影评
	@RequestMapping("/user/selectFilmReview")
	public String selectFilmReview(@RequestParam(name="page",required=false,defaultValue="1")int page,Model model) {
		Map<String, Object> ParamMap = new HashMap<String, Object>();
		List<FilmReview> filmReviews = filmReviewDao.queryList(ParamMap);
		int size = 3 ;
		if (page<=0){
			page = 1;
		}
		int begin = (page-1)*size ;
		
		ParamMap.put("begin", begin) ;
		ParamMap.put("size", size) ;
		 
		model.addAttribute("filmReviews",filmReviews);
		
		int pages = 0;
		int recordCount = filmReviewDao.querycountList(ParamMap);
		if ( recordCount % size == 0 ) {
			pages = recordCount / size  ;
			if(pages==0) {
				pages=1;
			}
		} else {
			pages = recordCount / size + 1 ;
		}

		model.addAttribute("pages",pages) ;
		model.addAttribute("page",page) ;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 if(auth.getName().equals("admin")) {
			 return "admin/filmReview/selectFilmReview";
		 }else {
			 return "error";
		 }
	}
	//删除影评
	@RequestMapping("/user/deleteFilmReview")
	public String deleteFilmReview(int filmReviewId) {
		boolean b = filmReviewDao.delete(filmReviewId);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		if(userName.equals("admin")) {
			 return "redirect:/user/selectFilmReview";
		 }else {

			 return "redirect:/user/userInfo";
		 }
		
	}
}
