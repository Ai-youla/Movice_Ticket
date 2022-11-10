package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.ViewingRelation;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface ViewingRelationMapper {

	@Insert("insert into viewingRelation(viewingTimeId, cinemaId,filmId,seatId)value(#{viewingTimeId},#{cinemaId},#{filmId},0)")
	@Options(useGeneratedKeys =  true, keyColumn = "viewingRelationId", keyProperty = "viewingRelationId")
	public boolean createViewingRelation(ViewingRelation viewingRelation);
	
	@Delete("delete from viewingRelation where viewingRelationId=#{viewingRelationId} ")
	public boolean delete(int viewingRelationId);

	@Update("update viewingRelation set seatId=#{seatId} where viewingRelationId=#{viewingRelationId} ")
	public boolean updateseatId(ViewingRelation viewingRelation);
	@Select("select * from viewingRelation where cinemaId=#{cinemaId} and filmId = #{filmId}")
	public List<ViewingRelation> getViewingTimebyId(Map<String, Object>param) ;

	@Select("select * from viewingRelation where cinemaId=#{cinemaId} and filmId = #{filmId} and seatId=#{seatId}")
	@Result(property = "viewingTime",column = "viewingTimeId",
			one=@One(select="com.example.movice_ticket.mapper.ViewingTimeMapper.getViewingTime"))
	@Result(property = "seats",column = "seatId",
			one=@One(select="com.example.movice_ticket.mapper.SeatsMapper.getSeats"))
	@Result(property = "film",column = "filmId",
			one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
	public List<ViewingRelation> getViewingTimeByseatId(Map<String, Object>param) ;

	@Select("select * from viewingRelation where cinemaId=#{cinemaId} and filmId = #{filmId} limit #{begin},#{size}")
	@Result(property = "viewingTime",column = "viewingTimeId",
	one=@One(select="com.example.movice_ticket.mapper.ViewingTimeMapper.getViewingTime"))
	@Result(property = "seats",column = "seatId",
			one=@One(select="com.example.movice_ticket.mapper.SeatsMapper.getSeats"))
	@Result(property = "film",column = "filmId",
			one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
	public List<ViewingRelation> getViewingTime(Map<String, Object>param) ;
	
	@Select("select count(*) from viewingRelation where cinemaId=#{cinemaId} and filmId = #{filmId} ")
	public int getViewingTimeConut(Map<String, Object>param) ;
}
