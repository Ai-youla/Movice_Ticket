package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.ViewingRelation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface ViewingRelationDAO {

	/**
	 * 影院批量绑定观影批次
	 * @param viewingRelation
	 * @return
	 */
	public boolean createViewingRelation(ViewingRelation[] viewingRelation);
	/**
	 * 重新批量绑定观影批次
	 * @param viewingRelation
	 * @return
	 */
	public boolean assignViewingTime(ViewingRelation[] viewingRelation) ;
	/**
	 * 分页查询所有观影批次
	 * @param param
	 * @return
	 */
 	public List<ViewingRelation> getViewingTime(Map<String, Object>param) ;
	/**
	 * 观影批次个数
	 * @param param
	 * @return
	 */
 	public int getViewingTimeConut(Map<String, Object>param) ;

	/**
	 * 场次批量绑定影厅
	 * @param viewingRelation
	 * @return
	 */
	public boolean updateseatId(ViewingRelation[] viewingRelation);

	/**
	 * 判断影厅是否已经场次
	 * @param param
	 * @return
	 */
	public List<ViewingRelation> getViewingTimeByseatId(Map<String, Object>param) ;
}
