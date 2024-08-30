package com.wu.vaccine.VaccineManagementSystem.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="citizen")
public class Citizen {
	@Id
	@Column(name="cid")
	Long citizenId;
	@Column(name="firstname")
	String firstName;
	@Column(name="lastname")
	String lastName;
	@Column(name="address")
	String address;
	@Column(name="contact")
	Long contact;
	@Column(name="dob")
	LocalDate dateOfBirth;
	@Column(name="email")
	String email;
	@Column (name="dosescount")
	int dosesTaken;
	
	public Citizen(Long citizenId, String firstName, String lastName, String address, Long contact,
			LocalDate dateOfBirth, String email, int dosesTaken) {
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
	
	@Override
	public String toString() {
		return "Citizen [citizenId=" + citizenId + ", firstName=" + firstName + ", lastName=" + lastName + ", address="
				+ address + ", contact=" + contact + ", dateOfBirth=" + dateOfBirth + ", email=" + email
				+ ", dosesTaken=" + dosesTaken + "]";
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
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
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
	
}
