package com.example.movice_ticket.mapper.dynSQL;


import com.example.movice_ticket.model.Participants;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class ParticipantsBuilder {

	public String listSql(Map<String, Object>param) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("participants");
				Participants participants = (Participants)param.get("participants");
				if(participants!=null) {
					String TureName = participants.getTureName();
					if(TureName!=null && !TureName.equals("")) {
						WHERE("TureName like concat('%',#{participants.TureName},'%')");
					}
				}
				Object beginobj = param.get("begin");
				Object sizeobj = param.get("size");
				if(beginobj!=null && sizeobj!=null) {
					LIMIT("#{begin},#{size}");
				}
			}
		}.toString();
		return sql;
	}

	public String listCountSql(Map<String, Object>param) {
		String sql = new SQL() {
			{
				SELECT("count(*)");
				FROM("participants");
				Participants participants = (Participants)param.get("participants");
				if(participants!=null) {
					String TureName = participants.getTureName();
					if(TureName!=null && !TureName.equals("")) {
						WHERE("TureName like concat('%',#{participants.TureName},'%')");
					}
				}
			}
		}.toString();
		return sql;
	}
}
