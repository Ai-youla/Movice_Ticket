package com.example.movice_ticket.controller;


import com.example.movice_ticket.dao.FilmDAO;
import com.example.movice_ticket.dao.ParticipantsDAO;
import com.example.movice_ticket.dao.PlayRelationDAO;
import com.example.movice_ticket.model.Film;
import com.example.movice_ticket.model.Participants;
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
* 参演人员信息
* */
@Controller
public class ParticipansController {

	@Autowired
	private ParticipantsDAO participantsDao;
	
	@Autowired
	private FilmDAO filmDao;
	
	@Autowired
	private PlayRelationDAO playRelationDao;
	
	@RequestMapping("/admin/toCreatePerson")
	public String toPerson() { 
		return "admin/film/createPerson";
	}
	
	//创建参演人员
		@RequestMapping("/admin/createPerson")
		public String createPerson(Participants participants, MultipartFile headFile) {
			String fileName = headFile.getOriginalFilename();
			String path = "F:\\mihayo\\movie\\playPicture\\";
			String uuid = UUID.randomUUID().toString();
			fileName = fileName + uuid;

			File file = new File(path + fileName);
			try {
				headFile.transferTo(file);
				participants.setPriture(fileName);
				boolean b = participantsDao.createParticipants(participants);

				 if (b) {
						path= "redirect:/admin/selectPerson";
					} else {
						path= "error";
					}
			} catch (IllegalStateException e) {
				e.printStackTrace();
				path = "/";
			} catch (IOException e) {
				path = "/";
				e.printStackTrace();
			}
			 
			return path;
		}
		//分页查询所有的参演人员信息
		@RequestMapping("/admin/selectPerson")
		public String SelectParticipants(@RequestParam(name="page",required = false, defaultValue = "1")int page,Participants participants,Model model) {
			Map<String, Object>param = new HashMap<String, Object>();
			param.put("participants", participants);
			int size = 3;
			if (page<=0){
				page = 1;
			}
			int begin = (page - 1) * size;
			param.put("size", size);
			param.put("begin", begin);
			
			List<Participants> listParticipants = participantsDao.list(param);
			model.addAttribute("listParticipants", listParticipants);
			 
			int pages =0;
			int recordcount = participantsDao.conutlist(param);
			if(recordcount % size ==0) {
				pages = recordcount / size;
				if(pages==0) {
					pages =1;
				}
			}else {
				pages = recordcount / size +1;
			}

			model.addAttribute("pages",pages);
			model.addAttribute("page",page);

			return "admin/film/showPerson";	
		}
		//查询参演人员信息
		@RequestMapping("/admin/showUpdatePerson")
		public String showUpdatePerson(int ParticipantsId ,Model model) {
			Participants participants = participantsDao.getParticipantsById(ParticipantsId);
			if(participants!=null) {
				model.addAttribute("participants", participants);
				return "admin/film/showUpdatePerson";
			}else {
				return "error";
			}	
		}
		//修改参演人员信息
		@RequestMapping("/admin/updatePerson")
		public String UpdatePerson(Participants participants ,Model model) {
			boolean b =  participantsDao.updateParticipants(participants);
			if(b) {
				 
				return "redirect:/admin/selectPerson";
			}else {
				return "error";
			}		
		}
		//删除人员信息
		@RequestMapping("/admin/deletePerson")
		public String deletePerson(int ParticipantsId) {
			boolean b =  participantsDao.delete(ParticipantsId);
			if(b) { 
				return "redirect:/admin/selectPerson";
			}else {
				return "error";
			}	
		}
		//修改人员头像
		@RequestMapping("/admin/updateParticipantsPicture")
		public String updateParticipantsPicture(int  ParticipantsId ,Model model,MultipartFile participantspicture ) {
			String fileName = participantspicture.getOriginalFilename();
			String path = "F:\\mihayo\\movie\\playPicture\\";
			String uuid = UUID.randomUUID().toString();
			fileName = fileName + uuid;

			File file = new File(path + fileName);
			Participants participants =null;
			try {
				participantspicture.transferTo(file);				  
				participants = new Participants(ParticipantsId, null, fileName,null , null);
				boolean b =  participantsDao.updatePriture(participants);
				if(b) {
					return "redirect:/admin/showUpdatePerson?ParticipantsId="+ ParticipantsId;
				}else {
					return "error";
				}
			} catch (IllegalStateException | IOException e) {	 
				e.printStackTrace();
				return "error";
			}		
		}
		//分页查询所有参演人员信息
		@RequestMapping("/admin/toCreatePlayRelation")
		public String toCreatePlayRelation(@RequestParam(name="page",required = false, defaultValue = "1")int page,Model model) {
			Map<String, Object>param = new HashMap<String, Object>();
			 
			int size = 3;
			if (page<=0){
				page = 1;
			}
			int begin = (page - 1) * size;
			param.put("size", size);
			param.put("begin", begin);
			
			List<Participants> listParticipants = participantsDao.list(param);
			 model.addAttribute("listParticipants", listParticipants);
			 
			int pages =0;
			int recordcount = participantsDao.conutlist(param);
			if(recordcount % size ==0) {
				pages = recordcount / size;
				if(pages==0) {
					pages =1;
				}
			}else {
				pages = recordcount / size +1;
			}

			model.addAttribute("pages",pages);
			model.addAttribute("page",page);
			param.clear();
			List<Film> films = filmDao.queryFilm(param);
			 
			
			model.addAttribute("films",films);
			
			return "admin/film/createPlayRelation";	
		}
		//批量绑定参演人员
		@RequestMapping("/admin/createPlayRelation")
		public String createPlayRelation(PlayRelation playRelation, Model model,HttpServletRequest request) {
			String[] strParticipants = request.getParameterValues("ParticipantsId");
			int filmId = playRelation.getFilmId();
			PlayRelation[] playRelations = null;
			if(strParticipants!=null ) {
				playRelations = new PlayRelation[strParticipants.length];
				for(int i =0; i<strParticipants.length; i++) {
					int ParticipantsId = Integer.parseInt(strParticipants[i]);
					 playRelation = new PlayRelation(0,ParticipantsId, filmId);
					playRelations[i]=playRelation;
				}
			}
			boolean b = playRelationDao.createPlayRelation(playRelations);
			if(b) {//查询电影信息
				return "redirect:/admin/showUpdatefilm?filmId="+filmId;
			}else {
				return "error";
			}
			
		}
		
}
