package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.ProduceDAO;
import com.example.movice_ticket.mapper.ProduceMapper;
import com.example.movice_ticket.model.Produce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProduceDAOImpl implements ProduceDAO {

	@Autowired
	private ProduceMapper produceMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean createProduce(Produce produce) {
		 
		return produceMapper.createProduce(produce);
	}

	@Override
	public boolean updateProduce(Produce produce) {
		 
		return produceMapper.updateProduce(produce);
	}

	@Override
	public boolean updateProducePricture(Map<String, Object> param) {
		 
		return produceMapper.updateProducePricture(param);
	}

	@Override
	public boolean updateState(Map<String, Object> param) {
		 
		return produceMapper.updateState(param);
	}

	@Override
	public Produce getProduceById(int produceId) {
		 
		return produceMapper.getProduceById(produceId);
	}

	@Override
	public List<Produce> produceList(Map<String, Object> param) {
		 
		return produceMapper.produceList(param);
	}

	@Override
	public int produceConutList(Map<String, Object> param) {
		 
		return produceMapper.produceConutList(param);
	}

	@Override
	public boolean reduceProduceNum(Map<String, Object> param) {
		 
		return produceMapper.reduceProduceNum(param);
	}

}
