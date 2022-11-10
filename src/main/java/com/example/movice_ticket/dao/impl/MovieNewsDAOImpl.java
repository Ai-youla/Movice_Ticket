package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.MovieNewsDAO;
import com.example.movice_ticket.mapper.MovieNewsMapper;
import com.example.movice_ticket.model.MovieNews;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MovieNewsDAOImpl implements MovieNewsDAO {

	@Autowired
	private MovieNewsMapper movieNewsMapper;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean createMovieNews(MovieNews movieNews) {
		 
		return movieNewsMapper.createMovieNews(movieNews);
	}

	@Override
	public MovieNews getMovieNewsbyId(int movieNewsId) {
		
		return movieNewsMapper.getMovieNewsbyId(movieNewsId);
	}

	@Override
	public boolean updateMovieNews(MovieNews movieNews) {
		
		return movieNewsMapper.updateMovieNews(movieNews);
	}

	@Override
	public boolean deleteMovieNews(int movieNewsId) {
		
		return movieNewsMapper.deleteMovieNews(movieNewsId);
	}

	@Override
	public boolean updateNewspicture(Map<String, Object> param) {
		 
		return movieNewsMapper.updateNewspicture(param);
	}

	@Override
	public List<MovieNews> list(Map<String, Object> paramMap) {
		 
		return movieNewsMapper.list(paramMap);
	}

	@Override
	public int listcount(Map<String, Object> paramMap) {
		 
		return movieNewsMapper.listcount(paramMap);
	}

}
