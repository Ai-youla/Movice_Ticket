package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.CinemaType;
import org.springframework.stereotype.Component;

import java.util.List;


public interface CinemaTypeDAO {

	/**
	 * 查询所以影院类型
	 * @return
	 */
	public List<CinemaType> CinemaTypeList();
}
