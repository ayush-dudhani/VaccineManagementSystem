package com.wu.vaccine.VaccineManagementSystem.entity;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Citizen {
	Long citizenId;
	String firstName;
	String lastName;
	String address;
	Long contact;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	Date dateOfBirth;
	String email;
	
	public Citizen(Long citizenId, String firstName, String lastName, String address, Long contact,
			Date dateOfBirth, String email, int dosesTaken) {
		super();
		this.citizenId = citizenId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.contact = contact;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.dosesTaken = dosesTaken;
	}
	
	public Citizen() {}
	public Long getCitizenId() {
		return citizenId;
	}
	public void setCitizenId(Long citizenId) {
		this.citizenId = citizenId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getContact() {
		return contact;
	}
	public void setContact(Long contact) {
		this.contact = contact;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getDosesTaken() {
		return dosesTaken;
	}
	public void setDosesTaken(int dosesTaken) {
		this.dosesTaken = dosesTaken;
	}
	int dosesTaken;
}
