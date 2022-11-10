package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.ProduceRelation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface ProduceRelationDAO {
	/**
	 * 批量绑定VIP的商品
	 * @param produceRelations
	 * @return
	 */
	public boolean createProduceRelation(ProduceRelation[] produceRelations);
	/**
	 * 批量修改绑定的商品
	 * @param produceRelations
	 * @return
	 */
	public boolean assignProduce(ProduceRelation[] produceRelations ) ;
	/**
	 * 分页查询VIP绑定的商品
	 * @param param
	 * @return
	 */
	public List<ProduceRelation> getProduce(Map<String, Object>param) ;
	/**
	 * 通过Id查询绑定的商品数量
	 * @param vipId
	 * @return
	 */
	public int getProduceConut(int vipId) ;

	/**
	 * 修改兑换商品的数量
	 * @param map
	 * @return
	 */
	public boolean updateNumber(Map<String, Object> map);

	/**
	 * 查询VIPid用户绑定的全部商品
	 * @param vipId
	 * @return
	 */
	public List<ProduceRelation> getProducebyId(int vipId) ;

}
