package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.VipMember;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface VipMemberDAO {

	/**
	 * 创建VIP会员
	 * @param vipmember
	 * @return
	 */
	public boolean createVIP_Member(VipMember vipmember);
	/**
	 * 通过用户Id查询VIP会员信息
	 * @param userId
	 * @return
	 */
	public VipMember getVip_member(int userId);
	/**
	 * 更新积分的值
	 * @param vipmember
	 * @return
	 */
	public boolean updateIntegral(VipMember vipmember);
	/**
	 * 修改会员状态
	 * @param param
	 * @return
	 */
	public boolean updateMember(Map<String, Object>param);
	/**
	 * 查询所有VIP会员
	 * @return
	 */
	public List<VipMember> ListVIP_Member();
	/**
	 * 减少积分
	 * @param param
	 * @return
	 */
	public boolean reduceIntegral(Map<String, Object>param);
	/**
	 * 查询所有VIP会员
	 * @return
	 */
	public int conut();
}
