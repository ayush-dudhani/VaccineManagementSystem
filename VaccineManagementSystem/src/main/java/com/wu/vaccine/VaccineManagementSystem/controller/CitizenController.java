package com.wu.vaccine.VaccineManagementSystem.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.vaccine.VaccineManagementSystem.dao.CitizenDAO;
import com.wu.vaccine.VaccineManagementSystem.entity.BoosterDose;
import com.wu.vaccine.VaccineManagementSystem.entity.Citizen;
import com.wu.vaccine.VaccineManagementSystem.entity.FirstDose;
import com.wu.vaccine.VaccineManagementSystem.entity.SecondDose;
import com.wu.vaccine.VaccineManagementSystem.exception.CitizenAlreadyExistedException;
import com.wu.vaccine.VaccineManagementSystem.exception.CitizenNotFoundException;

@RestController
@RequestMapping("/api")
public class CitizenController {
	private CitizenDAO citizenDAO;
	
	public CitizenController(CitizenDAO citizenDAO) {
		this.citizenDAO = citizenDAO;
	}
	
	@GetMapping("/citizens")
	public ArrayList<Citizen> getCitizens(){
		return citizenDAO.getCitizens();
	}
	
	@PostMapping("/citizen")
	public Citizen addCitizen(@RequestBody Citizen citizen) {
//		Exception Handled if citizen id exist
		if(citizenDAO.getCitizenById(citizen.getCitizenId()) != null) {
			throw new CitizenAlreadyExistedException("Citizen Already Existed");
		}
		return citizenDAO.saveCitizen(citizen);
	}
	
	@GetMapping("/citizen/{citizenId}")
	public Citizen getCitizenById(@PathVariable long citizenId) {
		if(citizenDAO.getCitizenById(citizenId) == null) {
			throw new CitizenNotFoundException("Citizen Not Found");
		}
		return citizenDAO.getCitizenById(citizenId);
	}
	
	
	@DeleteMapping("/citizen/{citizenId}")
	public void deleteCitizen(@PathVariable int citizenId) {
		Citizen citizen = citizenDAO.getCitizenById(citizenId);
		if(citizen==null)
			throw new CitizenNotFoundException("Citizen with citizenID not found");
		citizenDAO.deleteCitizen(citizen);
	}
	
	@PutMapping("/citizen")
	public String updateCitizen(@RequestBody Citizen c1) {
//		Exception Handling citizen not found
		if(getCitizenById(c1.getCitizenId()) == null) {
			throw new CitizenNotFoundException("Citizen with citizenId = " + c1.getCitizenId() + " not found");
		}
		return citizenDAO.updateCitizen(c1);
	}
	
//	Fetch from the first dose data table
	@GetMapping("/citizens/firstdose")
	public ArrayList<Citizen> getCitizensFirstDose(){
		return citizenDAO.getCitizensFirstDose();
	}
	
//	Fetch from the second dose data table
	@GetMapping("/citizens/seconddose")
	public ArrayList<Citizen> getCitizensSecondDose(){
		return citizenDAO.getCitizensSecondDose();
	}
	
//	Fetch from the booster dose data table
	@GetMapping("/citizens/boosterdose")
	public ArrayList<Citizen> getCitizensBoosterDose(){
		return citizenDAO.getCitizensBoosterDose();
	}
	

//	api/countpendingFDose
	@GetMapping("/citizen/pendingfirstdoses")
	public ArrayList<Citizen> pendingFDose(){
		return citizenDAO.pendingFDose();
	}
//	api/countpendingSDose
	@GetMapping("/citizen/pendingseconddoses")
	public ArrayList<Citizen> pendingSDose(){
		return citizenDAO.pendingSDose();
	}
	

//	api/countpendingBDose
	@GetMapping("/citizen/pendingboosterdoses")
	public ArrayList<Citizen> pendingBDose(){
		return citizenDAO.pendingBDose();
	}
	
//	api/addFirstDose
	@PostMapping("/addfirstdose")
	public String addFirstDose(@RequestBody FirstDose firstDose) {
		return citizenDAO.addFirstDose(firstDose);
		
	}
	
//	api/firstDoses
	@GetMapping("/firstdoses")
	public ArrayList<FirstDose> getFirstDoses(){
		return citizenDAO.getFirstDose();
	}
	
	
	@PostMapping("/addseconddose")
	public String addSecondDose(@RequestBody SecondDose secondDose) {
		return citizenDAO.addSecondDose(secondDose);
		
	}
	
	
	@GetMapping("/seconddoses")
	public ArrayList<SecondDose> getSecondDoses(){
		return citizenDAO.getSecondDoses();
	}
	
	
	@PostMapping("/addboosterdose")
	public String addBoosterDose(@RequestBody BoosterDose boosterDose) {
		return citizenDAO.addBoosterDose(boosterDose);
		
	}
	
	@GetMapping("/boosterdoses")
	public ArrayList<BoosterDose> getBoosterDoses(){
		return citizenDAO.getBoosterDoses();
	}
	
	
	@GetMapping("/citizen/{citizenId}/status")
	public String getStatusOfCitizen(@PathVariable long citizenId) {
//		Exception Handling citizen not found
		if(getCitizenById(citizenId) == null) {
			throw new CitizenNotFoundException("Citizen with citizenId = " + citizenId + " not found");
		}
		return citizenDAO.getStatusOfCitizen(citizenId);
	}
	
	
	

}
