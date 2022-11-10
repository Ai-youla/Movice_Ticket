package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.PlayRelationDAO;
import com.example.movice_ticket.mapper.PlayRelationMapper;
import com.example.movice_ticket.model.PlayRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PlayRelationDAOImpl implements PlayRelationDAO
{

	@Autowired
	private PlayRelationMapper playRelationMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean createPlayRelation(PlayRelation[] playRelation) {
		boolean b = true;
		boolean success = true;
		int s  =0;
		 for(PlayRelation playRelations : playRelation) {
			 int filmId = playRelations.getFilmId();
			 List<PlayRelation> listPlayRelation = playRelationMapper.getParticipants(filmId);
			 for(PlayRelation lists :listPlayRelation) {
				 int ParticipantsId = lists.getParticipantsId();
				 if(ParticipantsId==playRelations.getParticipantsId()) {
					 s =ParticipantsId; 
				 }
			 }
			 if(s==playRelations.getParticipantsId()) {
				 success = playRelationMapper.createPlayRelation(playRelations);
				 playRelationMapper.deletePlayRelation(playRelations.getPlayRelationId());
			 }else {
				 success = playRelationMapper.createPlayRelation(playRelations);
			 }
			   
			 b = b && success;
		 }
		return b;
	}

	 

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean assignPlayRelation(PlayRelation[] playRelation) {
		  
		 boolean result = true ;
		 if ( playRelation != null ) {
		    for(PlayRelation playRelations:playRelation) {
				boolean success = playRelationMapper.deletePlayRelation(playRelations.getPlayRelationId());
				result = result && success ;
		    }
		 }
		return result;
	}


	@Override
	public List<PlayRelation> getParticipants(int filmId) {
		 
		return playRelationMapper.getParticipants(filmId);
	}

 

}
