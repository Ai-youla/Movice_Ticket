package com.example.movice_ticket.model;

public class VipMember {

	private int vipId;
	private int integral;
	private float amount;
	private int member;
	private int userId;
	private User user;

	public VipMember() {
	}

	
	public VipMember( int integral, float amount, int member, int userId) {
		 
		this.integral = integral;
		this.amount = amount;
		this.member = member;
		this.userId = userId;
	}


	public int getVipId() {
		return vipId;
	}


	public void setVipId(int vipId) {
		this.vipId = vipId;
	}


	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
