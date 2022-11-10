package com.example.movice_ticket.model;

public class CinemaType {

	private int cinemaTypeId;
	private String type;

	public CinemaType() {

	}

	public CinemaType(int cinemaTypeId, String type) {

		this.cinemaTypeId = cinemaTypeId;
		this.type = type;
	}

	public int getCinemaTypeId() {
		return cinemaTypeId;
	}

	public void setCinemaTypeId(int cinemaTypeId) {
		this.cinemaTypeId = cinemaTypeId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
