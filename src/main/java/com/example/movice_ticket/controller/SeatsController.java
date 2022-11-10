package com.example.movice_ticket.controller;

import com.example.movice_ticket.dao.*;
import com.example.movice_ticket.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
/*
* 电影座位
* */
@Controller
public class SeatsController {

	@Autowired
	private SeatsDAO seatsDao;
	
	@Autowired
	private SeatRelationDAO seatRelationDao;
	@Autowired
	private ViewingRelationDAO viewingRelationDao;

	 @Autowired
	 private CinemaDAO cinemaDAO;
	@Autowired
	private FilmDAO filmDAO;
	@Autowired
	private PositionRelationDAO positionRelationDAO;
	@Autowired
	private FilmAndCinemaDAO filmAndCinemaDAO;
	@Autowired
	private UserDAO userDAO;
	 @Autowired
	 private ViewingPositionDAO viewingPositionDAO;
	 @Autowired
	 private TicketDao ticketDao;
	//跳转到创建影厅的页面
	@RequestMapping(value="/admin/toposition")
	public String  toposition(int cinemaId,Model model) {
		model.addAttribute("cinemaId", cinemaId);
		return "admin/viewingPosition/createSeats";
	}
 
	 
	//影厅绑定影院
	@RequestMapping("/admin/creatSeatRelation")
	public String creatSeatRelation(String cinemaNum,int cinemaId,String map) {
		 
		//保存座位地图
		Seats seat = new Seats(0, map, cinemaNum);
		int seatId = seatsDao.creatSeats(seat);
		 
		SeatRelation seatRelation = new SeatRelation(cinemaId, seatId);
		boolean b = seatRelationDao.createSeatRelation(seatRelation);
		if(b) {
			return "redirect:/admin/selectSeatRelation?cinemaId="+cinemaId;
		}else {
			return "error";
		}
		
	}
	//查看绑定的影厅
	@RequestMapping("/admin/selectSeatRelation")
	public String selectSeatRelation(int cinemaId, Model model) {
		 
		List<SeatRelation> seatRelations = seatRelationDao.getSeat(cinemaId); 
		 
		if(seatRelations!=null) {
			model.addAttribute("seatRelations", seatRelations);
			return "admin/viewingPosition/showSeatRelation";
		}else {
			return "error";
		}
	}
	//查看影厅座位排列
	@RequestMapping("/admin/showSeats")
	public String showSeats(int seatId,int cinemaId,  Model model) { 
		 Seats seat = seatsDao.getSeats(seatId);
		 model.addAttribute("seat", seat);
		 model.addAttribute("cinemaId", cinemaId);
		 return "admin/viewingPosition/showSeats";
	}
	//修改影厅座位排列
	@RequestMapping("/admin/updateSeats")
	public String updateSeats(int seatId,int cinemaId, Model model,String map,String cinemaNum) {
		Seats seat = new Seats(seatId, map, cinemaNum);
	    boolean b = seatsDao.updateSeat(seat);
	    if(b) {
		   return "redirect:/admin/showSeats?seatId="+seatId+"&cinemaId="+cinemaId;
	    }else {
		   return "error";
	    }
	}
	
	@RequestMapping("/admin/deleteSeats")
	public String deleteSeats(int seatRelationId, Model model,int cinemaId,int seatId) {		
		 //解除绑定
		 boolean b = seatRelationDao.deleteSeatRelation(seatRelationId);
		  if(b) {
			  //删除影厅座位排列
			  b = seatsDao.delete(seatId);
			  return "redirect:/admin/selectSeatRelation?cinemaId="+cinemaId;
		  }else {
			  return "error";
		  }
			
	}
	//查看购票的影厅和场次
	@RequestMapping("/user/selectSeatRelation")
	public String selectPosition(@RequestParam(name="cinemaId",required=false,defaultValue="1")int cinemaId,
								 @RequestParam(name="filmId",required=false,defaultValue="1")int filmId,
								@RequestParam(name="page",required=false,defaultValue="1")int page, Model model) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cinemaId", cinemaId);
		int size = 10 ;
		if (page<=0){
			page = 1;
		}
		int begin = (page-1)*size ;
		param.put("filmId", filmId);
		param.put("begin", begin) ;
		param.put("size", size) ;
		model.addAttribute("cinemaId", cinemaId);
		model.addAttribute("filmId", filmId);
		//分页查询全部电影
		List<FilmAndCinema> filmAndCinemas = filmAndCinemaDAO.queryByCinemaId(param);

