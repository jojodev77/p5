package p5SafetyNet.p5SafetyNet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.servicesImpl.SafetyNetServiceImpl;
import p5SafetyNet.p5SafetyNet.servicesImpl.UpdatePersonToJsonFileImpl;

@RestController
@RequestMapping
public class safetyNetCRUD {
	
	@Autowired
	SafetyNetServiceImpl safetyNetServiceImpl;
	
	@PostMapping("/person")
	public void addPerson(@RequestBody Persons person) throws Exception {
		safetyNetServiceImpl.createPersons(person);
	}
	
	@PatchMapping("/person")
	public void updatePerson(@RequestParam Persons persons) throws Exception {
		safetyNetServiceImpl.updatePersons(persons);
	}
	
	@DeleteMapping("/person")
	public void deletePerson(@RequestParam long id) throws Exception {
		safetyNetServiceImpl.deletePersons(id);
	}
	
	@PostMapping("/firestation")
	public void addFirestation(@RequestBody Firestations firestations) throws Exception {
		safetyNetServiceImpl.createFirestations(firestations);
	}
	
	@PatchMapping("/firestation")
	public void updateFirestation(@RequestParam Firestations firestations) throws Exception {
		safetyNetServiceImpl.updateFirestations(firestations);
	}
	
	@DeleteMapping("/firestation")
	public void deleteFirestation(@RequestParam long id) throws Exception {
		safetyNetServiceImpl.delteFirestations(id);
	}
	
	@PostMapping("/medicalRecord")
	public void addMedicalRecord(@RequestBody Medicalrecords medicalrecords) throws Exception {
		safetyNetServiceImpl.createMecicalrecords(medicalrecords);
	}
	
	@PatchMapping("/medicalRecord")
	public void updateMedicalRecord(@RequestParam Medicalrecords medicalrecords) throws Exception {
		safetyNetServiceImpl.updateMecicalrecords(medicalrecords);
	}
	
	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestParam long id) throws Exception {
		safetyNetServiceImpl.deleteMecicalrecords(id);
	}
	

}
