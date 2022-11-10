package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.MovieNews;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface MovieNewsDAO {
	/**
	 * 创建影讯
	 * @param movieNews
	 * @return
	 */
	public boolean createMovieNews(MovieNews movieNews);
	 /**
	  * 分页查询所有影评
	  * @param paramMap
	  * @return
	  */
	public List<MovieNews> list(Map<String, Object>paramMap);
	/**
	 * 影讯个数
	 * @param paramMap
	 * @return
	 */
    public int listcount(Map<String, Object>paramMap);
	 /**
	  * 通过Id查询影讯信息
	  * @param movieNewsId
	  * @return
	  */
	public MovieNews getMovieNewsbyId(int movieNewsId);
	/**
	 * 修改影讯信息
	 * @param movieNews
	 * @return
	 */
	public boolean updateMovieNews(MovieNews movieNews);
	/**
	 * 修改影讯海报
	 * @param param
	 * @return
	 */
	public boolean updateNewspicture(Map<String, Object>param);
	/**
	 * 下架后删除影讯
	 * @param movieNewsId
	 * @return
	 */
	public boolean deleteMovieNews(int movieNewsId);
}
