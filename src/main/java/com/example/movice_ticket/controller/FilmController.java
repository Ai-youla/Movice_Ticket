package com.example.movice_ticket.controller;


import com.example.movice_ticket.dao.FilmDAO;
import com.example.movice_ticket.dao.FilmPriceDAO;
import com.example.movice_ticket.dao.FilmTypeDAO;
import com.example.movice_ticket.dao.PlayRelationDAO;
import com.example.movice_ticket.model.Film;
import com.example.movice_ticket.model.FilmPrice;
import com.example.movice_ticket.model.FilmType;
import com.example.movice_ticket.model.PlayRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
 
/*
 电影管理
* */
@Controller
public class FilmController {
	
	@Autowired
	private FilmDAO filmDao;
	
	@Autowired
	private FilmPriceDAO filmPriceDao;
	
	@Autowired
	private FilmTypeDAO filmTypeDao;
	
	 
	
	@Autowired
	private PlayRelationDAO playRelationDao;
	//跳转到创建电影页面
	@RequestMapping("/admin/toCreateFilm")
	public String toCrateFilmPrice(Model model) { 
		List<FilmType> filmTypes = filmTypeDao.list();
		model.addAttribute("filmTypes", filmTypes);
		
		return "admin/film/createFilm";
	}
	//创建电影
	@RequestMapping("/admin/createFilm")
	public String createPerson(Film film,   HttpServletRequest request) {
		String path = "";
		float price = Float.parseFloat(request.getParameter("price"));
		FilmPrice filmPrice = new FilmPrice();
		filmPrice.setPrice(price);
		int  filmPriceId = filmPriceDao.createFilmPrice(filmPrice);
		film.setFilmPriceId(filmPriceId);
			int filmId = filmDao.createFilm(film);
			 if (filmId>0) {
					path= "redirect:/admin/toCreateFilm";
				} else {
					path= "error";
				}
		return path;
	}
	//分页查询所有电影
	@RequestMapping("/admin/selectFilm")
	public String queryFilm(Film film , Model model ,
			@RequestParam(name="page",required=false,defaultValue="1")int page) {
		
		Map<String,Object> paramMap = new HashMap<String,Object>() ;
		paramMap.put("film", film) ;
		
		int size = 3 ;
		if (page<=0){
			page = 1;
		}
		int begin = (page-1)*size ;
		
		paramMap.put("begin", begin) ;
		paramMap.put("size", size) ;
	
		List<Film> films = filmDao.queryFilm(paramMap);
		model.addAttribute("films",films) ;
		
		int pages = 0 ;
		int recordCount = filmDao.queryCountFilm(paramMap);
		
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
		return "admin/film/queryFilm";
	}
	//查询电影信息
	@RequestMapping("/admin/showUpdatefilm")
	public String showUpdatefilm(int filmId,Model model ) {
		List<FilmType> filmTypes = filmTypeDao.list();
		model.addAttribute("filmTypes", filmTypes);	 
	 
		Film film  = filmDao.getFilmById(filmId);		
		model.addAttribute("film", film);
		
		List<PlayRelation> playRelations = playRelationDao.getParticipants(filmId);
		model.addAttribute("playRelations", playRelations);
		
		return "admin/film/showUpdatefilm";
	}
	//修改电影信息
	@RequestMapping("/admin/updateFilm")
	public String updateFilm(Film film , Model model,HttpServletRequest request) {
		boolean b =  filmDao.updateFilm(film);
		  String[] playRelationIds =request.getParameterValues("playRelationId");
		  
		  PlayRelation[] playRelations = null; 
		  if(playRelationIds!=null ) {
		  playRelations = new PlayRelation[playRelationIds.length]; 
		  for(int i =0; i<playRelationIds.length; i++) {
			  int playRelationId =Integer.parseInt(playRelationIds[i]); 
			  PlayRelation playRelation = new PlayRelation(playRelationId,0, 0);
			  playRelations[i]=playRelation;
		  	} 
		  }
		 int filmPriceId = film.getFilmPriceId();
		float price = Float.parseFloat(request.getParameter("price"));
		 
		
		  FilmPrice filmPrice = new FilmPrice(); filmPrice.setPrice(price);
		  filmPrice.setFilmPriceId(filmPriceId);
		 
		b=filmPriceDao.updateFilmPrice(filmPrice);
		
		boolean s = playRelationDao.assignPlayRelation(playRelations);
		if(b) {
			return "redirect:/admin/selectFilm";
		}else {
			return "error";
		}
		
	}
	//查询电影信息跳转到修改电影图片的页面
	@RequestMapping("/admin/showUpdateFilmPicture")
	public String showUpdateFilmPicture(int filmId,Model model) {
		Film film  = filmDao.getFilmById(filmId);
		
		model.addAttribute("film", film);
		return "redirect:/admin/showUpdatefilm?filmId="+film.getFilmId();
	}
	//修改电影图片
	@RequestMapping("/admin/updateFilmPicture")
	public String UpdateFilmPicture(int filmId,String filmPicture , String video ,Model model) {
		 
	 String path = "";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("filmPicture", filmPicture);
		map.put("video", video);
			map.put("filmId", filmId);

			boolean b =  filmDao.updateFilmPrice(map);

			 if (b) {
					 path= "redirect:/admin/showUpdatefilm?filmId="+filmId; 
					 
				} else {
					path= "error";
				}
		return path;
	}
	//删除下架电影
	@RequestMapping("/admin/deletefilm")
	public String deleteFilm(int filmId) {
		//调用删除电影接口
		boolean b = filmDao.deleteFilm(filmId);
		if (b) {
			//重定向到查询电影列表的控制台查询全部电影再跳转到电影列表页面
			return "redirect:/admin/selectFilm";
		} else {
			return "error";
		}
	}
	//用户根据电影名进行查找电影
	@RequestMapping("/user/byfilmName")
	public String byfilmName(String filmName , Model model) {
		Map<String,Object> paramMap = new HashMap<String,Object>() ;
		Film film = new Film();
		film.setFilmName(filmName);
		paramMap.put("film", film) ;

		int size = 10 ;
		int	page = 1;
		int begin = (page-1)*size ;

		paramMap.put("begin", begin) ;
		paramMap.put("size", size) ;

		List<Film> films = filmDao.queryFilm(paramMap);
		model.addAttribute("films", films);
		return "user/film/showfilm";
	}

}
