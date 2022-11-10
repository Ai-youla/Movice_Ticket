package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.FilmReview;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface FilmReviewDAO {

	/**
	 * 创建影评
	 * @param filmReview
	 * @return
	 */
	public boolean createFilmReview(FilmReview filmReview);
	/**
	 * 修改影评
	 * @param param
	 * @return
	 */
	public boolean updateFilmReview(Map<String, Object>param);
	
	 /**
	  * 点赞数增加
	  * @param param
	  * @return
	  */
	public boolean addlikeNum(Map<String, Object>param);
	
 /**
  * 点赞数减少
  * @param param
  * @return
  */
	public boolean reducelikeNum(Map<String, Object>param);
	
 /**
  * 回复数增加
  * @param param
  * @return
  */
	public boolean addReplyNum(Map<String, Object>param);
	
 /**
  * 回复数减少
  * @param param
  * @return
  */
	public boolean reduceReplyNum(Map<String, Object>param);
	
 /**
  * 分页查询所有影评
  * @param param
  * @return
  */
	public List<FilmReview> queryList(Map<String, Object>param);
	/**
	 * 影评个数
	 * @param param
	 * @return
	 */
	public int querycountList(Map<String, Object>param);
	/**
	 * 通过Id查询影评信息
	 * @param filmReviewId
	 * @return
	 */
	public FilmReview byfilmReviewId(int filmReviewId);
	
	 /**
	  * 通过Id删除影评
	  * @param filmReviewId
	  * @return
	  */
	public boolean delete(int filmReviewId);
}
