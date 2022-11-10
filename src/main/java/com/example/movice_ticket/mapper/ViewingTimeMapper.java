package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.ViewingTime;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface ViewingTimeMapper {

	@Insert("insert into viewingTime (sessions, beginTime, overTime, viemingDate)value(#{sessions}, #{beginTime}, #{overTime}, #{viemingDate})")
	public boolean createViewingTime(ViewingTime viewingTime);
	
	@Update("update viewingTime set  sessions = #{sessions} ,beginTime=#{beginTime}, overTime=#{overTime}, viemingDate=#{viemingDate} where viewingTimeId=#{viewingTimeId}")
	public boolean updateViewingTime(ViewingTime viewingTime);
	
	@Select("select * from viewingTime where viewingTimeId=#{viewingTimeId}")
	public ViewingTime getViewingTime(int viewingTimeId);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.ViewingTimeBuilder.class,method="queryViewingTime")
	public List<ViewingTime> ViewingTimes(Map<String, Object>param);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.ViewingTimeBuilder.class,method="queryConutViewingTime")
	public int CountViewingTimes(Map<String, Object>param);

	@Delete("delete from viewingTime where viewingTimeId=#{viewingTimeId}")
	public boolean delete(int viewingTimeId);
}
