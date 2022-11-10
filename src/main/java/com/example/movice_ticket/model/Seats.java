package com.example.movice_ticket.model;

public class Seats {
	private int  seatId        ;
	private String  seats     ;
	private String cinemaNum;
	public Seats() {
		 
	}
	 
	public Seats(int seatId, String seats, String cinemaNum) {
	 
		this.seatId = seatId;
		this.seats = seats;
		this.cinemaNum = cinemaNum;
	}

	public int getSeatId() {
		return seatId;
	}
	public void setSeatId(int seatId) {
		this.seatId = seatId;
	}
	public String getSeats() {
		return seats;
	}
	public void setSeats(String seats) {
		this.seats = seats;
	}
	public String getCinemaNum() {
		return cinemaNum;
	}
	public void setCinemaNum(String cinemaNum) {
		this.cinemaNum = cinemaNum;
	}
	
}
