package com.example.movice_ticket.model;

public class FilmPrice {

	private int filmPriceId         ;
	private float   price              ;
	private int   filmNumber       ;
	private int   allNumber       ;
	public FilmPrice() {
		 
	}
	
	public FilmPrice(int filmPriceId, float price, int filmNumber, int allNumber) {
		 
		this.filmPriceId = filmPriceId;
		this.price = price;
		this.filmNumber = filmNumber;
		this.allNumber = allNumber;
	}

	public int getFilmPriceId() {
		return filmPriceId;
	}
	public void setFilmPriceId(int filmPriceId) {
		this.filmPriceId = filmPriceId;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getFilmNumber() {
		return filmNumber;
	}
	public void setFilmNumber(int filmNumber) {
		this.filmNumber = filmNumber;
	}
	public int getAllNumber() {
		return allNumber;
	}
	public void setAllNumber(int allNumber) {
		this.allNumber = allNumber;
	}
	
}
