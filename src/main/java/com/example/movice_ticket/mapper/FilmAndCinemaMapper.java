package com.example.movice_ticket.mapper;

import com.example.movice_ticket.model.FilmAndCinema;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface FilmAndCinemaMapper {
    @Insert("insert into filmandcinema(cinemaId,filmId)value(#{cinemaId},#{filmId})")
    @Options(useGeneratedKeys =  true, keyColumn = "f_c_relationId", keyProperty = "f_c_relationId")
    public boolean createFilmAndCinema(FilmAndCinema filmAndCinema);

    @Select("select * from filmandcinema where cinemaId = #{cinemaId} limit #{begin},#{size} ")
    @Result(property = "film" , column = "filmId",
            one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
    public List<FilmAndCinema> queryByCinemaId(Map<String,Object>map);

    @Delete("delete from filmandcinema where f_c_relationId = #{f_c_relationId}")
    public boolean deletefilmandcinema(int f_c_relationId) ;

    @Select("select count(f_c_relationId) from filmandcinema where  cinemaId = #{cinemaId}")
    public int queryCountFilmAndCinema(int cinemaId);

    @Select("select * from filmandcinema where cinemaId = #{cinemaId} ")
    @Result(property = "film" , column = "filmId",
            one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
    @Result(property = "cinema",column = "cinemaId",
            one=@One(select="com.example.movice_ticket.mapper.CinemaMapper.getCinemaById"))
    public List<FilmAndCinema> byCinemaId(int cinemaId);

    @Select("select * from filmandcinema where filmId = #{filmId} ")
    @Result(property = "film" , column = "filmId",
            one=@One(select="com.example.movice_ticket.mapper.FilmMapper.getFilmById"))
    @Result(property = "cinema",column = "cinemaId",
            one=@One(select="com.example.movice_ticket.mapper.CinemaMapper.getCinemaById"))
    public List<FilmAndCinema> byfilmId(int filmId);
}
