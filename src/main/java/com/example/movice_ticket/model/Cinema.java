package com.example.movice_ticket.model;

public class Cinema {
	private int cinemaId;
	 private int  cinemaAddressId;
	private int    cinemaTypeId;
	 private String  cinemaName;
	 private String  service;

	 private CinemaType cinemaType;
	 private CinemaAddress cinemaAddress;
	public Cinema() {
		 
	}
	public Cinema(int cinemaId, int cinemaAddressId, int cinemaTypeId, String cinemaName, String service) {
		 
		this.cinemaId = cinemaId;

		this.cinemaAddressId = cinemaAddressId;
		this.cinemaTypeId = cinemaTypeId;
		this.cinemaName = cinemaName;
		this.service = service;
	}
	public int getCinemaId() {
		return cinemaId;
	}
	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

	public int getCinemaAddressId() {
		return cinemaAddressId;
	}
	public void setCinemaAddressId(int cinemaAddressId) {
		this.cinemaAddressId = cinemaAddressId;
	}
	public int getCinemaTypeId() {
		return cinemaTypeId;
	}
	public void setCinemaTypeId(int cinemaTypeId) {
		this.cinemaTypeId = cinemaTypeId;
	}
	public String getCinemaName() {
		return cinemaName;
	}
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public CinemaType getCinemaType() {
		return cinemaType;
	}
	public void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}
	public CinemaAddress getCinemaAddress() {
		return cinemaAddress;
	}
	public void setCinemaAddress(CinemaAddress cinemaAddress) {
		this.cinemaAddress = cinemaAddress;
	}
	 
}
