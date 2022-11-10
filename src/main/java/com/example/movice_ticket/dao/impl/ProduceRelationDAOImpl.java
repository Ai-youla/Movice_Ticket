

package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.ProduceRelationDAO;
import com.example.movice_ticket.mapper.ProduceRelationMapper;
import com.example.movice_ticket.model.ProduceRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProduceRelationDAOImpl implements ProduceRelationDAO {

	@Autowired
	private ProduceRelationMapper produceRelationMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean createProduceRelation(ProduceRelation[] produceRelations) {
		boolean b = true;
		boolean success =true;
		int s = 0;
			if(produceRelations!=null) {
				 for(ProduceRelation produceRelation : produceRelations) {
					 
					 int vipId = produceRelation.getVipId();
					 
					List<ProduceRelation> produceRelationList =  produceRelationMapper.getProducebyId(vipId);
					 int produceId=0;
					 for(ProduceRelation uproduceRelation :produceRelationList) {						 
						produceId = uproduceRelation.getProduceId(); 
						if(produceId==produceRelation.getProduceId()) {	 
							s=produceId; 
						}
					 }
					 if(s==produceRelation.getProduceId()) {
						 success = produceRelationMapper.createProduceRelation(produceRelation);
							int produceRelationId = produceRelation.getProduceRelationId();
							produceRelationMapper.delete(produceRelationId);
					 }else {
						  success = produceRelationMapper.createProduceRelation(produceRelation);
					 }
					b = success && b;
				 }
			}

		return b;
	}


	@Override
	public boolean assignProduce(ProduceRelation[] produceRelations) {
		boolean b=true;
		
		if(produceRelations!=null) {
			 for(ProduceRelation produceRelation : produceRelations) {
				 int produceRelationId =produceRelation.getProduceRelationId(); 
				 b = produceRelationMapper.delete(produceRelationId);
			 }
		}
		return b;
	}

	@Override
	public List<ProduceRelation> getProduce(Map<String, Object> param) {
		
		return produceRelationMapper.getProduce(param);
	}

	@Override
	public int getProduceConut(int vipId) {
		
		return produceRelationMapper.getProduceConut(vipId);
	}

	@Override
	public boolean updateNumber(Map<String, Object> map) {
		return produceRelationMapper.updateNumber(map);
	}

	@Override
	public List<ProduceRelation> getProducebyId(int vipId) {
		return produceRelationMapper.getProducebyId(vipId);
	}

}
