package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.province_city_countyDAO;
import com.example.movice_ticket.mapper.province_city_county_mapper;
import com.example.movice_ticket.model.province_city_county;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class province_city_countyDAOImpl implements province_city_countyDAO {

	@Autowired
	private province_city_county_mapper province_city_countyMapper;
	
	@Override
	public List<province_city_county> list(int type) {
		 
		return province_city_countyMapper.list(type);
	}

	@Override
	public List<province_city_county> getNextAddress(Map<String, Object> param) {
		
		return province_city_countyMapper.getNextAddress(param);
	}

	@Override
	public province_city_county byName(String name) {
		
		return province_city_countyMapper.byName(name);
	}

}
