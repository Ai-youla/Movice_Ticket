package com.example.movice_ticket.model;

public class MovieNews {

	private int movieNewsId         ;
	private int   filmId             ;
	private String   title            ;
	private String   news            ;
	private String  newsPicture       ;
	private Film film;
	private String   video            ;

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}

	public MovieNews() {
		 
	}

	public MovieNews(int movieNewsId, int filmId, String title, String news, String newsPicture, Film film, String video) {
		this.movieNewsId = movieNewsId;
		this.filmId = filmId;
		this.title = title;
		this.news = news;
		this.newsPicture = newsPicture;
		this.film = film;
		this.video = video;
	}

	public int getMovieNewsId() {
		return movieNewsId;
	}
	public void setMovieNewsId(int movieNewsId) {
		this.movieNewsId = movieNewsId;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNews() {
		return news;
	}
	public void setNews(String news) {
		this.news = news;
	}
	public String getNewsPicture() {
		return newsPicture;
	}
	public void setNewsPicture(String newsPicture) {
		this.newsPicture = newsPicture;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
	
}
