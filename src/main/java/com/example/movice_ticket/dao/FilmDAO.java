package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.Film;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface FilmDAO {

	/**
	 * 创建电影信息
	 * @param films
	 * @return
	 */
	public int createFilm(Film films);
	/**
	 * 修改电影信息
	 * @param film
	 * @return
	 */
	public boolean updateFilm(Film film);
	/**
	 * 修改电影价格
	 * @param map
	 * @return
	 */
	public boolean updateFilmPrice(Map<String, Object>map);

	/**
	 * 通过Id查询电影信息
	 * @param filmId
	 * @return
	 */
	public Film getFilmById(int filmId);
 
	/**
	 * 通过分页查询所有的电影
	 * @param paramMap
	 * @return
	 */
	public List<Film> queryFilm(Map<String, Object> paramMap);
	/**
	 * 所有的电影数量
	 * @param paramMap
	 * @return
	 */
	public int queryCountFilm(Map<String, Object> paramMap);
	/**
	 * 删除下架的电影
	 * @param filmId
	 * @return
	 */
	public boolean deleteFilm(int filmId);

	/**
	 * 修改评分
	 * @param film
	 * @return
	 */
	public boolean updateScore(Film film);
}
