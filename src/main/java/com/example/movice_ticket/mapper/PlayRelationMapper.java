package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.PlayRelation;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PlayRelationMapper {

	@Insert("insert into playRelation(participantsId, filmId)value(#{participantsId}, #{filmId})")
	@Options(useGeneratedKeys =  true, keyColumn = "playRelationId", keyProperty = "playRelationId")
	public boolean createPlayRelation(PlayRelation playRelation) ;
	
	@Select("select * from playRelation where filmId = #{filmId} ")
	@Result(property = "participants",column = "ParticipantsId",
	one=@One(select="com.example.movice_ticket.mapper.ParticipantsMapper.getParticipantsById"))
	public List<PlayRelation> getParticipants(int filmId) ;
	
	@Delete("delete from playRelation where playRelationId = #{playRelationId}")
	public boolean deletePlayRelation(int playRelationId) ;
	
}
