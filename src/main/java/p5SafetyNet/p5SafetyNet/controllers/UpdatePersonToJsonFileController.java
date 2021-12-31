package p5SafetyNet.p5SafetyNet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.servicesImpl.UpdatePersonToJsonFileImpl;

@RestController
@RequestMapping
public class UpdatePersonToJsonFileController {
	
	@Autowired
	UpdatePersonToJsonFileImpl updatePersonToJsonFileImpl;
	
	@PostMapping("/addPerson")
	public void addPerson(@RequestBody Persons person) throws Exception {
		 updatePersonToJsonFileImpl.addPerson(person);
	}
	
	@PatchMapping("/updatePerson")
	public void updatePerson(@RequestParam String lastName,@RequestParam String firstName) {
		 updatePerson(lastName, firstName);
	}
	
	@DeleteMapping("/deletePerson")
	public void deletePerson(@RequestParam String lastName,@RequestParam String firstName) {
		 updatePersonToJsonFileImpl.DeletePersons(lastName, firstName);
	}
	

}
