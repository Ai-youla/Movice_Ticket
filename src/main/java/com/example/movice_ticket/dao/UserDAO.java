package com.example.movice_ticket.dao;

import com.example.movice_ticket.model.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface UserDAO {

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	public boolean regist(User user);
	/**
	 * 登录
	 * @param userName
	 * @return
	 */
	public User login(String userName);
	/**
	 * 通过Id查询用户信息
	 * @param userId
	 * @return
	 */
	public User query(int userId);
	/**
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	public boolean updateUser(User user);
	/**
	 * 修改用户头像
	 * @param user
	 * @return
	 */
	public boolean updatePicture(User user);
	/**
	 * 分页查询所有用户
	 * @param map
	 * @return
	 */
	public List<User> queryUser(Map<String, Object>map);
	/**
	 * 所有用户的个数
	 * @param map
	 * @return
	 */
	public int queryCountUser(Map<String, Object>map);
	/**
	 * 通过Id删除用户
	 * @param userId
	 * @return
	 */
	public boolean deleteUser(int userId);
}
