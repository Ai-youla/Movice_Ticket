package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.Participants;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface ParticipantsMapper {

	@Insert("insert into participants ( type, priture, tureName,playName)value(#{type}, #{priture}, #{tureName},#{playName})")
	public boolean createParticipants(Participants participants);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.ParticipantsBuilder.class,method="listSql")
	public List<Participants> list(Map<String, Object>param);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.ParticipantsBuilder.class,method="listCountSql")
	public int conutlist(Map<String, Object>param);
	
	@Update("update participants set type=#{type}, tureName=#{tureName}, playName=#{playName} where ParticipantsId = #{ParticipantsId}")
	public boolean updateParticipants(Participants participants);
	
	@Update("update participants set priture =#{priture}  where ParticipantsId = #{ParticipantsId}")
	public boolean updatePriture(Participants participants);
	
	@Delete("delete from participants where ParticipantsId=#{ParticipantsId}")
	public boolean delete(int ParticipantsId);
	
	@Select("select * from participants where ParticipantsId = #{ParticipantsId}")
	public Participants getParticipantsById(int ParticipantsId);
}
