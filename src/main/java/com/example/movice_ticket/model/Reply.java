package com.example.movice_ticket.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Reply {

	private int replyId             ;
	private int   filmReviewId      ;
	private int    userId         ;
	 private String  replyText      ;

	 private String replytime      ;
	 private FilmReview filmReview;
	 private User user;
	public Reply() {
		 
	}

	public Reply(int filmReviewId, int userId, String replyText, String replytime, FilmReview filmReview) {
		this.filmReviewId = filmReviewId;
		this.userId = userId;
		this.replyText = replyText;
		this.replytime = replytime;
		this.filmReview = filmReview;
	}

	public String getReplytime() {
		return replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}

	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getFilmReviewId() {
		return filmReviewId;
	}
	public void setFilmReviewId(int filmReviewId) {
		this.filmReviewId = filmReviewId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getReplyText() {
		return replyText;
	}
	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public FilmReview getFilmReview() {
		return filmReview;
	}
	public void setFilmReview(FilmReview filmReview) {
		this.filmReview = filmReview;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	 
}
