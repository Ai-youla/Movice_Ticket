package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.FilmTypeDAO;
import com.example.movice_ticket.mapper.FilmTypeMapper;
import com.example.movice_ticket.model.FilmType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmTypeDAOImplement implements FilmTypeDAO
{

	@Autowired
	private FilmTypeMapper filmTypeMapper;
	@Override
	public List<FilmType> list() {
		 
		return filmTypeMapper.list();
	}

}
