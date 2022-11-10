package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.SeatRelation;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface SeatRelationMapper {

	//影厅绑定影院
	@Insert("insert into seatRelation(cinemaId, seatId)value(#{cinemaId},#{seatId})")
	public boolean createSeatRelation(SeatRelation seatRelation);
	
	//查看影院绑定的影厅
	@Select("select * from seatRelation where cinemaId = #{cinemaId} ")
	@Result(property = "seats",column = "seatId",
	one=@One(select="com.example.movice_ticket.mapper.SeatsMapper.getSeats"))
	public List<SeatRelation> getSeat(int cinemaId) ;
	//删除某个影院绑定的影厅
	@Delete("delete from seatRelation where seatRelationId = #{seatRelationId}")
	public boolean deleteSeatRelation(int seatRelationId) ;
	
}
