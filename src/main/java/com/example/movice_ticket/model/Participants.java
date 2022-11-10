package com.example.movice_ticket.model;

public class Participants {

	private int ParticipantsId       ;
	private String   type             ;
	private String    priture            ;
	private String    TureName            ;
	private String    playName           ;
	public Participants() {
	}
 
	public Participants(int participantsId, String type, String priture, String tureName, String playName) {
		super();
		ParticipantsId = participantsId;
		this.type = type;
		this.priture = priture;
		TureName = tureName;
		this.playName = playName;
	}

	public int getParticipantsId() {
		return ParticipantsId;
	}
	public void setParticipantsId(int participantsId) {
		ParticipantsId = participantsId;
	}
	 
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPriture() {
		return priture;
	}
	public void setPriture(String priture) {
		this.priture = priture;
	}
	public String getTureName() {
		return TureName;
	}
	public void setTureName(String tureName) {
		TureName = tureName;
	}
	public String getPlayName() {
		return playName;
	}
	public void setPlayName(String playName) {
		this.playName = playName;
	}
	
	
}
