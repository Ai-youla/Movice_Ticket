package com.example.movice_ticket.mapper.dynSQL;


import com.example.movice_ticket.model.ViewingTime;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;



public class ViewingTimeBuilder {
	public String queryViewingTime(Map<String, Object>param) {
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("viewingTime");
				ViewingTime viewingTime = (ViewingTime)param.get("viewingTime");
				if(viewingTime!=null) {
					int viewingTimeId = viewingTime.getViewingTimeId();
					if(viewingTimeId>0) {
						WHERE("viewingTimeId = #{ViewingTime.viewingTimeId}");
					}
					String viemingDate = viewingTime.getViemingDate();
					if(viemingDate!=null && !viemingDate.equals("")) {
						WHERE("viemingDate like concat('%',#{viewingTime.viemingDate},'%')");
					}
					 
				}
				Object begin = param.get("begin");
				Object size = param.get("size");
				 if(begin!=null && size!=null) {
					 LIMIT("#{begin},#{size}");
				 }
			}
		}.toString();
		return sql;
	}
	public String queryConutViewingTime(Map<String, Object>param) {
		String sql = new SQL() {
			{
				SELECT("count(*)");
				FROM("ViewingTime"); 
				ViewingTime viewingTime = (ViewingTime)param.get("viewingTime");
				if(viewingTime!=null) {
					int viewingTimeId = viewingTime.getViewingTimeId();
					if(viewingTimeId>0) {
						WHERE("viewingTimeId = #{ViewingTime.viewingTimeId}");
					}
					String viemingDate = viewingTime.getViemingDate();
					if(viemingDate!=null && !viemingDate.equals("")) {
						WHERE("viemingDate like concat('%',#{viewingTime.viemingDate},'%')");
					}
					 
					 
				}
			}
		}.toString();
		return sql;
	}
}
