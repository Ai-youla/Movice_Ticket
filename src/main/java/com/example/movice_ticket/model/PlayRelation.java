package com.example.movice_ticket.model;

public class PlayRelation {

	private int playRelationId;
	private int   ParticipantsId;
	private int   filmId;
	private Participants participants;
	
	public PlayRelation() {
		 
	} 

	public PlayRelation(int playRelationId, int participantsId, int filmId) {
		this.playRelationId = playRelationId;
		ParticipantsId = participantsId;
		this.filmId = filmId;
	}



	public int getPlayRelationId() {
		return playRelationId;
	}
	public void setPlayRelationId(int playRelationId) {
		this.playRelationId = playRelationId;
	}
	public int getParticipantsId() {
		return ParticipantsId;
	}
	public void setParticipantsId(int participantsId) {
		ParticipantsId = participantsId;
	}
	public int getFilmId() {
		return filmId;
	}
	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}
	public Participants getParticipants() {
		return participants;
	}
	public void setParticipants(Participants participants) {
		this.participants = participants;
	}
	 
	
	
}
