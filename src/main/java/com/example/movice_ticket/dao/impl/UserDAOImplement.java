package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.UserDAO;
import com.example.movice_ticket.mapper.UserMapper;
import com.example.movice_ticket.mapper.VipMemberMapper;
import com.example.movice_ticket.model.User;
import com.example.movice_ticket.model.VipMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class UserDAOImplement implements UserDAO {

	@Autowired
	private UserMapper usermapper;
	
	@Autowired
	private VipMemberMapper vip_memberMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean regist(User user) {
		boolean b = usermapper.regist(user)>0;
		if(b) {
			int userId = user.getUserId();
			VipMember vip_member = new VipMember( 0, 0, 0, userId);
			boolean success = vip_memberMapper.createVIP_Member(vip_member);
			b = b && success;
		}
		return b ;
	}

	@Override
	public User login(String userName) {
		 
		return usermapper.login(userName);
	}

	@Override
	public User query(int userId) {
		
		return usermapper.query(userId);
	}

	@Override
	public boolean updateUser(User user) {
		
		return usermapper.updateUser(user);
	}

	@Override
	public boolean updatePicture(User user) {
		
		return usermapper.updatePicture(user);
	}

	@Override
	public List<User> queryUser(Map<String, Object> map) {
		
		return usermapper.queryUser(map);
	}

	@Override
	public int queryCountUser(Map<String, Object> map) {
		 
		return usermapper.queryCountUser(map);
	}

	@Override
	public boolean deleteUser(int userId) {
		 
		return usermapper.deleteUser(userId);
	}

	
}
