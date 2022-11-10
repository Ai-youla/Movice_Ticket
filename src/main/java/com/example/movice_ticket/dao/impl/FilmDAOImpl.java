package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.FilmDAO;
import com.example.movice_ticket.mapper.FilmMapper;
import com.example.movice_ticket.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FilmDAOImpl implements FilmDAO {

	@Autowired
	private FilmMapper filmMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int createFilm(Film films) {
		 
			boolean success = filmMapper.createFilm(films) ;
		 int filmId = 0;
			if(success) {
				filmId = films.getFilmId();
			}
		return filmId;
		 
	}

	@Override
	public boolean updateFilm(Film film) {
		 
		return filmMapper.updateFilm(film);
	}

	@Override
	public boolean updateFilmPrice(Map<String, Object> map) {
		
		return filmMapper.updateFilmPrice(map);
	}


	@Override
	public Film getFilmById(int filmId) {
		
		return filmMapper.getFilmById(filmId);
	}

	@Override
	public List<Film> queryFilm(Map<String, Object> paramMap) {
		
		return filmMapper.queryFilm(paramMap);
	}

	@Override
	public int queryCountFilm(Map<String, Object> paramMap) {
		
		return filmMapper.queryCountFilm(paramMap);
	}

	@Override
	public boolean deleteFilm(int filmId) {
		 
		return filmMapper.deleteFilm(filmId);
	}

	@Override
	public boolean updateScore(Film film) {
		return filmMapper.updateScore(film);
	}


}
