package com.wu.vaccine.VaccineManagementSystem.dao;

import java.util.ArrayList;

import com.wu.vaccine.VaccineManagementSystem.entity.BoosterDose;
import com.wu.vaccine.VaccineManagementSystem.entity.Citizen;
import com.wu.vaccine.VaccineManagementSystem.entity.FirstDose;
import com.wu.vaccine.VaccineManagementSystem.entity.SecondDose;

public interface CitizenDAO {
	public ArrayList<Citizen> getCitizens();
	public Citizen saveCitizen(Citizen Cz);
	public Citizen getCitizenById(long citizenId);
	public void deleteCitizen(Citizen citizen);
	public ArrayList<Citizen> getCitizensFirstDose();
	public ArrayList<Citizen> getCitizensSecondDose();
	public ArrayList<Citizen> getCitizensBoosterDose();
	public ArrayList<Citizen> pendingFDose();
	public ArrayList<Citizen> pendingSDose();
	public ArrayList<Citizen> pendingBDose();
	public String addFirstDose(FirstDose firstDose);
	public ArrayList<FirstDose> getFirstDose();
	public String addSecondDose(SecondDose secondDose);
	public ArrayList<SecondDose> getSecondDoses();
	public String addBoosterDose(BoosterDose boosterDose);
	public ArrayList<BoosterDose> getBoosterDoses();
	public String updateCitizen(Citizen c1);
	public String getStatusOfCitizen(long citizenId);
}
