package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.Seats;
import org.apache.ibatis.annotations.*;
@Mapper
public interface SeatsMapper {

	@Insert("insert into seats (seats,cinemaNum)value(#{seats},#{cinemaNum})")
	@Options(useGeneratedKeys = true, keyColumn = "seatId", keyProperty = "seatId")
	public boolean creatSeats(Seats seats);
	
	@Select("select * from seats where seatId = #{seatId}")
	public Seats getSeats(int seatId);
	
	@Update("update seats set seats=#{seats},cinemaNum=#{cinemaNum} where seatId=#{seatId}")
	public boolean updateSeat(Seats seats);
	
	@Delete("delete from seats where seatId = #{seatId} ")
	public boolean delete(int seatId);
}
