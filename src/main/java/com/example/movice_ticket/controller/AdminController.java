package com.example.movice_ticket.controller;


import com.example.movice_ticket.dao.*;
import com.example.movice_ticket.model.*;
import com.example.movice_ticket.util.GeneratePasswordEncoder;
//import com.example.movice_ticket.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
* 管理员及用户管理
* */
@Controller
public class AdminController {

	@Autowired
	private CinemaDAO cinemaDao;

	@Autowired
	private FilmDAO filmDao;

	@Autowired
	private FilmReviewDAO filmReviewDao;

	@Autowired
	private VipMemberDAO vipmemberDao;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private MovieNewsDAO movieNewsDAO;

	@Autowired
	private TicketDao ticketDao;
	@Autowired
	private PositionRelationDAO positionRelationDAO;
	@Autowired
	private ProduceDAO produceDAO;

	// 判断是管理员界面还是用户界面
	@RequestMapping("/role")
	public String toRole(@AuthenticationPrincipal Principal principal,Model model,
						 @RequestParam(name="page",required=false,defaultValue="1")int page) {
		//获取Security权限
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if ( auth.getName().equals("admin")) {

			return "admin/admin";
		} else {
			Map<String,Object>map = new HashMap<>();
			map.put("state","热播");
			int size = 3 ;//显示电影的数量
			if (page<=0){
				page = 1;
			}
			int begin = (page-1)*size ;
			map.put("begin", begin) ;
			map.put("size", size) ;
			List<Film> films = filmDao.queryFilm(map);//分页查询全部电影
			int count = filmDao.queryCountFilm(map);//总电影数
			model.addAttribute("films",films);
			model.addAttribute("count",count);
			int pages = 0 ;//电影总页数
			if ( count % size == 0 ) {
				pages = count / size  ;
				if(pages==0) {
					pages=1;
				}
			} else {
				pages = count / size + 1 ;
			}
			model.addAttribute("pages", pages);
			List<MovieNews> movieNews = movieNewsDAO.list(map);
			//总资讯数
			int reCounte = movieNewsDAO.listcount(map);
			model.addAttribute("reCounte", reCounte);
			int pages3 = 0 ;
			if ( reCounte % size == 0 ) {
				pages3 = reCounte / size  ;
				if(pages3==0) {
					pages3=1;
				}
			} else {
				pages3 = reCounte / size + 1 ;
			}
			model.addAttribute("pages3", pages3);
			model.addAttribute("page", page);
			model.addAttribute("movieNews", movieNews);
			List<Produce> produces = produceDAO.produceList(map);
			int produceCount = produceDAO.produceConutList(map);
			model.addAttribute("produceCount", produceCount);
			int producPpages = 0 ;
			if ( produceCount % size == 0 ) {
				producPpages = produceCount / size  ;
				if(producPpages==0) {
					producPpages=1;
				}
			} else {
				producPpages = produceCount / size + 1 ;
			}
			model.addAttribute("producPpages", producPpages);
			model.addAttribute("produces", produces);
			return "user/user";
		}
	}

	// 跳转到控制台
	@RequestMapping("/admin/towelcome")
	public String towelcome(Model model) {
		Map<String, Object> param = new HashMap<String, Object>();
		//查出电影数
		int filmNum = filmDao.queryCountFilm(param);

		//查出影院数量
		int cinemaNum = cinemaDao.cinemaCountList(param);

		//注册人数
		int userNum = userDao.queryCountUser(param);

		//评论数
		int filmReviewNum = filmReviewDao.querycountList(param);

		//会员
		int vip = vipmemberDao.conut();
		int ticketNum = ticketDao.queryCountTicket(param);//统计总订单数和人数
		double money = ticketDao.countMoney();//总金额
		money = (int)money;
		model.addAttribute("ticketNum", ticketNum);
		model.addAttribute("money", money);
		model.addAttribute("filmNum", filmNum);
		model.addAttribute("cinemaNum", cinemaNum);
		model.addAttribute("userNum", userNum);
		model.addAttribute("filmReviewNum", filmReviewNum);
		model.addAttribute("vip", vip);
		return "admin/welcome";
	}

	// 跳转到系统设置
	@RequestMapping("/admin/system")
	public String toSystem() {
		return "admin/system";
	}

	// 跳转到管理员个人中心
	@RequestMapping("/admin/super")
	public String tosuper( Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = userDao.login(userName);

		model.addAttribute("user", user);
		return "admin/admin-info";
	}

