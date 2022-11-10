package com.example.movice_ticket.mapper;


import com.example.movice_ticket.model.Film;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
@Mapper
public interface FilmMapper {

	@Insert("insert into film (filmName,filmDescribe,filmPicture,startDate,movieDuration,score,state,filmTypeId,filmPriceId,video)"
			+ "value(#{filmName},#{filmDescribe},#{filmPicture},#{startDate},#{movieDuration},0,#{state},#{filmTypeId},#{filmPriceId},#{video})")
	@Options(useGeneratedKeys = true, keyColumn = "filmId", keyProperty = "filmId")
	public boolean createFilm(Film film);
	
	@Update("update film set filmName =#{filmName},filmDescribe=#{filmDescribe},startDate=#{startDate},movieDuration=#{movieDuration},"
			+ "state=#{state},filmTypeId=#{filmTypeId},filmPriceId=#{filmPriceId} where filmId=#{filmId}")
	public boolean updateFilm(Film film);
	@Update("update film set score=#{score} where filmId=#{filmId}")
	public boolean updateScore(Film film);
	//	修改视频和图片
	@Update("update film set filmPicture=#{filmPicture} , video=#{video} where filmId=#{filmId}")
	public boolean updateFilmPrice(Map<String, Object>map);

	@Select("select * from film where filmId = #{filmId}")
	@Result(property = "filmPrice",column="filmPriceId",
	one = @One(select = "com.example.movice_ticket.mapper.FilmPriceMapper.queryById"))
	@Result(property="filmType",column="filmTypeId",
			one=@One(select="com.example.movice_ticket.mapper.FilmTypeMapper.getFilmTypeById"))
	public Film getFilmById(int filmId);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.FilmBuilder.class,method="generateSQL")
	@Result(property="filmType",column="filmTypeId",
	one=@One(select="com.example.movice_ticket.mapper.FilmTypeMapper.getFilmTypeById"))
	@Result(property = "filmPrice",column="filmPriceId",
	one = @One(select = "com.example.movice_ticket.mapper.FilmPriceMapper.queryById"))
	public List<Film> queryFilm(Map<String, Object> paramMap);
	
	@SelectProvider(type=com.example.movice_ticket.mapper.dynSQL.FilmBuilder.class,method="generateCountSQL")
	public int queryCountFilm(Map<String, Object> paramMap);
	
	@Delete("delete from film where filmId= #{filmId}")
	public boolean deleteFilm(int filmId);
}
