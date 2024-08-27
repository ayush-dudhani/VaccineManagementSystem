package com.wu.vaccine.VaccineManagementSystem.dao;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.wu.vaccine.VaccineManagementSystem.entity.Citizen;
import com.wu.vaccine.VaccineManagementSystem.entity.FirstDose;
import com.wu.vaccine.VaccineManagementSystem.entity.SecondDose;

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
			if(daysBetween >= 120) {
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
	public FirstDose addFirstDose(FirstDose firstDose) {
		Session s = entityManager.unwrap(Session.class);
		if(getCitizenById(firstDose.getCitizenId()) == null) {
			return null;
		}
		s.merge(firstDose);
		return firstDose;
	}
	
	@Override
	public ArrayList<FirstDose> getFirstDose() {
		Session s = entityManager.unwrap(Session.class);
		Query theQuery = s.createQuery("from FirstDose", FirstDose.class);
		ArrayList<FirstDose> fDoses = (ArrayList<FirstDose>) theQuery.getResultList();
		return fDoses;
		
	}
	

	
	
}
