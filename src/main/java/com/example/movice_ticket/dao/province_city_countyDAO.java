package com.example.movice_ticket.dao;


import com.example.movice_ticket.model.province_city_county;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


public interface province_city_countyDAO {
	/**
	 * 通过类型查询省份
	 * @param type
	 * @return
	 */
	public List<province_city_county> list(int type);
	/**
	 * 通过编号查询城市
	 * @param param
	 * @return
	 */
	public List<province_city_county> getNextAddress(Map<String, Object>param);
	/**
	 * 通过名字查询下一级地址信息
	 * @param name
	 * @return
	 */
	public province_city_county byName(String name);
}
