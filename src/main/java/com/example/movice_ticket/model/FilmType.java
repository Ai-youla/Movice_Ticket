package com.example.movice_ticket.model;

public class FilmType {
	private int filmTypeId       ;
	private String   type  ;
	public FilmType() {
		 
	}
	public FilmType(int filmTypeId, String type) {
		 
		this.filmTypeId = filmTypeId;
		this.type = type;
	}
	public int getFilmTypeId() {
		return filmTypeId;
	}
	public void setFilmTypeId(int filmTypeId) {
		this.filmTypeId = filmTypeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
