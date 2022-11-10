package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.CinemaAddressDAO;
import com.example.movice_ticket.mapper.CinemaAddressMapper;
import com.example.movice_ticket.model.CinemaAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CinemaAddressDAOImpl implements CinemaAddressDAO {

	@Autowired
	private CinemaAddressMapper cinemaAddressMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int createCinemaAddress(CinemaAddress cinemaAddress) {
		int b = cinemaAddressMapper.createCinemaAddress(cinemaAddress);
		int cinemaAddressId =0;
		if(b>0) {
			cinemaAddressId = cinemaAddress.getCinemaAddressId();
		}
		
		return cinemaAddressId;
	}

	@Override
	public CinemaAddress getCinemaAddressById(int cinemaAddressId) {
		
		return cinemaAddressMapper.getCinemaAddressById(cinemaAddressId);
	}

	@Override
	public boolean updateCinemaAddress(CinemaAddress cinemaAddress) {
		
		return cinemaAddressMapper.updateCinemaAddress(cinemaAddress);
	}

}
