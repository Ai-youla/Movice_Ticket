package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.MovieNews;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface MovieNewsMapper {

	@Insert("insert into movieNews( filmId, title, news, newsPicture,video)value(#{filmId}, #{title}, #{news}, #{newsPicture},#{video})")
	public boolean createMovieNews(MovieNews movieNews);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.MovieNewsBuilder.class,method="generateSQL")
	@Result(property = "film",column="filmId",
	one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
	public List<MovieNews> list(Map<String, Object>paramMap);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.MovieNewsBuilder.class,method="generateCountSQL")
	public int listcount(Map<String, Object>paramMap);
	
	@Select("select * from movieNews where movieNewsId=#{movieNewsId}")	
	public MovieNews getMovieNewsbyId(int movieNewsId);
	
	@Update("update movieNews set title=#{title} ,filmId=#{filmId}, news=#{news} where movieNewsId= #{movieNewsId}")
	public boolean updateMovieNews(MovieNews movieNews);
	//	修改视频和图片
	@Update("update movieNews set newsPicture=#{newsPicture} , video=#{video} where movieNewsId= #{movieNewsId}")
	public boolean updateNewspicture(Map<String, Object>param);
	@Delete("delete from movieNews where movieNewsId= #{movieNewsId}")
	public boolean deleteMovieNews(int movieNewsId);
}
