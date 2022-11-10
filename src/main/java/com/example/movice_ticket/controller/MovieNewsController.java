package com.example.movice_ticket.controller;

import com.example.movice_ticket.dao.FilmDAO;
import com.example.movice_ticket.dao.MovieNewsDAO;
import com.example.movice_ticket.model.Film;
import com.example.movice_ticket.model.MovieNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/*
* 资讯管理
* */

@Controller
public class MovieNewsController {

	@Autowired
	private MovieNewsDAO movieNewsDao;
	
	@Autowired
	private FilmDAO filmDao;
	//跳转到创建影讯的页面
	@RequestMapping("/admin/showCreateMovieNews")
	public String showCreateMovieNews(Model model) {
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		List<Film> films = filmDao.queryFilm(param);
		model.addAttribute("films",films);
		return "admin/movieNews/showCreateMovieNews";
	}
	//上传资讯图片和预览
	@RequestMapping("/admin/upNewpicture")
	@ResponseBody
	public Map<String, String> upNewpicture(MultipartFile photo) {
		Map<String, String> ret = new HashMap<String, String>();
		if (photo == null) {
			ret.put("type", "error");
			ret.put("msg", "选择要上传的文件！");
			return ret;
		}
		if (photo.getSize() > 1024 * 1024 * 15) {
			ret.put("type", "error");
			ret.put("msg", "文件大小不能超过15M！");
			return ret;
		}
		// 获取文件后缀
		String suffix =photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1,photo.getOriginalFilename().length());
		if (!"jpg,jpeg,gif,png".toUpperCase().contains(suffix.toUpperCase()) ) {
			ret.put("type", "error");
			ret.put("msg", "请选择jpg,jpeg,gif,png格式的图片或视频！");
			return ret;
		}
		String fileName = photo.getOriginalFilename();
		String path = "F:\\mihayo\\movie\\filmPicture\\";
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
		ret.put("msg", "上传图片/视频成功！");
		ret.put("fileName", fileName);
		return ret;
	}
	//上传资讯视频和预览
	@RequestMapping("/admin/upNewvideo")
	@ResponseBody
	public Map<String, String> upNewvideo(MultipartFile video) {
		Map<String, String> ret = new HashMap<String, String>();
		if (video == null) {
			ret.put("type", "error");
			ret.put("msg", "选择要上传的文件！");
			return ret;
		}
		if (video.getSize() > 1024 * 1024 * 30) {
			ret.put("type", "error");
			ret.put("msg", "文件大小不能超过15M！");
			return ret;
		}
		// 获取文件后缀
		String suffix =video.getOriginalFilename().substring(video.getOriginalFilename().lastIndexOf(".") + 1,video.getOriginalFilename().length());
		if (!"gif,3gp,asf,avi,dat,dv,flv,f4v,m2t,m4v,mj2,mov,mp4,mpe,mpg,mpeg,mts,ogg,qt,rm,rmvb,swf,ts,vob,wmv,webm".toUpperCase().contains(suffix.toUpperCase()) ) {
			ret.put("type", "error");
			ret.put("msg", "请选择gif,mp4,mpg等格式的视频！");
			return ret;
		}
		String fileName = video.getOriginalFilename();
		String path = "F:\\mihayo\\movie\\filmPicture\\";
		String uuid = UUID.randomUUID().toString();
		fileName =uuid+ fileName  ;
		File file = new File(path + fileName);
		if (!file.exists()) {
			// 若不存在该目录，则创建目录
			file.mkdir();
		}
		try { // 将上传的文件保存到指定文件中
			video.transferTo(file);
		} catch (IllegalStateException e1) {
			e1.printStackTrace();
			ret.put("type", "error");
			ret.put("msg", "保存文件异常！");
			return ret;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ret.put("type", "success");
		ret.put("msg", "上传视频成功！");
		ret.put("fileName", fileName);
		return ret;
	}
	//创建影讯, MultipartFile picture
	@RequestMapping("/admin/createMovieNews")
	public String CreateMovieNews(MovieNews movieNews) throws IllegalStateException, IOException {

		boolean b = movieNewsDao.createMovieNews(movieNews);
		if(b) {
			return "redirect:/admin/queryMovieNews";
		}else {
			return "error";
		}
		 
	}
	//分页查询所有影讯
	@RequestMapping("/admin/queryMovieNews")
	public String ListMovieNews(MovieNews movieNews, @RequestParam(name="page",required=false,defaultValue="1")int page,Model model){
		Map<String, Object>paramMap = new HashMap<String, Object>();
		paramMap.put("movieNews", movieNews);
		int size = 3;
		if (page<=0){
			page = 1;
		}
		int begin = (page - 1)*size;
		paramMap.put("size", size) ;
		paramMap.put("begin", begin);
		List<MovieNews> listmovieNews = movieNewsDao.list(paramMap);
		model.addAttribute("listmovieNews", listmovieNews);
		int pages = 0;
		int recordcount = movieNewsDao.listcount(paramMap);
		if ( recordcount % size == 0 ) {
			pages = recordcount / size  ;
			if(pages==0) {
				pages=1;
			}
		} else {
			pages = recordcount / size + 1 ;
		}

		model.addAttribute("pages",pages) ;
		model.addAttribute("page",page) ;
		return "admin/movieNews/showMovieNews";
	}
	//删除影讯
	@RequestMapping("/admin/deleteMovieNews")
	public String delete(int movieNewsId ) {
		boolean b = movieNewsDao.deleteMovieNews(movieNewsId);
		return "redirect:/admin/queryMovieNews";
	}
	//查询影讯信息跳转到修改影讯页面
	@RequestMapping("/admin/showUpdateMovieNews")
	public String showupdatemovieNaws(int movieNewsId , Model model ) {
		MovieNews movieNews = movieNewsDao.getMovieNewsbyId(movieNewsId);
		model.addAttribute("movieNews", movieNews);
		
		Map<String, Object> param = new HashMap<String, Object>();
		
		List<Film> films = filmDao.queryFilm(param);
		model.addAttribute("films",films);
		
		return "admin/movieNews/showUpdateMovieNews";
	}
	//修改影讯信息
	@RequestMapping("/admin/updateMovieNews")
	public String updatemovieNaws(MovieNews movieNews , Model model ) {
		//调用修改资讯接口
		 boolean b = movieNewsDao.updateMovieNews(movieNews);
		//跳转到资讯列表页面
		return "redirect:/admin/queryMovieNews";
	}
	//修改海报信息
	@RequestMapping("/admin/updateNewsPicture")
	public String updateNewsPicture(int movieNewsId ,String newsPicture,String video, Model model ) {

			Map<String, Object> param = new HashMap<String, Object>();
		 param.put("movieNewsId", movieNewsId);
		param.put("newsPicture", newsPicture);
		param.put("video", video);
		boolean b =  movieNewsDao.updateNewspicture(param);
		 if(b){
			 return "redirect:/admin/showUpdateMovieNews?movieNewsId="+movieNewsId;
		 }else {
			 return "error";
		 }
	}

	//用户查询影讯详情
	@RequestMapping("/user/showMovieNews")
	public String showMovieNews(int movieNewsId,Model model ) {
		MovieNews movieNews = movieNewsDao.getMovieNewsbyId(movieNewsId);
		if (movieNews!=null){
			model.addAttribute("movieNews", movieNews);
			return "user/film/showMovieNews";
		}
		return "error";
	}
}
