package com.example.movice_ticket.controller;


import com.example.movice_ticket.dao.ViewingTimeDAO;
import com.example.movice_ticket.model.ViewingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ViewingTimeController {

	@Autowired
	private ViewingTimeDAO viewingTimeDao;
	//跳转到创建批次页面
	@RequestMapping("/admin/toCtreateViewingTime")
	public String toCreateViewingTime() {
		return "admin/viewingTime/CreateViewingTime";
	}
	//创建批次
	@RequestMapping("/admin/createViewingTime")
	public String ctreateViewingTime(ViewingTime viewingTime, Model model) {
	 	boolean b = viewingTimeDao.createViewingTime(viewingTime);
		if(b) {
			return "redirect:/admin/selectViewingTime";
		}else {
			return "error";
		}
	}
	//分页查询批次
	@RequestMapping("/admin/selectViewingTime")
	public String selectViewingTime(ViewingTime viewingTime,Model model,
			@RequestParam(name="page",required = false,defaultValue = "1")int page) {
		Map<String,Object> param = new HashMap<String,Object>() ;
		param.put("viewingTime", viewingTime) ;
		
		int size = 3 ;
		if (page<=0){
			page = 1;
		}
		int begin = (page-1)*size ;
		
		param.put("begin", begin) ;
		param.put("size", size) ;
	
		List<ViewingTime> viewingTimes = viewingTimeDao.ViewingTimes(param);
		model.addAttribute("viewingTimes",viewingTimes) ;
		
		int pages = 0 ;
		int recordCount = viewingTimeDao.CountViewingTimes(param);
		
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
		return "admin/viewingTime/queryViewingTime";
	}
	//查询批次信息
	@RequestMapping("/admin/showUpdateViewingTime")
	public String showUpdateViewingTime(int viewingTimeId,Model model) {
		ViewingTime viewingTime = viewingTimeDao.getViewingTime(viewingTimeId);
		if(viewingTime!=null) {
			model.addAttribute("viewingTime", viewingTime);
			return "admin/viewingTime/updateViewingTime";
		}else {
			return "error";
		}
	}
	//修改批次信息
	@RequestMapping("/admin/updateViewingTime")
	public String updateViewingTime(ViewingTime viewingTime ) {
		boolean b = viewingTimeDao.updateViewingTime(viewingTime);
		if(b) {
			return "redirect:/admin/selectViewingTime";
		}else {
			return "error";
		}
	}
	//删除批次信息
	@RequestMapping("/admin/deleteViewingTime")
	public String deleteViewingTime(int viewingTimeId) {
		boolean b = viewingTimeDao.delete(viewingTimeId);
		if(b) {
			return "redirect:/admin/selectViewingTime";
		}else {
			return "error";
		}
	}
}
