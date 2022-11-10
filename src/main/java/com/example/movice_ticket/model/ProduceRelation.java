package com.example.movice_ticket.model;

public class ProduceRelation {

	private int produceRelationId     ;
	private int  vipId              ;
	private int   produceId          ;
	private int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	private Produce produce;
	private VipMember vipmember;
	public ProduceRelation() {
		 
	}
	public ProduceRelation(int produceRelationId, int vipId, int produceId) {
		 
		this.produceRelationId = produceRelationId;
		this.vipId = vipId;
		this.produceId = produceId;
	}
	public int getProduceRelationId() {
		return produceRelationId;
	}
	public void setProduceRelationId(int produceRelationId) {
		this.produceRelationId = produceRelationId;
	}
	public int getVipId() {
		return vipId;
	}
	public void setVipId(int vipId) {
		this.vipId = vipId;
	}
	public int getProduceId() {
		return produceId;
	}
	public void setProduceId(int produceId) {
		this.produceId = produceId;
	}
	public Produce getProduce() {
		return produce;
	}
	public void setProduce(Produce produce) {
		this.produce = produce;
	}
	public VipMember getVipmember() {
		return vipmember;
	}
	public void setVipmember(VipMember vipmember) {
		this.vipmember = vipmember;
	}
	 
	
}
