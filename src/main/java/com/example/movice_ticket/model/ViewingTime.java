package com.example.movice_ticket.model;

public class ViewingTime {

	private int viewingTimeId        ;
	private int  sessions       ;
	 private String  beginTime          ;
	 private String  overTime          ;
	 private String   viemingDate       ;
	public ViewingTime() {
		 
	}
	 
	public ViewingTime(int viewingTimeId, int sessions, String beginTime, String overTime, String viemingDate) {
		 
		this.viewingTimeId = viewingTimeId;
		this.sessions = sessions;
		this.beginTime = beginTime;
		this.overTime = overTime;
		this.viemingDate = viemingDate;
	}

	public int getViewingTimeId() {
		return viewingTimeId;
	}
	public void setViewingTimeId(int viewingTimeId) {
		this.viewingTimeId = viewingTimeId;
	}
	public int getSessions() {
		return sessions;
	}
	public void setSessions(int sessions) {
		this.sessions = sessions;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getOverTime() {
		return overTime;
	}
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
 
	public String getViemingDate() {
		return viemingDate;
	}
	public void setViemingDate(String viemingDate) {
		this.viemingDate = viemingDate;
	}
	 
}
