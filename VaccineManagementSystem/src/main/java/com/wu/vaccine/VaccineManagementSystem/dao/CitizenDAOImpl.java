package com.wu.vaccine.VaccineManagementSystem.dao;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wu.vaccine.VaccineManagementSystem.entity.BoosterDose;
import com.wu.vaccine.VaccineManagementSystem.entity.Citizen;
import com.wu.vaccine.VaccineManagementSystem.entity.FirstDose;
import com.wu.vaccine.VaccineManagementSystem.entity.SecondDose;
import com.wu.vaccine.VaccineManagementSystem.exception.CitizenNotFoundException;
import com.wu.vaccine.VaccineManagementSystem.exception.EligibilityCriteriaFailedException;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
public class CitizenDAOImpl implements CitizenDAO {
	
	private EntityManager entityManager;
	public CitizenDAOImpl(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}
	@Override
	public ArrayList<Citizen> getCitizens() {
		Session session = entityManager.unwrap(Session.class);
		Query theQuery = session.createQuery("from Citizen", Citizen.class);
		ArrayList<Citizen> cz = (ArrayList<Citizen>) theQuery.getResultList();

		
		return cz;
	}

	@Override
	@Transactional
	public Citizen getCitizenById(long citizenId) {
		// TODO Auto-generated method stub
		Session s = entityManager.unwrap(Session.class);
		Citizen citizen = s.get(Citizen.class,citizenId);
		
		return citizen;
	}
	
	@Transactional
	@Override
	public Citizen saveCitizen(Citizen citizen) {
		Session s = entityManager.unwrap(Session.class);
		s.merge(citizen);
		return citizen;
	}


