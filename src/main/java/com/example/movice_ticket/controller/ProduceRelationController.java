package com.example.movice_ticket.controller;


import com.example.movice_ticket.dao.ProduceDAO;
import com.example.movice_ticket.dao.ProduceRelationDAO;
import com.example.movice_ticket.dao.VipMemberDAO;
import com.example.movice_ticket.model.Produce;
import com.example.movice_ticket.model.ProduceRelation;
import com.example.movice_ticket.model.VipMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* vip商品管理
* */
@Controller
public class ProduceRelationController {

	@Autowired
	private ProduceRelationDAO produceRelationDao;
	
	@Autowired
	private VipMemberDAO vipmemberDao;
	
	@Autowired
	private ProduceDAO produceDao;
	
	 
	//VIP批量绑定商品
	@RequestMapping("/admin/createProduceRelation")
	public String CreateProduceRelation(int  vipId,Model model,HttpServletRequest request) {
		String producestr[] = request.getParameterValues("produceId");
		ProduceRelation[] produceRelations= null;
		int uvip =1;
		if(vipId>0) {
			uvip = vipId;
		}
			if(producestr!=null) {
				produceRelations = new ProduceRelation[producestr.length];
				for(int i = 0 ; i< producestr.length ; i++) {
					int produceId = Integer.parseInt(producestr[i]) ;
					
					ProduceRelation produceRelation = new ProduceRelation(0, uvip, produceId);
					produceRelations[i]=produceRelation;
				}
			}
		
		boolean b = produceRelationDao.createProduceRelation(produceRelations);
		if(b) {
			return "redirect:/admin/showProduceRelation?vipId="+vipId;
		}else {
			return "error";
		}	
		
	}
	 //重新绑定商品
	@RequestMapping("/admin/updateProduceRelation")
	public String updateProduceRelation(int  vipId,Model model,HttpServletRequest request) {
		String produceRelationstr[] = request.getParameterValues("produceRelationId");
		ProduceRelation[] produceRelations= null;
		int uvip =1;
		if(vipId>0) {
			uvip = vipId;
		}
		if(produceRelationstr!=null) {
			produceRelations = new ProduceRelation[produceRelationstr.length];
			for(int i = 0 ; i< produceRelationstr.length ; i++) {
				int produceRelationId = Integer.parseInt(produceRelationstr[i]) ;
				ProduceRelation produceRelation = new ProduceRelation(produceRelationId, uvip, 0);
				produceRelations[i]=produceRelation;
			}
		}
		boolean b = produceRelationDao.assignProduce(produceRelations);
		if(b) {
			return "redirect:/admin/showProduceRelation?vipId="+vipId;
		}
		return "error";
	}


	//分页查询VIP会员和商品
	@RequestMapping("/admin/selectProduceRelation")
	public String selectProduceRelation(@RequestParam(name="page",required = false,defaultValue = "1")int page,
			@RequestParam(name="vipId",required=false,defaultValue="1")int vipId ,Model model) {
		 
		List<VipMember> vipmembers = vipmemberDao.ListVIP_Member();
		model.addAttribute("vipmembers", vipmembers);
		Map<String, Object>param = new HashMap<String, Object>();
		
		int size = 3;
		if (page<=0){
			page = 1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);
		List<Produce> produces = produceDao.produceList(param);
		
		model.addAttribute("produces", produces);
		int pages = 0;
		int recoredCount = produceDao.produceConutList(param);
		if(recoredCount % size ==0) {
			pages = recoredCount / size;
			if(pages==0) {
				pages = 1;
			}
		}else {
			pages = recoredCount / size + 1;
		}
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		return "admin/produce/createProduceRelation";
	}
	//分页查询已绑定的VIP回员和商品
	@RequestMapping("/admin/showProduceRelation")
	public String showProduceRelation(@RequestParam(name="page",required = false,defaultValue = "1")int page,
			@RequestParam(name="vipId",required=false,defaultValue="1")int vipId ,Model model) {
		List<VipMember> vipmembers = vipmemberDao.ListVIP_Member();
		model.addAttribute("vipmembers", vipmembers);
		Map<String, Object>param = new HashMap<String, Object>();
		int size = 3;
		if (page<=0){
			page = 1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);

		param.put("vipId", vipId);
		List<ProduceRelation> produceRelations = produceRelationDao.getProduce(param);
		 
		model.addAttribute("produceRelations", produceRelations);
		int pages = 0;
		int recoredCount = produceRelationDao.getProduceConut(vipId);
		if(recoredCount % size ==0) {
			pages = recoredCount / size;
			if(pages==0) {
				pages = 1;
			}
		}else {
			pages = recoredCount / size + 1;
		}
		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		model.addAttribute("vipId", vipId);
		
		return "admin/produce/showProduceRelation";
	}
}
