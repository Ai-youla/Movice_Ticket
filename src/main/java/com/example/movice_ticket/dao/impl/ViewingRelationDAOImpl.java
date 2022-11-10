package com.example.movice_ticket.dao.impl;


import com.example.movice_ticket.dao.ViewingRelationDAO;
import com.example.movice_ticket.mapper.ViewingRelationMapper;
import com.example.movice_ticket.model.ViewingRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ViewingRelationDAOImpl implements ViewingRelationDAO {

	@Autowired
	private ViewingRelationMapper viewingRelationMapper;
	
	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean createViewingRelation(ViewingRelation[] viewingRelation) {
		List<ViewingRelation> viewingRelationList = new ArrayList<ViewingRelation>();
		boolean b = true;
		int s = 0 ;
		if(viewingRelation!=null) {
			for(ViewingRelation viewingRelations:viewingRelation) {
				int cinemaId = viewingRelations.getCinemaId();
				Map<String, Object>param = new HashMap<String, Object>();
				int filmId = viewingRelations.getFilmId();
				param.put("cinemaId", cinemaId);
				param.put("filmId", filmId);
				viewingRelationList = viewingRelationMapper.getViewingTimebyId(param);
				for(ViewingRelation vr:viewingRelationList) {
					if(vr.getViewingTimeId()==viewingRelations.getViewingTimeId()) {
						s = vr.getViewingTimeId();
					}
				}
				
				if(s==viewingRelations.getViewingTimeId()) {
					b = viewingRelationMapper.createViewingRelation(viewingRelations);
					b= viewingRelationMapper.delete(viewingRelations.getViewingRelationId());
				}else {
					b = viewingRelationMapper.createViewingRelation(viewingRelations);
				}
			}
		}
		return b;
	}

	@Override
	public boolean assignViewingTime(ViewingRelation[] viewingRelation) {
		boolean b=true;
		if(viewingRelation!=null) {
			for(ViewingRelation viewingRelations: viewingRelation) {
				int viewingRelationId = viewingRelations.getViewingRelationId();
				b = viewingRelationMapper.delete(viewingRelationId);
			}
		}
		return b;
	}

	@Override
	public List<ViewingRelation> getViewingTime(Map<String, Object> param) {
		
		return viewingRelationMapper.getViewingTime(param);
	}

	@Override
	public int getViewingTimeConut(Map<String, Object>param) {
		
		return viewingRelationMapper.getViewingTimeConut(param);
	}

	@Override
	public boolean updateseatId(ViewingRelation[] viewingRelation) {
		List<ViewingRelation> viewingRelationList = null;
		boolean b = true;
		int s = 0 ;
		if(viewingRelation!=null) {
			for(ViewingRelation viewingRelations:viewingRelation) {
				int cinemaId = viewingRelations.getCinemaId();
				Map<String, Object>param = new HashMap<String, Object>();
				int filmId = viewingRelations.getFilmId();
				int seatId = viewingRelations.getSeatId();
				param.put("cinemaId", cinemaId);
				param.put("filmId", filmId);
				param.put("seatId", seatId);
				viewingRelationList = viewingRelationMapper.getViewingTimeByseatId(param);
				if(viewingRelationList!=null){
					for(ViewingRelation vr:viewingRelationList) {
						if(vr.getViewingTimeId()==viewingRelations.getViewingTimeId()) {
							s = vr.getViewingTimeId();
						}
					}
					if(s!=0){
						if(s==viewingRelations.getViewingTimeId()) {
//							System.out.println(s+"ttttt"+viewingRelations.getViewingTimeId());
							System.out.println("该场次已存在");
							b=false;
						}else {
							b = viewingRelationMapper.updateseatId(viewingRelations);
						}
					}else {
						b = viewingRelationMapper.updateseatId(viewingRelations);
					}
				}else {
					b = viewingRelationMapper.updateseatId(viewingRelations);
				}

			}
		}
		return  b;
	}

	@Override
	public List<ViewingRelation> getViewingTimeByseatId(Map<String, Object> param) {

		return viewingRelationMapper.getViewingTimeByseatId(param);
	}

}
