package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.VipMemberDAO;
import com.example.movice_ticket.mapper.VipMemberMapper;
import com.example.movice_ticket.model.VipMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class VipMemberDAOImpl implements VipMemberDAO {

	@Autowired
	private VipMemberMapper vipmemberMapper;
	
	@Override
	public boolean createVIP_Member(VipMember vip_member) {
		
		return vipmemberMapper.createVIP_Member(vip_member);
	}

	@Override
	public VipMember getVip_member(int userId) {
		
		return vipmemberMapper.getVip_member(userId);
	}

	@Override
	public boolean updateIntegral(VipMember vip_member) {
		
		return vipmemberMapper.updateIntegral(vip_member);
	}

	@Override
	public boolean updateMember(Map<String, Object> param) {
		
		return vipmemberMapper.updateMember(param);
	}

	@Override
	public List<VipMember> ListVIP_Member() {
		
		return vipmemberMapper.ListVIPMember();
	}

	@Override
	public boolean reduceIntegral(Map<String, Object> param) {
		 
		return vipmemberMapper.reduceIntegral(param);
	}

	@Override
	public int conut() {
		 
		return vipmemberMapper.conut();
	}

}
