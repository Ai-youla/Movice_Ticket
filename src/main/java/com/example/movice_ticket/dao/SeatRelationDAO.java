package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.SeatRelation;
import org.springframework.stereotype.Component;

import java.util.List;


public interface SeatRelationDAO {
	/**
	 * 影院绑定影厅
	 * @param seatRelation
	 * @return
	 */
	public boolean createSeatRelation(SeatRelation seatRelation);
	/**
	 * 通过影院Id查询所有绑定的影院
	 * @param cinemaId
	 * @return
	 */
	public List<SeatRelation> getSeat(int cinemaId) ;
	/**
	 * 解除绑定的影厅
	 * @param seatRelationId
	 * @return
	 */
	public boolean deleteSeatRelation(int seatRelationId) ;
}
