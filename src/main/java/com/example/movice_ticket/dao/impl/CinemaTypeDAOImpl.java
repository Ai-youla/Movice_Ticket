package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.CinemaTypeDAO;
import com.example.movice_ticket.mapper.CinemaTypeMapper;
import com.example.movice_ticket.model.CinemaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaTypeDAOImpl implements CinemaTypeDAO {

	@Autowired
	private CinemaTypeMapper cinematypeMapper;
	
	@Override
	public List<CinemaType> CinemaTypeList() {
		 
		return cinematypeMapper.CinemaTypeList();
	}

}
