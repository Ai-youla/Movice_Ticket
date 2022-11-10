package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.FilmPriceDAO;
import com.example.movice_ticket.mapper.FilmPriceMapper;
import com.example.movice_ticket.model.FilmPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FilmPriceDAOImpl implements FilmPriceDAO {

	@Autowired
	private FilmPriceMapper filmPriceMapper;
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int createFilmPrice(FilmPrice filmPrice) {
		int b =filmPriceMapper.createFilmPrice(filmPrice);
		int filmPriceId =0;
		if(b>0) {
			filmPriceId = filmPrice.getFilmPriceId();
		}
		return filmPriceId;
	}

	@Override
	public List<FilmPrice> list() {
		
		return filmPriceMapper.list();
	}

	@Override
	public FilmPrice queryById(int filmPriceId) {
		
		return filmPriceMapper.queryById(filmPriceId);
	}

	@Override
	public boolean updateFilmPrice(FilmPrice filmPrice) {
		
		return filmPriceMapper.updateFilmPrice(filmPrice)>0;
	}

	/*
	 * @Override public boolean updateNumber(Map<String, Object> map) {
	 * 
	 * return filmPriceMapper.updateNumber(map)>0; }
	 * 
	 * @Override public boolean reduce(Map<String, Object> map) {
	 * 
	 * return filmPriceMapper.reduce(map)>0; }
	 * 
	 * @Override public boolean add(Map<String, Object> map) {
	 * 
	 * return filmPriceMapper.add(map)>0; }
	 */

}
