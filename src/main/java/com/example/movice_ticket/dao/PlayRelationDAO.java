package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.PlayRelation;
import org.springframework.stereotype.Component;

import java.util.List;


public interface PlayRelationDAO {

	/**
	 * 电影批量绑定参演人员
	 * @param playRelation
	 * @return
	 */
	public boolean createPlayRelation(PlayRelation[] playRelation) ;
	
	/**
	 * 批量修改绑定的人员
	 * @param playRelation
	 * @return
	 */
	public boolean assignPlayRelation(PlayRelation[] playRelation) ;
	/**
	 * 通过filmId查询该电影的所有参演人员
	 * @param filmId
	 * @return
	 */
	public List<PlayRelation> getParticipants(int filmId) ;
}
