package p5SafetyNet.p5SafetyNet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.services.FirestationService;

@RestController
@RequestMapping
public class FirestationController {
	
	@Autowired
	FirestationService firestationService;
	
	@PostMapping("/addFirestation")
	public void addFirestation(@RequestBody Firestations firestations) throws Exception {
		firestationService.createFirestations(firestations);
	}
	
	@PutMapping("/updateFirestation")
	public void updateFirestation(@RequestBody Firestations firestations) throws Exception {
		firestationService.updateFirestations(firestations);
	}
	
	@DeleteMapping("/deleteFirestation")
	public void deleteFirestation(@RequestParam long id) throws Exception {
		firestationService.deleteFirestations(id);
	}
	
	@PostMapping("/addListFirestation")
	public void addListFirestation() {
		firestationService.createListFireStation();
	}
}
