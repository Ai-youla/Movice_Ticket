package com.example.movice_ticket.model;

public class FilmAndCinema {
    private  int f_c_relationId     ;
    private  int  cinemaId          ;
    private  int filmId           ;
    private  Cinema cinema;
    private Film film;

    public FilmAndCinema() {
    }

    public FilmAndCinema(int f_c_relationId, int cinemaId, int filmId) {
        this.f_c_relationId = f_c_relationId;
        this.cinemaId = cinemaId;
        this.filmId = filmId;
    }

    public FilmAndCinema(int cinemaId, int filmId) {
        this.cinemaId = cinemaId;
        this.filmId = filmId;
    }

    public int getF_c_relationId() {
        return f_c_relationId;
    }

    public void setF_c_relationId(int f_c_relationId) {
        this.f_c_relationId = f_c_relationId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }
}
