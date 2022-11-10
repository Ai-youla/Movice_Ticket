package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.ViewingTimeDAO;
import com.example.movice_ticket.mapper.ViewingTimeMapper;
import com.example.movice_ticket.model.ViewingTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ViewingTimeDAOImpl implements ViewingTimeDAO {

	@Autowired
	private ViewingTimeMapper viewingTimeMapper;
	
	@Override
	public boolean createViewingTime(ViewingTime viewingTime) {
		
		return viewingTimeMapper.createViewingTime(viewingTime);
	}

	 
	@Override
	public boolean updateViewingTime(ViewingTime viewingTime) {
		
		return viewingTimeMapper.updateViewingTime(viewingTime);
	}

	@Override
	public ViewingTime getViewingTime(int viewingTimeId) {
		
		return viewingTimeMapper.getViewingTime(viewingTimeId);
	}

	@Override
	public List<ViewingTime> ViewingTimes(Map<String, Object> param) {
		
		return viewingTimeMapper.ViewingTimes(param);
	}

	@Override
	public int CountViewingTimes(Map<String, Object> param) {
		
		return viewingTimeMapper.CountViewingTimes(param);
	}

	@Override
	public boolean delete(int viewingTimeId) {
		
		return viewingTimeMapper.delete(viewingTimeId);
	}

}
