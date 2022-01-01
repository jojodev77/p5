package p5SafetyNet.p5SafetyNet.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.FirestationRepository;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.safetyNetService;

public class SafetyNetServiceImpl implements safetyNetService {

	@Autowired
	FirestationRepository firestationRepository;
	
	@Autowired
	PersonsRepository personsRepository;
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	@Override
	public Persons createPersons(Persons persons) throws Exception {
		if (persons == null) {
			throw new Exception("persons is null");
		} 
		for(Persons p: personsRepository.findAll()) {
			if (p.getLastName() == persons.getLastName() && p.getFirstName() == persons.getFirstName()) {
				throw new Exception("persons exist in database");
			} else {
				personsRepository.save(persons);
			}
		}
			
		
		return persons;
	}

	@Override
	public Persons updatePersons(Persons persons) throws Exception {
		if (persons == null) {
			throw new Exception("persons is null");
		} 
		for(Persons p: personsRepository.findAll()) {
			if (p.getLastName() != persons.getLastName() && p.getFirstName() != persons.getFirstName()) {
				throw new Exception("persons not exist in database");
			} else {
				Persons pers = personsRepository.findByLastNameAndFirstName(persons.getLastName(), persons.getLastName());
				personsRepository.save(pers);
			}
		}
		return persons;
	}

	@Override
	public Persons deletePersons(long id) throws Exception {
		if (id > 0) {
			throw new Exception("persons is null");
		} 
		Persons pers = personsRepository.findById(id);
		if (pers == null) {
			throw new Exception("not persons with id");
		} else {
			personsRepository.delete(pers);
		}
		
		return pers;
	}

	@Override
	public Medicalrecords createMecicalrecords(Medicalrecords medicalrecords) throws Exception {
		if (medicalrecords == null) {
			throw new Exception("medicalrecords is null");
		} 
		for(Medicalrecords m: medicalRecordRepository.findAll()) {
			if (m.getLastName() == medicalrecords.getLastName() && m.getFirstName() == medicalrecords.getFirstName()) {
				throw new Exception("medicalrecords exist in database");
			} else {
				medicalRecordRepository.save(medicalrecords);
			}
		}
			
		
		return medicalrecords;
	}

	@Override
	public Medicalrecords updateMecicalrecords(Medicalrecords medicalrecords) throws Exception {
		if (medicalrecords == null) {
			throw new Exception("persons is null");
		} 
		for(Medicalrecords m: medicalRecordRepository.findAll()) {
			if (m.getLastName() != medicalrecords.getLastName() && m.getFirstName() != medicalrecords.getFirstName()) {
				throw new Exception("persons not exist in database");
			} else {
				Medicalrecords med = medicalRecordRepository.findByLastNameAndFirstName(medicalrecords.getLastName(), medicalrecords.getLastName());
				medicalRecordRepository.save(med);
			}
		}
		return null;
	}

	@Override
	public Medicalrecords deleteMecicalrecords(long id) throws Exception {
		if (id > 0) {
			throw new Exception("persons is null");
		} 
		Medicalrecords pers = medicalRecordRepository.findById(id);
		if (pers == null) {
			throw new Exception("not medicalrecords with id");
		} else {
			medicalRecordRepository.delete(pers);
		}
		return null;
	}

	@Override
	public Firestations createFirestations(Firestations firestations) throws Exception {
		if (firestations == null) {
			throw new Exception("firestations is null");
		} 
		for(Firestations f: firestationRepository.findAll()) {
			if (f.getAddress() == firestations.getAddress() && f.getStation() == firestations.getStation()) {
				throw new Exception("persons exist in database");
			} else {
				firestationRepository.save(firestations);
			}
		}
		return null;
	}

	@Override
	public Firestations updateFirestations(Firestations firestations) throws Exception {
		if (firestations == null) {
			throw new Exception("firestations is null");
		} 
		for(Firestations f: firestationRepository.findAll()) {
			if (f.getAddress() != firestations.getAddress() && f.getStation() != firestations.getStation()) {
				throw new Exception("persons not exist in database");
			} else {
				Firestations med = firestationRepository.findByAddressAndStation(firestations.getAddress(), firestations.getStation());
				firestationRepository.save(med);
			}
		}
		return null;
	}

	@Override
	public Firestations delteFirestations(long id) throws Exception {
		if (id > 0) {
			throw new Exception("firestations is null");
		} 
		Firestations pers = firestationRepository.findById(id);
		if (pers == null) {
			throw new Exception("not firestations with id");
		} else {
			firestationRepository.delete(pers);
		}
		return null;
	}

}
