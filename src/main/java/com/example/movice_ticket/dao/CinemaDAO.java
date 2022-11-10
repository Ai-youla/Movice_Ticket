package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.Cinema;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface CinemaDAO {
	/**
	 * 创建影院
	 * @param cinema 实体类
	 * @return
	 */
	public boolean createCinema(Cinema cinema);
	/**
	 * 修改影院信息
	 * @param cinema
	 * @return
	 */
 	public boolean updateCinema(Cinema cinema);
	/**
	 * 分页查询所有影院信息
	 * @param param
	 * @return
	 */
 	public List<Cinema> cinemaList(Map<String,Object>param);
	/**
	 * 统计全部的影院个数
	 * @param param
	 * @return
	 */
 	public int cinemaCountList(Map<String,Object>param);
 	/**
 	 * 通过Id查讯影院信息
 	 * @param cinemaId
 	 * @return
 	 */
 	public Cinema getCinemaById(int cinemaId);

	/**
	 * 查询有某个电影的全部电院
	 * @param filmId
	 * @return
	 *//*
	public List<Cinema> cinemas (int filmId);*/
}
