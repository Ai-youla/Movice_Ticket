package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.FilmReviewDAO;
import com.example.movice_ticket.mapper.FilmReviewMapper;
import com.example.movice_ticket.model.FilmReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FilmReviewDAOImpl implements FilmReviewDAO {

	@Autowired
	private FilmReviewMapper filmMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean createFilmReview(FilmReview filmReview) {
		
		return filmMapper.createFilmReview(filmReview);
	}

	@Override
	public boolean updateFilmReview(Map<String, Object> param) {
		
		return filmMapper.updateFilmReview(param);
	}

	@Override
	public boolean addlikeNum(Map<String, Object> param) {
		
		return filmMapper.addlikeNum(param);
	}

	@Override
	public boolean reducelikeNum(Map<String, Object> param) {
		
		return filmMapper.reducelikeNum(param);
	}

	@Override
	public boolean addReplyNum(Map<String, Object> param) {
		
		return filmMapper.addReplyNum(param);
	}

	@Override
	public boolean reduceReplyNum(Map<String, Object> param) {
		
		return filmMapper.reduceReplyNum(param);
	}

	@Override
	public FilmReview byfilmReviewId(int filmReviewId) {
		
		return filmMapper.byfilmReviewId(filmReviewId);
	}

	@Override
	public boolean delete(int filmReviewId) {
		
		return filmMapper.delete(filmReviewId);
	}

	@Override
	public List<FilmReview> queryList(Map<String, Object> param) {
		 
		return filmMapper.queryList(param);
	}

	@Override
	public int querycountList(Map<String, Object> param) {
		 
		return filmMapper.querycountList(param);
	}

}
