package com.wu.vaccine.VaccineManagementSystem.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wu.vaccine.VaccineManagementSystem.dao.CitizenDAO;
import com.wu.vaccine.VaccineManagementSystem.entity.Citizen;
import com.wu.vaccine.VaccineManagementSystem.entity.FirstDose;

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
		return citizenDAO.saveCitizen(citizen);
	}
	
	@GetMapping("/citizen/{citizenId}")
	public Citizen getCitizenById(@PathVariable long citizenId) {
		return citizenDAO.getCitizenById(citizenId);
	}
	
	
	@DeleteMapping("/citizen/{citizenId}")
	public void deleteCitizen(@PathVariable int citizenId) {
		Citizen citizen = citizenDAO.getCitizenById(citizenId);
		citizenDAO.deleteCitizen(citizen);
	}
	
	@GetMapping("/citizens/firstdose")
	public ArrayList<Citizen> getCitizensFirstDose(){
		return citizenDAO.getCitizensFirstDose();
	}
	
	@GetMapping("/citizens/seconddose")
	public ArrayList<Citizen> getCitizensSecondDose(){
		return citizenDAO.getCitizensSecondDose();
	}
	
	@GetMapping("/citizens/boosterdose")
	public ArrayList<Citizen> getCitizensBoosterDose(){
		return citizenDAO.getCitizensBoosterDose();
	}
	

//	api/countpendingFDose
	@GetMapping("/citizen/pendingFdose")
	public ArrayList<Citizen> pendingFDose(){
		return citizenDAO.pendingFDose();
	}
//	api/countpendingSDose
	@GetMapping("/citizen/pendingSdose")
	public ArrayList<Citizen> pendingSDose(){
		return citizenDAO.pendingSDose();
	}
	

//	api/countpendingBDose
	@GetMapping("/citizen/pendingBdose")
	public ArrayList<Citizen> pendingBDose(){
		return citizenDAO.pendingBDose();
	}
	
//	api/addFirstDose
	@PostMapping("/addFirstDose")
	public FirstDose addFirstDose(@RequestBody FirstDose firstDose) {
		return citizenDAO.addFirstDose(firstDose);
		
	}
	
//	api/firstDoses
	@GetMapping("/firstdoses")
	public ArrayList<FirstDose> getFirstDoses(){
		return citizenDAO.getFirstDose();
	}
	
//	api/countFDose
//	api/countSDose
//	api/countBDose
}
