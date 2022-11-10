package com.example.movice_ticket.model;

public class ViewingRelation {

	private int  viewingRelationId;
	private int   viewingTimeId;
	private int   cinemaId;
	private int filmId ;
	private  int seatId;
	private ViewingTime viewingTime;
	private Cinema cinema;
	private Film film;

	private Seats seats;

	public ViewingRelation() {
		 
	}
	 
	public ViewingRelation(int viewingTimeId, int cinemaId, int filmId) {
		 
		this.viewingTimeId = viewingTimeId;
		this.cinemaId = cinemaId;
		this.filmId = filmId;
	}

	public Seats getSeats() {
		return seats;
	}

	public void setSeats(Seats seats) {
		this.seats = seats;
	}

	public int getSeatId() {
		return seatId;
	}

	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}

	public int getViewingRelationId() {
		return viewingRelationId;
	}
	public void setViewingRelationId(int viewingRelationId) {
		this.viewingRelationId = viewingRelationId;
	}
	public int getViewingTimeId() {
		return viewingTimeId;
	}
	public void setViewingTimeId(int viewingTimeId) {
		this.viewingTimeId = viewingTimeId;
	}
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	public ViewingTime getViewingTime() {
		return viewingTime;
	}
	public void setViewingTime(ViewingTime viewingTime) {
		this.viewingTime = viewingTime;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	
}
