package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.ParticipantsDAO;
import com.example.movice_ticket.mapper.ParticipantsMapper;
import com.example.movice_ticket.model.Participants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ParticipantsDAOImpl implements ParticipantsDAO {

	@Autowired
	private ParticipantsMapper participantsMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean createParticipants(Participants participants) {
		 
		return participantsMapper.createParticipants(participants);
	}

	@Override
	public boolean updateParticipants(Participants participants) {
		
		return participantsMapper.updateParticipants(participants);
	}

	@Override
	public boolean updatePriture(Participants participants) {
		
		return participantsMapper.updatePriture(participants);
	}

	@Override
	public boolean delete(int participantsId) {
		
		return participantsMapper.delete(participantsId);
	}

	@Override
	public List<Participants> list(Map<String, Object> param) {
		 
		return participantsMapper.list(param);
	}

	@Override
	public int conutlist(Map<String, Object> param) {
		 
		return participantsMapper.conutlist(param);
	}

	@Override
	public Participants getParticipantsById(int ParticipantsId) {
		 
		return participantsMapper.getParticipantsById(ParticipantsId);
	}

}
