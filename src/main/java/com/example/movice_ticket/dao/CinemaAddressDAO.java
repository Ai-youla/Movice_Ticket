package com.example.movice_ticket.dao;

 

import com.example.movice_ticket.model.CinemaAddress;
import org.springframework.stereotype.Component;


public interface CinemaAddressDAO {

	/**
	 * 创建地址信息
	 * @param cinemaAddress
	 * @return
	 */
	public int createCinemaAddress(CinemaAddress cinemaAddress);
	/**
	 * 通过地址Id查讯地址信息
	 * @param cinemaAddressId
	 * @return
	 */
 	public CinemaAddress getCinemaAddressById(int cinemaAddressId);
	/**
	 * 修改地址信息
	 * @param cinemaAddress
	 * @return
	 */
 	public boolean updateCinemaAddress(CinemaAddress cinemaAddress);
}
