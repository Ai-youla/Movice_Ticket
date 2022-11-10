package com.example.movice_ticket.dao;

import com.example.movice_ticket.model.FilmAndCinema;
import com.example.movice_ticket.model.ProduceRelation;

import java.util.List;
import java.util.Map;

public interface FilmAndCinemaDAO {
    /**
     * 批量绑定电影
     * @param filmAndCinema
     * @return
     */
    public boolean createFilmAndCinema(FilmAndCinema[] filmAndCinema);

    /**
     * 分页查询某个影院的全部电影
     * @param map
     * @return
     */
    public List<FilmAndCinema> queryByCinemaId(Map<String,Object> map);
    /**
     * 批量修改绑定的商品
     * @param filmAndCinemas
     * @return
     */
    public boolean assignFilm(FilmAndCinema[] filmAndCinemas ) ;

    /**
     * 删除该影院的电影
     * @param f_c_relationId
     * @return
     */
    public boolean deletefilmandcinema(int f_c_relationId) ;

    /**
     * 查询该影院有多少电影
     * @param cinemaId
     * @return
     */
    public int queryCountFilmAndCinema(int cinemaId);

    /**
     * 查询某影院全部电影
     * @param cinemaId
     * @return
     */
    public List<FilmAndCinema> byCinemaId(int cinemaId);

    /**
     * 查询有某电影的全部影院
     * @param filmId
     * @return
     */
    public List<FilmAndCinema> byfilmId(int filmId);
}
