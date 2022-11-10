package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.province_city_county;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface province_city_county_mapper {

	@Select("select * from province_city_county where type=#{type}")
	public List<province_city_county> list(int type);
	
	@Select("select * from province_city_county where type=#{type} and code like concat(#{code},'%')")
	public List<province_city_county> getNextAddress(Map<String, Object>param);
	
	@Select("select * from province_city_county where name=#{name}")
	public province_city_county byName(String name);
}
