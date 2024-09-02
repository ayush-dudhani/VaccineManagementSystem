package com.wu.vaccine.VaccineManagementSystem.entity;

public class JSONObject {
	String message;
	int statuscode;
	
	
	public JSONObject(String message, int statuscode) {
		super();
		this.message = message;
		this.statuscode = statuscode;
	}
	
	public JSONObject() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatuscode() {
		return statuscode;
	}
	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}
}
