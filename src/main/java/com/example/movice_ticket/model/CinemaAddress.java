package com.example.movice_ticket.model;

public class CinemaAddress {
	private int  cinemaAddressId      ;
	private String   province            ;
	private String   city                ;
	private String   county              ;
	private String   detailed             ;
	public CinemaAddress() {
		 
	}
	public CinemaAddress(int cinemaAddressId, String province, String city, String county, String detailed) {
		 
		this.cinemaAddressId = cinemaAddressId;
		this.province = province;
		this.city = city;
		this.county = county;
		this.detailed = detailed;
	}
	public int getCinemaAddressId() {
		return cinemaAddressId;
	}
	public void setCinemaAddressId(int cinemaAddressId) {
		this.cinemaAddressId = cinemaAddressId;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getDetailed() {
		return detailed;
	}
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	
}