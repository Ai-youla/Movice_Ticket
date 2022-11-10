package com.example.movice_ticket.controller;


import com.example.movice_ticket.dao.*;
import com.example.movice_ticket.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ViewingRelationController {

	@Autowired
	private ViewingRelationDAO viewingRelationDao;
	
	@Autowired
	private CinemaDAO cinemaDao;
	
	@Autowired
	private FilmDAO filmDao;
	
	@Autowired
	private ViewingTimeDAO viewingTimeDao;
	@Autowired
	private SeatRelationDAO seatRelationDao;
	@Autowired
	private FilmAndCinemaDAO filmAndCinemaDAO;

	//分页查询所有观影时间
	@RequestMapping("/admin/toCreateViewingRelation")
	public String toCreateViewingRelation(@RequestParam(name="page",required = false,defaultValue = "1")int page ,Model model) {
		Map<String, Object>param = new HashMap<String, Object>();
		int size = 10;
		if (page<=0){
			page = 1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);
		
		List<Cinema> cinemas = cinemaDao.cinemaList(param);
		model.addAttribute("cinemas", cinemas);
		
		List<ViewingTime> viewingTimes = viewingTimeDao.ViewingTimes(param);
		model.addAttribute("viewingTimes", viewingTimes);
		
		int pages = 0; 
		int recordCount = viewingTimeDao.CountViewingTimes(param);
		if(recordCount % size==0) {
			pages = recordCount / size;
			if(pages==0) {
				pages =1;
			}
		}else {
			pages = recordCount / size +1;
		}

		model.addAttribute("pages",pages);
		model.addAttribute("page", page);
		return "admin/viewingTime/createViewingRelation";
	}
	//查询电影院绑定的电影
	@RequestMapping(value="/admin/viewingRelation/queryfilms",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<FilmAndCinema> queryfilms( int cinemaId) {
		//分页查询全部电影
		List<FilmAndCinema> filmAndCinemas = filmAndCinemaDAO.byCinemaId(cinemaId);

		return  filmAndCinemas;
	}
	//查询电影院绑定的影厅
	@RequestMapping(value="/admin/viewingRelation/querySeats",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<SeatRelation> querySeats( int cinemaId) {

		List<SeatRelation> seatRelations = seatRelationDao.getSeat(cinemaId);

		return  seatRelations;
	}
	//影院批量绑定批次
	@RequestMapping("/admin/createViewingRelation")
	public String createViewingRelation(int cinemaId,Model model,HttpServletRequest request,int filmId) {
		String viewingTimeStr[] = request.getParameterValues("viewingTimeId");
		ViewingRelation[] viewingRelations= null;
		 
		if(viewingTimeStr!=null) {
			viewingRelations = new ViewingRelation[viewingTimeStr.length];
			for(int i = 0 ; i< viewingTimeStr.length ; i++) {
				int viewingTimeId = Integer.parseInt(viewingTimeStr[i]) ;
				
				ViewingRelation viewingRelation = new ViewingRelation(viewingTimeId,cinemaId,filmId);
				viewingRelations[i]=viewingRelation;
			}
		}
		boolean b = viewingRelationDao.createViewingRelation(viewingRelations);
		if(b) {	
			return "redirect:/admin/toUpdateViewingRelation?cinemaId="+cinemaId+"&filmId="+filmId;
		}else {
			return "error";
		}	
	}
	//分页查询已绑定的批次
	@RequestMapping("/admin/toUpdateViewingRelation")
	public String toUpdateViewingRelation(@RequestParam(name="cinemaId",required=false,defaultValue="1")int cinemaId,
			@RequestParam(name="filmId",required=false,defaultValue="1")int filmId,
										  @RequestParam(name="seatId",required=false,defaultValue="1")int seatId,
			@RequestParam(name="page",required = false,defaultValue = "1")int page ,Model model) {
		Map<String, Object>param = new HashMap<String, Object>();
		int size = 10;
		if (page<=0){
			page = 1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);
		param.put("cinemaId", cinemaId);
		param.put("filmId", filmId);
		List<Cinema> cinemas = cinemaDao.cinemaList(param);
		model.addAttribute("cinemas", cinemas);
		model.addAttribute("cinemaId", cinemaId);
		model.addAttribute("filmId", filmId);

		//分页查询全部电影
		List<FilmAndCinema> filmAndCinemas = filmAndCinemaDAO.byCinemaId(cinemaId);

		model.addAttribute("filmAndCinemas", filmAndCinemas);
		//查出绑定的观影时间
		List<ViewingRelation> viewingRelations = viewingRelationDao.getViewingTime(param);
		model.addAttribute("viewingRelations", viewingRelations);

		List<SeatRelation> seatRelations = seatRelationDao.getSeat(cinemaId);
		model.addAttribute("seatRelations", seatRelations);
		model.addAttribute("seatId",seatId);

		int pages = 0; 
		int recordCount = viewingTimeDao.CountViewingTimes(param);
		if(recordCount % size==0) {
			pages = recordCount / size;
			if(pages==0) {
				pages =1;
			}
		}else {
			pages = recordCount / size +1;
		}

		model.addAttribute("pages",pages);
		model.addAttribute("page", page);
		return "admin/viewingTime/updateViewingRelation";
	}
	//重新批量绑定批次
	@RequestMapping("/admin/updateViewingRelation")
	public String updateViewingRelation(int cinemaId,int filmId,int seatId,Model model,HttpServletRequest request) {
		String viewingRelationStr[] = request.getParameterValues("viewingRelationId");
		ViewingRelation[] viewingRelations= null;

		if(viewingRelationStr!=null) {
			viewingRelations = new ViewingRelation[viewingRelationStr.length];
			for(int i = 0 ; i< viewingRelationStr.length ; i++) {
				int viewingRelationId = Integer.parseInt(viewingRelationStr[i]) ;			
				ViewingRelation viewingRelation = new ViewingRelation();
				viewingRelation.setViewingRelationId(viewingRelationId);
				viewingRelations[i]=viewingRelation;
			}
		}

		boolean b = viewingRelationDao.assignViewingTime(viewingRelations);
		if(b) {
			return "redirect:/admin/toUpdateViewingRelation?cinemaId="+cinemaId+"&filmId="+filmId+"&seatId="+seatId;
		}else {
			return "error";
		}
	}
	//场次绑定影院
	@RequestMapping("/admin/viewingRelation/updateSeatId")
	@ResponseBody
	public String updateSeatId(int cinemaId,int filmId,int seatId,@RequestParam(value = "viewingRelationStr[]") String[] viewingRelationStr,Model model,HttpServletRequest request) {
//		String viewingRelationStr[] = request.getParameterValues("viewingRelationId");
		ViewingRelation[] viewingRelations= null;

		if(viewingRelationStr!=null) {
			viewingRelations = new ViewingRelation[viewingRelationStr.length];
			for(int i = 0 ; i< viewingRelationStr.length ; i++) {
				int viewingRelationId = Integer.parseInt(viewingRelationStr[i]) ;
				ViewingRelation viewingRelation = new ViewingRelation();
				viewingRelation.setViewingRelationId(viewingRelationId);
				viewingRelation.setSeatId(seatId);
				viewingRelation.setCinemaId(cinemaId);
				viewingRelation.setFilmId(filmId);
				viewingRelations[i]=viewingRelation;
			}
		}

		boolean b = viewingRelationDao.updateseatId(viewingRelations);
		if(b) {
			return "success";
		}else {
			return "false";
		}
	}
}
