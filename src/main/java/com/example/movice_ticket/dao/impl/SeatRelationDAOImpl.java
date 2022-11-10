package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.SeatRelationDAO;
import com.example.movice_ticket.mapper.SeatRelationMapper;
import com.example.movice_ticket.model.SeatRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatRelationDAOImpl implements SeatRelationDAO {

	@Autowired
	private SeatRelationMapper seatRelationMapper;

	@Override
	public boolean createSeatRelation(SeatRelation seatRelation) {
		 
		return seatRelationMapper.createSeatRelation(seatRelation);
	}

	@Override
	public List<SeatRelation> getSeat(int cinemaId) {
		
		return seatRelationMapper.getSeat(cinemaId);
	}

	@Override
	public boolean deleteSeatRelation(int seatRelationId) {
		 
		return seatRelationMapper.deleteSeatRelation(seatRelationId);
	}
	
	
}
