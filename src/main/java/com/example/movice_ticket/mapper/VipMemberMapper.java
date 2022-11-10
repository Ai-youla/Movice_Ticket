package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.VipMember;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface VipMemberMapper {

	@Insert("insert into vipmember(integral, amount, member, userId)value(#{integral}, #{amount}, #{member}, #{userId})")
	public boolean createVIP_Member(VipMember vipmember);
	
	@Select("select * from vipmember where userId=#{userId}")
	public VipMember getVip_member(int userId);
	
	@Update("update vipmember set integral=integral + #{integral} , amount=amount + #{amount} where userId=#{userId}")
	public boolean updateIntegral(VipMember vipmember);
	
	@Update("update vipmember set integral=integral - #{integral}   where userId=#{userId}")
	public boolean reduceIntegral(Map<String, Object>param);
	
	@Update("update vipmember set member=#{member} where userId=#{userId}")
	public boolean updateMember(Map<String, Object>param);
	
	@Select("select * from vipmember where member = 1")
	@Result(property = "user",column="userId",
	one = @One(select="com.example.movice_ticket.mapper.UserMapper.query"))
	public List<VipMember> ListVIPMember();
	
	@Select("select count(*) from vipmember where member = 1 ")
	public int conut();
}