	// 修改管理员信息
	@RequestMapping(value = "/admin/updatesuper")
	@ResponseBody
	public String updatesuper( User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User u = userDao.login(userName);
		if(u.getPassword().equalsIgnoreCase(user.getUserPassword())) {

		}else {
			String pass = GeneratePasswordEncoder.generatePasswordEncoder(user.getPassword());// 加密
			user.setUserPassword(pass);
		}

		boolean b = userDao.updateUser(user);
		String success = null;

		if (b) {
			success = "修改成功";
		} else {
			success = "修改失败";
		}
		return success;
	}
	// 上传和刷新头像
	@RequestMapping(value = "/updatephoto", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, String> updataphoto(HttpServletRequest request, MultipartFile photo) {
		Map<String, String> ret = new HashMap<String, String>();
		if (photo == null) {
			ret.put("type", "error");
			ret.put("msg", "选择要上传的文件！");
			return ret;
		}
		if (photo.getSize() > 1024 * 1024 * 10) {
			ret.put("type", "error");
			ret.put("msg", "文件大小不能超过10M！");
			return ret;
		}
		// 获取文件后缀
		String suffix =photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1,photo.getOriginalFilename().length());
		if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase())) {
			ret.put("type", "error");
			ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片！");
			return ret;
		}
		String fileName = photo.getOriginalFilename();
		String path = "F:\\mihayo\\movie\\userPicture\\";
		String uuid = UUID.randomUUID().toString();
		fileName = uuid+fileName ;
		File file = new File(path + fileName);
		if (!file.exists()) {
			// 若不存在该目录，则创建目录
			file.mkdir();
		}
		try { // 将上传的文件保存到指定文件中
			photo.transferTo(file);
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
			ret.put("type", "error");
			ret.put("msg", "保存文件异常！");
			return ret;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ret.put("type", "success");
		ret.put("msg", "上传图片成功！");
		ret.put("fileName", fileName);
		return ret;
	}

	// 修改管理员头像
	@RequestMapping("/admin/updatesuperPicture")
	@ResponseBody
	public String updatesuperPicture( User user) {
		boolean b = userDao.updatePicture(user);
		if(b) {
			return "success";
		}else {
			 return "false";
		}

	}

	// 分页查询用户
	@RequestMapping("/admin/ListUser")
	public String ListUser(User user, Model model,
			@RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Map<String, Object> ParamMap = new HashMap<String, Object>();
		ParamMap.put("user", user);
		int size = 3;
		if(page<=0){
			page=1;
		}
		int begin = (page - 1) * size;

		ParamMap.put("begin", begin);
		ParamMap.put("size", size);

		List<User> users = userDao.queryUser(ParamMap);
		model.addAttribute("users", users);

		int pages = 0;
		int recordCount = userDao.queryCountUser(ParamMap);
		if (recordCount % size == 0) {
			pages = recordCount / size;
			if (pages == 0) {
				pages = 1;
			}
		} else {
			pages = recordCount / size + 1;
		}
//		int s = 10;
//		if(pages>=s){
//			if(pages % s ==0){
//				s = pages / s;
//			}else {
//				s = pages / s +1;
//			}
//		}
//		model.addAttribute("s", s);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		return "user/showListUser";
	}

	// 查询用户信息
	@RequestMapping("/admin/showUpdateUser")
	public String showUpdateUser(int userId, Model model) {
		User user = userDao.query(userId);
		if (user != null) {
			model.addAttribute("user", user);
			return "user/showUpdateUser";
		} else {
			return "error";
		}
	}

	// 修改用户
	@RequestMapping("/admin/updateUser")
	public String updateUser(User user, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User u = userDao.login(userName);
		if(u.getPassword().equalsIgnoreCase(user.getUserPassword())) {

		}else {
			String pass = GeneratePasswordEncoder.generatePasswordEncoder(user.getPassword());// 加密
			user.setUserPassword(pass);
		}
		boolean b = userDao.updateUser(user);
		if (b) {
			return "redirect:/admin/ListUser";
		} else {
			return "error";
		}
	}

	// 跳转到查询所有用户界面
	@RequestMapping("/admin/updateUserPicture")
	public String updateUserPicture(MultipartFile UserPicture, int userId, Model model) {
			String path = "";
				if (userId == 1) {
					path = "redirect:/admin/super";
				} else {
					path = "redirect:/admin/ListUser";
				}

		return path;
	}

	// 删除用户
	@RequestMapping("/admin/deleteUser")
	public String deleteUser(int userId, Model model) {
		boolean b = userDao.deleteUser(userId);
		return "redirect:/admin/ListUser";
	}
	// 分页查询订单
	@RequestMapping("/admin/queryTickets")
	public String queryTickets(Ticket ticket, Model model,
							   @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Map<String, Object> ParamMap = new HashMap<String, Object>();
		ParamMap.put("ticket", ticket);
		int size = 10;
		if(page<=0){
			page=1;
		}
		int begin = (page - 1) * size;

		ParamMap.put("begin", begin);
		ParamMap.put("size", size);

		List<Ticket> tickets = ticketDao.queryTicketList(ParamMap);
		model.addAttribute("tickets", tickets);

		int pages = 0;
		int recordCount = ticketDao.queryCountTicket(ParamMap);
		if (recordCount % size == 0) {
			pages = recordCount / size;
			if (pages == 0) {
				pages = 1;
			}
		} else {
			pages = recordCount / size + 1;
		}

		model.addAttribute("pages", pages);
		model.addAttribute("page", page);

		return "admin/ticket/showTicket";
	}
	//查询订单详情
	@RequestMapping("/admin/showTicket")
	public String queryTickets(int ticketId, Model model){
		Ticket ticket = ticketDao.getTicket(ticketId);
		PositionRelation positionRelation = positionRelationDAO.ById(ticket.getPositionRelationId());

		if (ticket != null) {
			if (positionRelation != null) {
				model.addAttribute("ticket", ticket);
				model.addAttribute("positionRelation", positionRelation);
				return "admin/ticket/TicketInfo";
			}
		}
		return "error";
	}

}
