package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
@Mapper
public interface UserMapper {

	@Insert("insert into user(userName, userPassword, picture, address, email,"
			+  "phone, userRole)value(#{userName},#{userPassword},#{picture},#{address},#{email},#{phone},'ROLE_USER')")
	@Options(useGeneratedKeys = true, keyColumn = "userId", keyProperty = "userId")
	public int regist(User user);
	
	@Select("select * from user where userName=#{userName}")
	public User login(String userName);
	
	@Select("select * from user where userId=#{userId}")
	public User query(int userId);
	
	@Update("update user set userName=#{userName}, userPassword=#{userPassword}, address=#{address}, email=#{email},"
			+ "phone=#{phone} where userId = #{userId}")
	public boolean updateUser(User user);
	
	@Delete("delete from user where userId=#{userId}")
	public boolean deleteUser(int userId);
	
	@Update("update user set  picture=#{picture} where userId = #{userId}")
	public boolean updatePicture(User user);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.UserBuilder.class,method="generateSQL")
	public List<User> queryUser(Map<String, Object>map);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.UserBuilder.class,method="generateCountSQL")
	public int queryCountUser(Map<String, Object>map);
}