	@Override
	@Transactional
	public void deleteCitizen(Citizen citizen) {
		// TODO Auto-generated method stub
		Session s = entityManager.unwrap(Session.class);
		s.remove(citizen);
	}
	@Override
	public ArrayList<Citizen> getCitizensFirstDose() {
		Session s = entityManager.unwrap(Session.class);
		Query theQuery = s.createQuery("from Citizen where dosesTaken=1", Citizen.class);
		ArrayList<Citizen> cz = (ArrayList<Citizen>) theQuery.getResultList();
		return cz;
	}
	@Override
	public ArrayList<Citizen> getCitizensSecondDose() {
		Session s = entityManager.unwrap(Session.class);
		Query theQuery = s.createQuery("from Citizen where dosesTaken=2", Citizen.class);
		ArrayList<Citizen> cz = (ArrayList<Citizen>) theQuery.getResultList();
		return cz;
	}
	@Override
	public ArrayList<Citizen> getCitizensBoosterDose() {
		Session s = entityManager.unwrap(Session.class);
		Query theQuery = s.createQuery("from Citizen where dosesTaken=3", Citizen.class);
		ArrayList<Citizen> cz = (ArrayList<Citizen>) theQuery.getResultList();
		return cz;
	}
	@Override
	public ArrayList<Citizen> pendingFDose() {
		Session s = entityManager.unwrap(Session.class);
		Query theQuery = s.createQuery("from Citizen where dosesTaken=0", Citizen.class);
		ArrayList<Citizen> cz = (ArrayList<Citizen>) theQuery.getResultList();
		return cz;
	}
	
	
	@Override
	public ArrayList<Citizen> pendingSDose() {
		Session s = entityManager.unwrap(Session.class);
		LocalDate currentdate = LocalDate.now();
		
		Query theQuery = s.createQuery("from FirstDose", FirstDose.class );
		
		ArrayList<FirstDose>list=(ArrayList<FirstDose>) theQuery.getResultList();
		ArrayList<Citizen>ans = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			long daysBetween = ChronoUnit.DAYS.between(list.get(i).getDoseDate(),currentdate);
//			the citizen has 120+ days where citizen has not taken second dose
			if(daysBetween > 120) {
				Citizen cz  = getCitizenById(list.get(i).getCitizenId());
//				the citizen has not taken second dose
				if(cz.getDosesTaken() == 1) {
					ans.add(cz);
				}
			}
		}
		return ans;
	}
	
	
	@Override
	public ArrayList<Citizen> pendingBDose() {
		Session s = entityManager.unwrap(Session.class);
		LocalDate currentdate = LocalDate.now();
		
		Query theQuery = s.createQuery("from SecondDose", SecondDose.class );
		
		ArrayList<SecondDose>list=(ArrayList<SecondDose>) theQuery.getResultList();
		ArrayList<Citizen>ans = new ArrayList<>();
		for(int i=0;i<list.size();i++) {
			long daysBetween = ChronoUnit.DAYS.between(list.get(i).getDoseDate(),currentdate);
//			the citizen has 150+ days where citizen has not taken second dose
			if(daysBetween >= 150) {
				Citizen cz  = getCitizenById(list.get(i).getCitizenId());
//				the citizen has not taken booster dose
				if(cz.getDosesTaken() == 2) {
					ans.add(cz);
				}
			}
		}
		return ans;
	}
	@Transactional
	@Override
	public String addFirstDose(FirstDose firstDose) {
		Session s = entityManager.unwrap(Session.class);
		if(getCitizenById(firstDose.getCitizenId()) == null) {
			throw new EligibilityCriteriaFailedException("Invalid Citizen Id");
		}
		s.merge(firstDose);
		return "success";
	}
	
	@Override
	public ArrayList<FirstDose> getFirstDose() {
		Session s = entityManager.unwrap(Session.class);
		Query theQuery = s.createQuery("from FirstDose", FirstDose.class);
		ArrayList<FirstDose> fDoses = (ArrayList<FirstDose>) theQuery.getResultList();
		return fDoses;
		
	}
	@Transactional
	@Override
	public String addSecondDose(SecondDose secondDose) {
		// TODO Auto-generated method stub
		Session s = entityManager.unwrap(Session.class);
		Citizen cz = getCitizenById(secondDose.getCitizenId());
		if(cz == null) {
			throw new EligibilityCriteriaFailedException("Invalid Citizen Id");
		}
		
		FirstDose fDose = s.get(FirstDose.class, cz.getCitizenId());
		
		if(!fDose.getDoseName().equals(secondDose.getDoseName())) {
			throw new EligibilityCriteriaFailedException(fDose.getDoseName() 
					+ " was taken in first dose");
		}
		long daysinbetween = ChronoUnit.DAYS.between(fDose.getDoseDate(), secondDose.getDoseDate());
		if(daysinbetween < 120) {
			throw new EligibilityCriteriaFailedException("Wait for another " + (120-daysinbetween) + " days before getting second dose");
		}	
		
		s.merge(secondDose);
		
		return "success";
	}
	@Override
	public ArrayList<SecondDose> getSecondDoses() {
		// TODO Auto-generated method stub
		Session s = entityManager.unwrap(Session.class);
		Query theQuery = s.createQuery("from SecondDose", SecondDose.class);
		ArrayList<SecondDose> sDoses = (ArrayList<SecondDose>) theQuery.getResultList();
		return sDoses;
	}
	@Transactional
	@Override
	public String addBoosterDose(BoosterDose boosterDose) {
		// TODO Auto-generated method stub
		Session s = entityManager.unwrap(Session.class);
		Citizen cz = getCitizenById(boosterDose.getCitizenId());
		if(cz == null) {
			throw new EligibilityCriteriaFailedException("Invalid Citizen Id");
		}
		
		SecondDose sDose = s.get(SecondDose.class, cz.getCitizenId());
		
//		if(!sDose.getDoseName().equals(boosterDose.getDoseName())) {
//			return sDose.getDoseName() 
//					+ " was taken in second dose";
//		}
		long daysinbetween = ChronoUnit.DAYS.between(sDose.getDoseDate(), boosterDose.getDoseDate());
		if(daysinbetween < 150) {
			String str = "Wait for another " + (150-daysinbetween) + " days before getting second dose";
			throw new EligibilityCriteriaFailedException(str);
		}
		
		s.merge(boosterDose);
		
		return "success";
	}
	@Override
	public ArrayList<BoosterDose> getBoosterDoses() {
		// TODO Auto-generated method stub
		Session s = entityManager.unwrap(Session.class);
		Query theQuery = s.createQuery("from BoosterDose", BoosterDose.class);
		ArrayList<BoosterDose> bDoses = (ArrayList<BoosterDose>) theQuery.getResultList();
		return bDoses;
	}
	
	@Transactional
	@Override
	public String updateCitizen(Citizen c1) {
		// TODO Auto-generated method stub
		Session s = entityManager.unwrap(Session.class);
		s.merge(c1);
		return "Citizen updated successful!";
	}
	
	
	@Override
	public String getStatusOfCitizen(long citizenId) {
		// TODO Auto-generated method stub
		Session s = entityManager.unwrap(Session.class);
		Citizen cz = s.get(Citizen.class, citizenId);
		if(cz.getDosesTaken() ==  0) {
			return "Not Vaccinated";
		} else if (cz.getDosesTaken() == 1) {
			return "Partially Vaccinated";
		} else if (cz.getDosesTaken() == 2) {
			return "Fully Vaccinated";
		} else if (cz.getDosesTaken() == 3) {
			return "Fully + Booster Dose Taken";
		} else {
			return "Something went wrong";
		}
		
	}
	

	
	
}