		model.addAttribute("filmAndCinemas", filmAndCinemas);
		Film film = filmDAO.getFilmById(filmId);
		model.addAttribute("film", film);
		List<ViewingRelation> viewingRelations = viewingRelationDao.getViewingTime(param);
		int count = viewingRelationDao.getViewingTimeConut(param);//总场次
		model.addAttribute("count", count);
		int countPages = 0;// 总场次的页数
		if(count % size ==0) {
			countPages = count / size ;
			if(countPages==0){
				countPages = 1;
			}
		}else {
			countPages = count /size + 1;
		}
		model.addAttribute("countPages", countPages);
		int pages = 0;
		int recordCount = filmAndCinemaDAO.queryCountFilmAndCinema(cinemaId);//该影院的总电影数
		model.addAttribute("recordCount", recordCount);
		if(recordCount % size ==0) {
			pages = recordCount / size ;
			if(pages==0){
				pages = 1;
			}
		}else {
			pages = recordCount /size + 1;
		}

		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		if( viewingRelations!=null ) {
			model.addAttribute("viewingRelations", viewingRelations);
			return "user/cinema/viewingTime";
		}else {
			return "error";
		}
	}
	//进行选位
	@RequestMapping("/user/toSelectSeats")
	public String toSelectSeats(int cinemaId, int filmId,int seatId,
								@RequestParam(name="viewingRelationId",required=false,defaultValue="1")int viewingRelationId,
								@RequestParam(name="viewingTimeId",required=false,defaultValue="1")int viewingTimeId, @RequestParam(name="page",required=false,defaultValue="1")int page, Model model) {

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cinemaId", cinemaId);
		param.put("filmId", filmId);
		param.put("seatId", seatId);
		//查询场次信息
		List<ViewingRelation> viewingRelations = viewingRelationDao.getViewingTimeByseatId(param);
		model.addAttribute("viewingRelationId", viewingRelationId);
		//查出已选的位置
		List<PositionRelation> positionRelations = positionRelationDAO.queryPositionRelation(param);
		String position = "";
		if (positionRelations!=null){

			for (PositionRelation positionRelation: positionRelations){
				System.out.println(positionRelation.getViewingTime().getViewingTimeId()+"----"+viewingTimeId);
				//判断是否是同一个场次
				if(viewingTimeId==positionRelation.getViewingTime().getViewingTimeId()){
					//是否是同一个影厅
					if (seatId==positionRelation.getSeats().getSeatId()){
						System.out.println(positionRelation.getSeats().getSeatId()+"======="+seatId);
						if(positionRelation.getViewingPosition().getPosition().length()<5){
							position =  positionRelation.getViewingPosition().getPosition().substring(0, 3).replace("排","_")+"," +position;
						}else {
							position =  positionRelation.getViewingPosition().getPosition().substring(0, 4).replace("排","_")+"," +position;
						}
					}
				}
			}
		}
		System.out.println(position+"-------------");
//		Seats seat = seatsDao.getSeats(seatId);
		Film film = filmDAO.getFilmById(filmId);
		float price = film.getFilmPrice().getPrice();
		if(viewingRelations!=null ) {
			model.addAttribute("viewingRelations", viewingRelations);
			model.addAttribute("price", price);
			model.addAttribute("position", position);
//			model.addAttribute("seat", seat);
			return "user/viewingPosition/createViewingPosition";
		}else {
			return "error";
		}
	}
	//重新选择购票的影厅和场次
	@RequestMapping("/user/toupdateviewingtime")
	public String updateviewingtiem(@RequestParam(name="cinemaId",required=false,defaultValue="1")int cinemaId,
								 @RequestParam(name="filmId",required=false,defaultValue="1")int filmId,
								 @RequestParam(name="page",required=false,defaultValue="1")int page,
									@RequestParam(name="positionRelationId",required=false,defaultValue="1")int positionRelationId ,Model model) {
		model.addAttribute("positionRelationId", positionRelationId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cinemaId", cinemaId);
		int size = 10 ;
		if (page<=0){
			page = 1;
		}
		int begin = (page-1)*size ;
		param.put("filmId", filmId);
		param.put("begin", begin) ;
		param.put("size", size) ;
		model.addAttribute("cinemaId", cinemaId);
		model.addAttribute("filmId", filmId);

		Film film = filmDAO.getFilmById(filmId);
		model.addAttribute("film", film);

		List<ViewingRelation> viewingRelations = viewingRelationDao.getViewingTime(param);
		int count = viewingRelationDao.getViewingTimeConut(param);//总场次
		model.addAttribute("count", count);
		int countPages = 0;// 总场次的页数
		if(count % size ==0) {
			countPages = count / size ;
			if(countPages==0){
				countPages = 1;
			}
		}else {
			countPages = count /size + 1;
		}
		model.addAttribute("countPages", countPages);
		model.addAttribute("page", page);
		if( viewingRelations!=null ) {
			model.addAttribute("viewingRelations", viewingRelations);
			return "user/cinema/updateViewingTime";
		}else {
			return "error";
		}
	}
	//重新进行选位
	@RequestMapping("/user/reSelectSeats")
	public String reSelectSeats(int cinemaId, int filmId,int seatId,int viewingRelationId,int viewingTimeId,int positionRelationId, @RequestParam(name="page",required=false,defaultValue="1")int page, Model model) {

		model.addAttribute("positionRelationId", positionRelationId);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("cinemaId", cinemaId);
		param.put("filmId", filmId);
		param.put("seatId", seatId);
		//查询场次信息
		List<ViewingRelation> viewingRelations = viewingRelationDao.getViewingTimeByseatId(param);
		model.addAttribute("viewingRelationId", viewingRelationId);
		//查出已选的位置
		List<PositionRelation> positionRelations = positionRelationDAO.queryPositionRelation(param);
		String position = "";
		if (positionRelations!=null){
			for (PositionRelation positionRelation: positionRelations){
				//判断是否是同一个场次
				if(viewingTimeId==positionRelation.getViewingTime().getViewingTimeId()){
					//是否是同一个影厅
					if (seatId==positionRelation.getSeats().getSeatId()){

						if(positionRelation.getViewingPosition().getPosition().length()<5){
							position =  positionRelation.getViewingPosition().getPosition().substring(0, 3).replace("排","_")+"," +position;
						}else {
							position =  positionRelation.getViewingPosition().getPosition().substring(0, 4).replace("排","_")+"," +position;
						}
					}
				}
			}
		}
//		Seats seat = seatsDao.getSeats(seatId);
		Film film = filmDAO.getFilmById(filmId);
		float price = film.getFilmPrice().getPrice();
		if(viewingRelations!=null ) {
			model.addAttribute("viewingRelations", viewingRelations);
			model.addAttribute("price", price);
			model.addAttribute("position", position);
//			model.addAttribute("seat", seat);
			return "user/viewingPosition/toupdateViewPosition";
		}else {
			return "error";
		}
	}

	//修改场次和座位
	@RequestMapping("/user/updateViewPosition")
	public String createTicket(int filmId, int cinemaId, int viewingTimeId,
							   int positionNum, int seatId,String position,int positionRelationId, Model model) {
//		System.out.println(money+"+++"+position);
		Map<String,Object> map = new HashMap<>();
		ViewingPosition viewingPosition = null;
		String path = "";
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		User user = userDAO.login(userName);
		for (int i =1;i <= position.length();i++){
			if((i % 4)==0){
				if(i>=4){
					String s = position.substring(i-4,i);
					//创建观影位置
					viewingPosition = new ViewingPosition(0,positionNum,0,s);
					int viewingPositionId = viewingPositionDAO.createViewingPosition(viewingPosition);
					if (viewingPositionId>0){
						PositionRelation positionRelation = new PositionRelation(positionRelationId,seatId,viewingPositionId,cinemaId, filmId, viewingTimeId, user.getUserId());
						//修改已选位置
						boolean b = positionRelationDAO.updatePosition(positionRelation);
						if(b){

							path = "redirect:/user/userInfo";
						}else {
							path= "error";
						}
					}
				}

			}
		}
		return path ;
	}
}
