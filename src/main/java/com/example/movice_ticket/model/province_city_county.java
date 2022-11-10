package com.example.movice_ticket.model;

public class province_city_county {
	private int id  ;
	private String  name  ;
	private int   pid  ;
	private int  type  ;
	private String   code  ;
	private int    status  ;
	private int    create_time  ;
	private int    update_time ;
	public province_city_county() {
		 
	}
	public province_city_county(int id, String name, int pid, int type, String code, int status, int create_time,
			int update_time) {
		 
		this.id = id;
		this.name = name;
		this.pid = pid;
		this.type = type;
		this.code = code;
		this.status = status;
		this.create_time = create_time;
		this.update_time = update_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCreate_time() {
		return create_time;
	}
	public void setCreate_time(int create_time) {
		this.create_time = create_time;
	}
	public int getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(int update_time) {
		this.update_time = update_time;
	}
	
	
}
