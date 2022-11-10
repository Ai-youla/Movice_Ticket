package com.example.movice_ticket.model;

public class SeatRelation {

	private int seatRelationId;
	private int cinemaId;
	private int seatId;
	private Cinema cinema;
	private Seats seats;
	public SeatRelation() {
		 
	}
	public SeatRelation(int cinemaId, int seatId) {
		this.cinemaId = cinemaId;
		this.seatId = seatId;
	}
	public int getSeatRelationId() {
		return seatRelationId;
	}
	public void setSeatRelationId(int seatRelationId) {
		this.seatRelationId = seatRelationId;
	}
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public Cinema getCinema() {
		return cinema;
	}
	public void setCinema(Cinema cinema) {
		this.cinema = cinema;
	}
	public Seats getSeats() {
		return seats;
	}
	public void setSeats(Seats seats) {
		this.seats = seats;
	}
	
}
