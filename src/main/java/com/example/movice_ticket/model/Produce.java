package com.example.movice_ticket.model;

public class Produce {

	private int produceId;
	private String  produceName;
	private String  picture;
	   private int  integral;
	   private int  produceNum;
	   private int   state;
	   private String  beginDate;
	   private String  overDate;
	public Produce() {
		 
	}
	public Produce(String produceName, String picture, int integral, int produceNum, int state, String beginDate,
			String overDate) {
		 
		this.produceName = produceName;
		this.picture = picture;
		this.integral = integral;
		this.produceNum = produceNum;
		this.state = state;
		this.beginDate = beginDate;
		this.overDate = overDate;
	}
	public int getProduceId() {
		return produceId;
	}
	public void setProduceId(int produceId) {
		this.produceId = produceId;
	}
	public String getProduceName() {
		return produceName;
	}
	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	public int getProduceNum() {
		return produceNum;
	}
	public void setProduceNum(int produceNum) {
		this.produceNum = produceNum;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getOverDate() {
		return overDate;
	}
	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}
	   
	   
}
