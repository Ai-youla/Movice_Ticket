package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.Cinema;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface CinemaMapper {

	@Insert("insert into cinema( cinemaAddressId, cinemaTypeId, cinemaName, service)value(#{cinemaAddressId}, #{cinemaTypeId}, #{cinemaName}, #{service})")
	public boolean createCinema(Cinema cinema);
	
	@Update("update cinema set  cinemaTypeId=#{cinemaTypeId}, cinemaName=#{cinemaName}, service=#{service} where cinemaId = #{cinemaId}")
	public boolean updateCinema(Cinema cinema);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.CinemaBuilder.class,method="queryCinemaList")
	@Result(property = "cinemaType" , column = "cinemaTypeId",
	one=@One(select="com.example.movice_ticket.mapper.CinemaTypeMapper.getCinemaType"))
	@Result(property = "cinemaAddress" , column = "cinemaAddressId",
	one=@One(select="com.example.movice_ticket.mapper.CinemaAddressMapper.getCinemaAddressById"))
	public List<Cinema> cinemaList(Map<String,Object>param);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.CinemaBuilder.class,method="queryCountCinemaList")
	public int cinemaCountList(Map<String,Object>param);
	
	@Select("select * from cinema where cinemaId = #{cinemaId}")
	@Result(property = "cinemaAddress" , column = "cinemaAddressId",
	one=@One(select="com.example.movice_ticket.mapper.CinemaAddressMapper.getCinemaAddressById"))
	public Cinema getCinemaById(int cinemaId);

	/*@Select("select * from cinema where filmId=#{filmId}")
	@Result(property = "film" , column = "filmId",
			one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
	@Result(property = "cinemaType" , column = "cinemaTypeId",
			one=@One(select="com.example.movice_ticket.mapper.CinemaTypeMapper.getCinemaType"))
	@Result(property = "cinemaAddress" , column = "cinemaAddressId",
			one=@One(select="com.example.movice_ticket.mapper.CinemaAddressMapper.getCinemaAddressById"))
	public List<Cinema> cinemas (int filmId);*/
 
}
