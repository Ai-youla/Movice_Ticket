package com.example.movice_ticket.controller;

import com.example.movice_ticket.dao.ProduceDAO;
import com.example.movice_ticket.dao.ProduceRelationDAO;
import com.example.movice_ticket.dao.UserDAO;
import com.example.movice_ticket.dao.VipMemberDAO;
import com.example.movice_ticket.model.Produce;
import com.example.movice_ticket.model.ProduceRelation;
import com.example.movice_ticket.model.User;
import com.example.movice_ticket.model.VipMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
* 商品管理
* */
@Controller
public class ProduceController {


	@Autowired
	private ProduceDAO produceDao;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private VipMemberDAO vipMemberDAO;
	@Autowired
	private ProduceRelationDAO produceRelationDAO;

	@RequestMapping("/admin/toProduce")
	public String toProduce() {
		return "admin/produce/CreateProduce";
	}

	//创建商品
	@RequestMapping("/admin/createProduce")
	public String CreateProduce(Produce produce, MultipartFile producepicture) {
		String fileName = producepicture.getOriginalFilename();
		String path = "D:\\javaWEB\\picture\\filmPicture\\";
		String uuid = UUID.randomUUID().toString();
		fileName = fileName + uuid;

		File file = new File(path + fileName);

		try {
			producepicture.transferTo(file);
			produce.setPicture(fileName);
			boolean b = produceDao.createProduce(produce);
			if (b) {
				path = "redirect:/admin/selectProduce";
			}
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			path = "error";
		}
		return path;
	}

	//分页查询所有商品
	@RequestMapping("/admin/selectProduce")
	public String SelectProduce(Produce produce, Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("produce", produce);
		int size = 3;
		if (page <= 0) {
			page = 1;
		}
		int begin = (page - 1) * size;
		param.put("size", size);
		param.put("begin", begin);

		List<Produce> produces = produceDao.produceList(param);
		model.addAttribute("produces", produces);

		int pages = 0;
		int recoredCount = produceDao.produceConutList(param);

		if (recoredCount % size == 0) {
			pages = recoredCount / size;
			if (pages == 0) {
				pages = 1;
			}
		} else {
			pages = recoredCount / size + 1;
		}

		model.addAttribute("pages", pages);
		model.addAttribute("page", page);
		return "admin/produce/showProduce";
	}

	//查询商品跳转到修改页面
	@RequestMapping("/admin/showUpdateProduce")
	public String showUpdateProduce(int produceId, Model model) {
		Produce produce = produceDao.getProduceById(produceId);
		if (produce != null) {
			model.addAttribute("produce", produce);
			return "admin/produce/updateProduce";
		} else {
			return "redirect:/admin/selectProduce";
		}
	}

	//修改商品
	@RequestMapping("/admin/updateProduce")
	public String updateProduce(Produce produce, Model model) {
		boolean b = produceDao.updateProduce(produce);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("state", produce.getState());
		param.put("produceId", produce.getProduceId());
		boolean success = produceDao.updateState(param);
		b = b && success;
		if (b) {
			return "redirect:/admin/selectProduce";
		} else {
			return "error";
		}
	}

	//修改商品图片
	@RequestMapping("/admin/updatePicture")
	public String updatePicture(int produceId, Model model, MultipartFile producepicture) throws IllegalStateException, IOException {
		String fileName = producepicture.getOriginalFilename();
		String path = "F:\\mihayo\\movie\\filmPicture\\";
		String uuid = UUID.randomUUID().toString();
		fileName = fileName + uuid;

		File file = new File(path + fileName);

		producepicture.transferTo(file);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("picture", fileName);
		param.put("produceId", produceId);
		boolean b = produceDao.updateProducePricture(param);
		if (b) {
			return "redirect:/admin/showUpdateProduce?produceId=" + produceId;
		} else {
			return "error";
		}
	}

	//兑换商品
	@RequestMapping("/user/updateProduce")
	@ResponseBody
	public Map<String,Object> updateNum(int produceId){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		Map<String, Object> map = new HashMap<>();
		//查询出用户的信息
		User user = userDAO.login(userName);
		Produce produce = produceDao.getProduceById(produceId);
		int i =1;//每次商品减少的数量或兑换商品的数量
//		map.put("produce", produce);
		if(user!=null){
			VipMember vipMember = vipMemberDAO.getVip_member(user.getUserId());
			//是否是会员：0：非会员，1：会员
			if (vipMember.getMember()!=1){
				map.put("type", "false");
				map.put("msg", "非会员");
			}else{
				List<ProduceRelation> produceRelations = produceRelationDAO.getProducebyId(vipMember.getVipId());
				if(produceRelations==null){
					map.put("msg", "没有该商品的兑换权限！");
				}
				for (ProduceRelation produceRelation : produceRelations) {
							//System.out.println(produceRelation.getProduce().getProduceId()+"----------");
					if (produceRelation.getProduce().getProduceId() != produceId){
						map.put("type", "false");
						map.put("msg", "没有该商品的兑换权限！");
					}else {
						if(vipMember.getIntegral() >= produce.getIntegral()){
							map.put("integral",produce.getIntegral());
							map.put("userId", user.getUserId());
							//扣取积分
							boolean s = vipMemberDAO.reduceIntegral(map);
							map.put("produceId", produceId);
							map.put("produceNum", i);
							//减少商品数量
							boolean b = produceDao.reduceProduceNum(map);
							if (b){
								map.put("produceRelationId", produceRelation.getProduceRelationId());
								map.put("number", i);
								//保存已兑换的商品
								b = produceRelationDAO.updateNumber(map);
								produce = produceDao.getProduceById(produceId);//刷新兑换商品数量
								map.put("produce",produce);
							}
							map.put("type", "success");
							break;

						}else {
							map.put("type", "false");
							map.put("msg", "积分不够！");
						}
					}
				}
			}
		}
		return map;
	}
}
