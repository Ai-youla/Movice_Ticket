package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.CinemaDAO;
import com.example.movice_ticket.mapper.CinemaMapper;
import com.example.movice_ticket.model.Cinema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
@Service
public class CinemaDAOImpl implements CinemaDAO {

	@Autowired
	private CinemaMapper cinemaMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean createCinema(Cinema cinema) {
		 
		return cinemaMapper.createCinema(cinema);
	}

	@Override
	public boolean updateCinema(Cinema cinema) {
		 
		return cinemaMapper.updateCinema(cinema);
	}

	@Override
	public List<Cinema> cinemaList(Map<String, Object> param) {
		
		return cinemaMapper.cinemaList(param);
	}

	@Override
	public int cinemaCountList(Map<String, Object> param) {
		
		return cinemaMapper.cinemaCountList(param);
	}

	@Override
	public Cinema getCinemaById(int cinemaId) {
		 
		return cinemaMapper.getCinemaById(cinemaId);
	}

	/*@Override
	public List<Cinema> cinemas(int filmId) {
		return cinemaMapper.cinemas(filmId);
	}*/

}
