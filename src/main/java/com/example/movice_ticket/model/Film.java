package com.example.movice_ticket.model;



public class Film {

	 private int filmId;
	 private String  filmName;
	 private String  filmDescribe;
	 private String   filmPicture;
	 private String  startDate;
	 private int  movieDuration;
	 private float  score;
	 private String  state;
	  
	   private int  filmTypeId;
	   private int  filmPriceId;
	   
	   private FilmType filmType;
	   private FilmPrice filmPrice;
	private String  video;

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public Film() {
		 
	}

	public Film(int filmId, String filmName, String filmDescribe, String filmPicture, String startDate, int movieDuration, float score, String state, int filmTypeId, int filmPriceId, FilmType filmType, FilmPrice filmPrice, String video) {
		this.filmId = filmId;
		this.filmName = filmName;
		this.filmDescribe = filmDescribe;
		this.filmPicture = filmPicture;
		this.startDate = startDate;
		this.movieDuration = movieDuration;
		this.score = score;
		this.state = state;
		this.filmTypeId = filmTypeId;
		this.filmPriceId = filmPriceId;
		this.filmType = filmType;
		this.filmPrice = filmPrice;
		this.video = video;
	}

	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getFilmName() {
		return filmName;
	}
	public void setFilmName(String filmName) {
		this.filmName = filmName;
	}
	public String getFilmDescribe() {
		return filmDescribe;
	}
	public void setFilmDescribe(String filmDescribe) {
		this.filmDescribe = filmDescribe;
	}
	public String getFilmPicture() {
		return filmPicture;
	}
	public void setFilmPicture(String filmPicture) {
		this.filmPicture = filmPicture;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public int getMovieDuration() {
		return movieDuration;
	}
	public void setMovieDuration(int movieDuration) {
		this.movieDuration = movieDuration;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
 
	public int getFilmTypeId() {
		return filmTypeId;
	}
	public void setFilmTypeId(int filmTypeId) {
		this.filmTypeId = filmTypeId;
	}
	public int getFilmPriceId() {
		return filmPriceId;
	}
	public void setFilmPriceId(int filmPriceId) {
		this.filmPriceId = filmPriceId;
	}
 
	public FilmType getFilmType() {
		return filmType;
	}
	public void setFilmType(FilmType filmType) {
		this.filmType = filmType;
	}
	public FilmPrice getFilmPrice() {
		return filmPrice;
	}
	public void setFilmPrice(FilmPrice filmPrice) {
		this.filmPrice = filmPrice;
	}
	   
}
