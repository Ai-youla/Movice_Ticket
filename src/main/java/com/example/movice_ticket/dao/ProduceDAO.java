package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.Produce;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface ProduceDAO {

	/**
	 * 创建积分商品
	 * @param produce
	 * @return
	 */
	public boolean createProduce(Produce produce);
	/**
	 * 修改商品信息
	 * @param produce
	 * @return
	 */
	public boolean updateProduce(Produce produce);
	/**
	 * 修改商品图片
	 * @param param
	 * @return
	 */
	public boolean updateProducePricture(Map<String, Object>param);
	/**
	 * 修改商品状态
	 * @param param
	 * @return
	 */
	public boolean updateState(Map<String, Object>param);
	/**
	 * 通过Id查询商品信息
	 * @param produceId
	 * @return
	 */
	public Produce getProduceById(int produceId);
	/**
	 * 分页查询所有商品信息
	 * @param param
	 * @return
	 */
	public List<Produce> produceList(Map<String, Object>param);
	/**
	 * 商品个数
	 * @param param
	 * @return
	 */
	public int produceConutList(Map<String, Object>param);
	/**
	 * 减少商品数量
	 * @param param
	 * @return
	 */
	public boolean reduceProduceNum(Map<String, Object>param);
}
