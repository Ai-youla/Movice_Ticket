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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* 影院管理
* */
@Controller
public class CinemaController {

	@Autowired
	private CinemaDAO cinemaDao;
	
	@Autowired
	private CinemaTypeDAO cinemaTypeDao;
	
	@Autowired
	private CinemaAddressDAO cinemaAddressDao;
	
	@Autowired
	private province_city_countyDAO province_city_countyDao;
	@Autowired
	private FilmAndCinemaDAO filmAndCinemaDAO;
	@Autowired
	private SeatRelationDAO seatRelationDAO;
	@Autowired
	private ViewingRelationDAO viewingRelationDAO;

	@Autowired
	private FilmDAO filmDao;
	 //跳转到创建影院的页面
	@RequestMapping("/admin/toCreateCinema")
	public String toCreateCinema(Model model) {
		List<CinemaType> cinemaTypes = cinemaTypeDao.CinemaTypeList();
		model.addAttribute("cinemaTypes", cinemaTypes);
		
		Map<String, Object>param = new HashMap<String, Object>();
		int province = 1;
		//查询全部的省
		List<province_city_county> province_city_countys = province_city_countyDao.list(province);
		model.addAttribute("province_city_countys", province_city_countys);
			
		return "admin/cinema/createCinema";
	}
	//创建影院
	@RequestMapping("/admin/createCinema")
	public String createCinema(Cinema cinema, CinemaAddress cinemaAddress , Model model) {
		 int cinemaAddressId = cinemaAddressDao.createCinemaAddress(cinemaAddress);
		 
		 if(cinemaAddressId > 0) {
			 cinema.setCinemaAddressId(cinemaAddressId);
			 boolean b = cinemaDao.createCinema(cinema);
			 if(b) {
				 return "redirect:/admin/selectCinema";
			 }else {
				 return "error";
			 }
		 }else {
			 return "error";
		 }	
	}
	//分页查询所有影院
	@RequestMapping("/admin/selectCinema")
	public String selectCinema(Cinema cinema,@RequestParam(name="page",required = false,defaultValue = "1")int page ,Model model) {
		 Map<String, Object>param = new HashMap<String, Object>();
		 int size = 3;
		if(page<=0){
			page=1;
		}
		 int begin = (page - 1) * size;
		 param.put("size", size);
		 param.put("begin", begin);
		 param.put("cinema", cinema);
		 
		 List<Cinema> cinemas = cinemaDao.cinemaList(param);
		 model.addAttribute("cinemas", cinemas);
		
		 int pages = 0;
		 int recordCount = cinemaDao.cinemaCountList(param);
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
		  
		 return "admin/cinema/showCinema";
	}
	//查询影院信息进行编辑
	@RequestMapping("/admin/showUpdateCinema")
	public String showUpdateCinema(int cinemaId ,Model model) {
		 Cinema cinema = cinemaDao.getCinemaById(cinemaId);
		 if(cinema!=null) {
			 List<CinemaType> cinemaTypes = cinemaTypeDao.CinemaTypeList();
			 model.addAttribute("cinemaTypes", cinemaTypes);

			 Map<String, Object> param = new HashMap<String, Object>();
			 List<Film> films = filmDao.queryFilm(param);
			 model.addAttribute("films", films);
				
			 int province = 1;
			 List<province_city_county> province_city_countys = province_city_countyDao.list(province);
			 model.addAttribute("province_city_countys", province_city_countys);

			 int city = 2;
			 List<province_city_county> province_city_county2 = province_city_countyDao.list(city);
			 model.addAttribute("province_city_county2", province_city_county2);

			 int county = 3;
			 List<province_city_county> province_city_county3 = province_city_countyDao.list(county);
			 model.addAttribute("province_city_county3", province_city_county3);
			
			 model.addAttribute("cinema", cinema);
			 return "admin/cinema/updateCinema";
		 }else {
			  return "error";
		 }  
	}
	//修改影院信息
	@RequestMapping("/admin/updateCinema")
	public String updateCinema(Cinema cinema  ,CinemaAddress cinemaAddress ,Model model) {
		 boolean b = cinemaDao.updateCinema(cinema);
		 if(b) {
			 b = cinemaAddressDao.updateCinemaAddress(cinemaAddress);
			 if(b) {
				 return "redirect:/admin/selectCinema";
			 }else {
				  return "error";
			 }	
		 }else {
			 return "error";
		 }	 
	}
	//通过省份查询下级市
	@RequestMapping(value="/selectCity",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<province_city_county> selectCity(String province) {
		List<province_city_county> province_city_countys = new ArrayList<province_city_county>();
		province_city_county pcc = province_city_countyDao.byName(province);
		 
		if(pcc!=null) {
			Map<String, Object> param = new HashMap<String, Object>();
			String code  = pcc.getCode();

			code = code.substring(0, 3);
			param.put("code", code);
			int type = 2;
			param.put("type", type);
			province_city_countys = province_city_countyDao.getNextAddress(param);
		}
		return province_city_countys;
	}
	//通过城市查询下级县
	@RequestMapping(value="/selectCounty",method= {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public List<province_city_county> selectCounty(String city) {
		List<province_city_county> province_city_countys = new ArrayList<province_city_county>();
		province_city_county pcc = province_city_countyDao.byName(city);
		if(pcc!=null) {
			Map<String, Object>param = new HashMap<String, Object>();
			String code  = pcc.getCode();
			code = code.substring(0, 4);
			param.put("code", code);
			int type = 3;
			param.put("type", type);
			 province_city_countys = province_city_countyDao.getNextAddress(param); 
		}
		return province_city_countys;
	}
	//跳转到有某个电影的影院
	@RequestMapping("/user/showfilm")
	public String usershowfilm(int filmId,Model model) {

		Map<String, Object> map = new HashMap<>();
		//查出全部影院
		List<Cinema> cinemas = cinemaDao.cinemaList(map);
		model.addAttribute("cinemas",cinemas);
		//查询该电影的全部影院
		List<FilmAndCinema> filmAndCinemas = filmAndCinemaDAO.byfilmId(filmId);
		model.addAttribute("filmAndCinemas",filmAndCinemas);
		model.addAttribute("filmId",filmId);
		return "user/cinema/queryCinemaByFilmId"; }

	//跳转用户到全部影院
	@RequestMapping("/user/showCinema")
	public String showCinema(Cinema cinema ,Model model,@RequestParam(name="page",required = false,defaultValue = "1")int page ) {
		Map<String, Object> param = new HashMap<>();
		int size = 10;
		if (page <= 0) {
			page = 1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);
		param.put("cinema", cinema);
		List<Cinema> cinemas = cinemaDao.cinemaList(param);
		model.addAttribute("cinemas", cinemas);

		int pages = 0;
		int recordCount = cinemaDao.cinemaCountList(param);
		model.addAttribute("recordCount", recordCount);
		if (recordCount % size == 0) {
			pages = recordCount / size;
			if (pages == 0) {
				pages = 1;
			}
		} else {
			pages = recordCount / size + 1;
		}
		int end = 1;
		 for(int i =1; i <= pages ; i++){
			 if((i - page)== 9){
				 end = i ;
			 }
		 }
		model.addAttribute("end",end) ;
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);

		return "user/cinema/allCinema";
	}
	//跳转到电影分配页面
	@RequestMapping("/admin/toCreateFilmAndCinema")
	public String toCreateFilmAndCinema(Cinema cinema,@RequestParam(name="page",required = false,defaultValue = "1")int page ,Model model) {
		Map<String, Object>param = new HashMap<String, Object>();
		int size = 10;
		if(page<=0){
			page=1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);
		param.put("cinema", cinema);
		List<Cinema> cinemas = cinemaDao.cinemaList(param);
		model.addAttribute("cinemas", cinemas);
		//查询全部电影
		List<Film> films = filmDao.queryFilm(param);
		model.addAttribute("films", films);
		int pages = 0;
		int recordCount = filmDao.queryCountFilm(param);
		if(recordCount % size ==0) {
			pages = recordCount / size ;
			if(pages==0){
				pages = 1;
			}
		}else {
			pages = recordCount /size + 1;
		}
		int end = 1;
		for (int i =1; i <=pages; i++){
			if((i - page )==9){
				end = i;
			} else if (i<=10) {
				end = i;
			}
		}
		model.addAttribute("end", end);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);

		return "admin/cinema/CreatFilmAndCinema";
	}
	//影院批量分配电影
	@RequestMapping("/admin/creatFilmAndCinema")
	public String creatFilmAndCinema(int  cinemaId, Model model, HttpServletRequest request) {
		String filmIds[] = request.getParameterValues("filmId");
		FilmAndCinema[] filmAndCinemas= null;
		int ucinemaId =1;
		if(cinemaId>0) {
			ucinemaId = cinemaId;
		}
		if(filmIds!=null) {
			filmAndCinemas = new FilmAndCinema[filmIds.length];
			for(int i = 0 ; i< filmIds.length ; i++) {
				int filmId = Integer.parseInt(filmIds[i]) ;

				FilmAndCinema filmAndCinema = new FilmAndCinema( ucinemaId, filmId);
				filmAndCinemas[i]=filmAndCinema;
			}
		}
		boolean b = filmAndCinemaDAO.createFilmAndCinema(filmAndCinemas);
		if(b) {
			return "redirect:/admin/showFilmAndCinema?cinemaId="+cinemaId;
		}else {
			return "error";
		}
	}
	//分页查询已分配的电影
	@RequestMapping("/admin/showFilmAndCinema")
	public String showProduceRelation(@RequestParam(name="page",required = false,defaultValue = "1")int page,
									  @RequestParam(name="cinemaId",required=false,defaultValue="1")int cinemaId ,Model model) {
		Map<String, Object>param = new HashMap<String, Object>();
		int size = 10;
		if(page<=0){
			page=1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);
		param.put("cinemaId", cinemaId);
		model.addAttribute("cinemaId", cinemaId);
		List<Cinema> cinemas = cinemaDao.cinemaList(param);
		model.addAttribute("cinemas", cinemas);
		//分页查询全部电影
		List<FilmAndCinema> filmAndCinemas = filmAndCinemaDAO.queryByCinemaId(param);
		model.addAttribute("filmAndCinemas", filmAndCinemas);
		int pages = 0;
		int recordCount = filmAndCinemaDAO.queryCountFilmAndCinema(cinemaId);
		if(recordCount % size ==0) {
			pages = recordCount / size ;
			if(pages==0){
				pages = 1;
			}
		}else {
			pages = recordCount /size + 1;
		}
		int end = 1;
		for (int i =1; i <=pages; i++){
			if((i - page )==9){
				end = i;
			} else if (i<=10) {
				end = i;
			}
		}
		model.addAttribute("end", end);
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		return "admin/cinema/showFilmAndCinema";
	}
	//重新分配电影
	@RequestMapping("/admin/updateFilmAndCinema")
	public String updateFilmAndCinema(int  cinemaId,Model model,HttpServletRequest request) {
		String filmAndCinemas[] = request.getParameterValues("f_c_relationId");
		FilmAndCinema[] filmAndCinemalist= null;
		int u =1;
		if(cinemaId>0) {
			u = cinemaId;
		}
		if(filmAndCinemas!=null) {
			filmAndCinemalist = new FilmAndCinema[filmAndCinemas.length];
			for(int i = 0 ; i< filmAndCinemas.length ; i++) {
				int f_c_relationId = Integer.parseInt(filmAndCinemas[i]) ;
				FilmAndCinema filmAndCinema = new FilmAndCinema(f_c_relationId,cinemaId,0);
				filmAndCinemalist[i]=filmAndCinema;
			}
		}
		boolean b = filmAndCinemaDAO.assignFilm(filmAndCinemalist);
		if(b) {
			return "redirect:/admin/showFilmAndCinema?cinemaId="+cinemaId;
		}
		return "admin/welcome";
	}
	//选择场次
	@RequestMapping("/user/selectFilmAndCinema")
	public String selectFilmAndCinema(@RequestParam(name="page",required = false,defaultValue = "1")int page,
									  @RequestParam(name="cinemaId",required=false,defaultValue="1")int cinemaId ,
									  @RequestParam(name="filmId",required=false,defaultValue="1")int filmId ,Model model) {
		Map<String, Object>param = new HashMap<String, Object>();
		int size = 10;
		if(page<=0){
			page=1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);
		param.put("cinemaId", cinemaId);
		param.put("filmId", filmId);
		model.addAttribute("cinemaId", cinemaId);
		model.addAttribute("filmId", filmId);

		//分页查询全部电影
		List<FilmAndCinema> filmAndCinemas = filmAndCinemaDAO.queryByCinemaId(param);
		model.addAttribute("filmAndCinemas", filmAndCinemas);
		//分页查询该电影在影院中的全部场次
		List<ViewingRelation> viewingRelations = viewingRelationDAO.getViewingTime(param);
		int count = viewingRelationDAO.getViewingTimeConut(param);//总场次
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
		model.addAttribute("viewingRelations", viewingRelations);
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
		return "user/cinema/selectfilmAndviewingTime";
	}

}
