package com.example.movice_ticket.model;

public class FilmReview {

	private int  filmReviewId        ;
	private String   filmReview     ;
	private int   likeNumber       ;
	private int   replyNumber      ;
	 private String  time          ;
	 private float  score          ;
	 private int  userId           ;
	 private int  filmId            ;
	 private User user;
	 private Film film;
	public FilmReview() {
		 
	}
	public FilmReview(int filmReviewId, String filmReview, int likeNumber, int replyNumber, String time, float score,
			int userId, int filmId) {
		super();
		this.filmReviewId = filmReviewId;
		this.filmReview = filmReview;
		this.likeNumber = likeNumber;
		this.replyNumber = replyNumber;
		this.time = time;
		this.score = score;
		this.userId = userId;
		this.filmId = filmId; 
	}
	public int getFilmReviewId() {
		return filmReviewId;
	}
	public void setFilmReviewId(int filmReviewId) {
		this.filmReviewId = filmReviewId;
	}
	public String getFilmReview() {
		return filmReview;
	}
	public void setFilmReview(String filmReview) {
		this.filmReview = filmReview;
	}
	public int getLikeNumber() {
		return likeNumber;
	}
	public void setLikeNumber(int likeNumber) {
		this.likeNumber = likeNumber;
	}
	public int getReplyNumber() {
		return replyNumber;
	}
	public void setReplyNumber(int replyNumber) {
		this.replyNumber = replyNumber;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Film getFilm() {
		return film;
	}
	public void setFilm(Film film) {
		this.film = film;
	}
 
}
