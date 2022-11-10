package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.CinemaAddress;
import org.apache.ibatis.annotations.*;
@Mapper
public interface CinemaAddressMapper {

	@Insert("insert into cinemaAddress(province, city, county, detailed)value(#{province}, #{city}, #{county}, #{detailed})")
	@Options(useGeneratedKeys = true,keyColumn="cinemaAddressId",keyProperty = "cinemaAddressId")
	public int createCinemaAddress(CinemaAddress cinemaAddress);
	
	@Select("select * from  cinemaAddress where cinemaAddressId = #{cinemaAddressId}")
	public CinemaAddress getCinemaAddressById(int cinemaAddressId);
	
	@Update("update cinemaAddress set province=#{province}, city=#{city}, county=#{county},detailed=#{detailed} where cinemaAddressId = #{cinemaAddressId}")
	public boolean updateCinemaAddress(CinemaAddress cinemaAddress);
	
}
