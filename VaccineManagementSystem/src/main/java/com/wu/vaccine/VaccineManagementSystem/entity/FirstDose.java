package com.wu.vaccine.VaccineManagementSystem.entity;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="first_dose")
public class FirstDose {
	@Id
	@Column(name="CID")
	Long citizenId;
	@Column(name="DOSE_NAME")
	String doseName;
	@Column(name="DOCTOR_NAME")
	String docName;
	@Column(name="LOCATION")
	String location;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name="DOSE_DATE")
	LocalDate doseDate;
	
	public FirstDose() {
		
	}
	
	
	public Long getCitizenId() {
		return citizenId;
	}


	public void setCitizenId(Long citizenId) {
		this.citizenId = citizenId;
	}


	public String getDoseName() {
		return doseName;
	}


	public void setDoseName(String doseName) {
		this.doseName = doseName;
	}


	public String getDocName() {
		return docName;
	}


	public void setDocName(String docName) {
		this.docName = docName;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public LocalDate getDoseDate() {
		return doseDate;
	}


	public void setDoseDate(LocalDate doseDate) {
		this.doseDate = doseDate;
	}
	
	
	public FirstDose(Long citizenId, String doseName, String docName, String location, LocalDate doseDate) {
		super();
		this.citizenId = citizenId;
		this.doseName = doseName;
		this.docName = docName;
		this.location = location;
		this.doseDate = doseDate;
	}
	
}