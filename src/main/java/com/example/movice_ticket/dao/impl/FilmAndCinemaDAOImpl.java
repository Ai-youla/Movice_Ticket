package com.example.movice_ticket.dao.impl;

import com.example.movice_ticket.dao.FilmAndCinemaDAO;
import com.example.movice_ticket.mapper.FilmAndCinemaMapper;
import com.example.movice_ticket.model.FilmAndCinema;
import com.example.movice_ticket.model.ProduceRelation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FilmAndCinemaDAOImpl implements FilmAndCinemaDAO {
    @Autowired
    private FilmAndCinemaMapper filmAndCinemaMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean createFilmAndCinema(FilmAndCinema[] filmAndCinema) {
        boolean b = true;
        boolean success =true;
        int s = 0;
        if(filmAndCinema!=null) {
            for(FilmAndCinema filmAndCinemas : filmAndCinema) {

                int cinemaId = filmAndCinemas.getCinemaId();
    //查询该影院的全部电影
                List<FilmAndCinema> filmAndCinemaList = filmAndCinemaMapper.byCinemaId(cinemaId);
                int filmId=0;
                for(FilmAndCinema ufilmAndCinemaList :filmAndCinemaList) {

                    filmId = ufilmAndCinemaList.getFilmId();
                    //将选择的电影和数据库中该影院存在的电影进行比较
                    if(filmId==filmAndCinemas.getFilmId()) {
                        s=filmId;
                    }
                }
                if(s==filmAndCinemas.getFilmId()) {
                    success = filmAndCinemaMapper.createFilmAndCinema(filmAndCinemas);
                    int f_c_relationId = filmAndCinemas.getF_c_relationId();
                    filmAndCinemaMapper.deletefilmandcinema(f_c_relationId);
                }else {
                    success = filmAndCinemaMapper.createFilmAndCinema(filmAndCinemas);
                }
                b = success && b;
            }
        }
        return b;
    }

    @Override
    public List<FilmAndCinema> queryByCinemaId(Map<String, Object> map) {
        return filmAndCinemaMapper.queryByCinemaId(map);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean assignFilm(FilmAndCinema[] filmAndCinemas) {
        boolean b=true;

        if(filmAndCinemas!=null) {
            for(FilmAndCinema filmAndCinema : filmAndCinemas) {
                int f_c_relationId =filmAndCinema.getF_c_relationId();
                b = filmAndCinemaMapper.deletefilmandcinema(f_c_relationId);
            }
        }
        return b;
    }

    @Override
    public boolean deletefilmandcinema(int f_c_relationId) {
        return filmAndCinemaMapper.deletefilmandcinema(f_c_relationId);
    }

    @Override
    public int queryCountFilmAndCinema(int cinemaId) {
        return filmAndCinemaMapper.queryCountFilmAndCinema(cinemaId);
    }

    @Override
    public List<FilmAndCinema> byCinemaId(int cinemaId) {
        return filmAndCinemaMapper.byCinemaId(cinemaId);
    }

    @Override
    public List<FilmAndCinema> byfilmId(int filmId) {
        return filmAndCinemaMapper.byfilmId(filmId);
    }
}
