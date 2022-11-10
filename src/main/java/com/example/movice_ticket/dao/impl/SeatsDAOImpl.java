package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.SeatsDAO;
import com.example.movice_ticket.mapper.SeatsMapper;
import com.example.movice_ticket.model.Seats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeatsDAOImpl implements SeatsDAO {

	@Autowired
	private SeatsMapper seatsMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int creatSeats(Seats seats) {
		boolean b = seatsMapper.creatSeats(seats);
		int seatId = 0;
		if(b) {
			seatId = seats.getSeatId();
		}
		return  seatId;
	}

	@Override
	public Seats getSeats(int seatId) {
		 
		return seatsMapper.getSeats(seatId);
	}

	@Override
	public boolean updateSeat(Seats seats) {
		 
		return seatsMapper.updateSeat(seats);
	}

	@Override
	public boolean delete(int seatId) {
		 
		return seatsMapper.delete(seatId);
	}

	
}
