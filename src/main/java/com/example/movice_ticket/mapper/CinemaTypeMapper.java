package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.CinemaType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface CinemaTypeMapper {

	@Select("select * from cinemaType")
	public List<CinemaType> CinemaTypeList();
	
	@Select("select * from cinemaType where cinemaTypeId = #{cinemaTypeId}")
	public CinemaType getCinemaType (int cinemaTypeId);
}
