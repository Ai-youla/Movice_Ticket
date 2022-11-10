package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.FilmType;
import org.springframework.stereotype.Component;

import java.util.List;


public interface FilmTypeDAO {
/**
 * 查询所有电影类型
 * @return
 */
	public List<FilmType> list();
}
