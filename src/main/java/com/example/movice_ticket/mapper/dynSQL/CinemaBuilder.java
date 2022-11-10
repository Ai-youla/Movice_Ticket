package com.example.movice_ticket.mapper.dynSQL;


import com.example.movice_ticket.model.Cinema;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class CinemaBuilder {

	public String queryCinemaList(Map<String, Object>param) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("cinema");
				Cinema cinema = (Cinema)param.get("cinema");
				 
				if(cinema!=null) {
					String cinemaName = cinema.getCinemaName();
					if(cinemaName!=null && !cinemaName.equals("")) {
						WHERE("cinemaName like concat('%',#{cinema.cinemaName},'%')");
					}
					String service  = cinema.getService();
					if(service!=null && !service.equals("")) {
						WHERE("service like concat('%',#{cinema.service},'%')");
					}
					int cinemaId = cinema.getCinemaId();
					if(cinemaId>0) {
						WHERE("cinemaId = #{cinema.cinemaId}");
					}
				}
				ORDER_BY("cinemaId desc");
				Object beginObj = param.get("begin");
				Object sizeObj = param.get("size");

				if (beginObj != null && sizeObj != null) {
					LIMIT(" #{begin},#{size}");
				}
			}
		}.toString();
		return sql;
	}
	public String queryCountCinemaList(Map<String, Object>param) {
		String sql = new SQL() {
			{
				SELECT("count(*)");
				FROM("cinema");
				Cinema cinema = (Cinema)param.get("cinema");
				if(cinema!=null) {
					String cinemaName = cinema.getCinemaName();
					if(cinemaName!=null && !cinemaName.equals("")) {
						WHERE("cinemaName like concat('%',#{cinema.cinemaName},'%')");
					}
					int cinemaId = cinema.getCinemaId();
					if(cinemaId>0) {
						WHERE("cinemaId = #{cinema.cinemaId}");
					}
					
				}
			}
		}.toString();
		return sql;
	}
}
