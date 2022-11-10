package com.example.movice_ticket.dao;



import com.example.movice_ticket.model.FilmPrice;

import java.util.List;

public interface FilmPriceDAO {

	/**
	 * 创建票价
	 * @param filmPrice
	 * @return
	 */
	public int  createFilmPrice(FilmPrice filmPrice);
	
	/**
	 * 查讯所有票价
	 * @return
	 */
	public List<FilmPrice> list();
	
 /**
  * 通过Id查询票价信息
  * @param filmPriceId
  * @return
  */
	public FilmPrice queryById(int filmPriceId);
	
 /**
  * 修改票价
  * @param filmPrice
  * @return
  */
	public boolean updateFilmPrice(FilmPrice filmPrice);
	
 
	/*
	 * public boolean updateNumber(Map<String, Object> map);
	 * 
	 * public boolean reduce(Map<String, Object> map);
	 * 
	 * 
	 * public boolean add(Map<String, Object> map);
	 */
}
