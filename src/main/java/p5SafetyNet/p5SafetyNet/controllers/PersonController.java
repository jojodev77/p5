package p5SafetyNet.p5SafetyNet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.services.PersonService;


@RestController
@RequestMapping
public class PersonController {

	@Autowired
	PersonService personService;
	
	@PostMapping("/person")
	public Persons addPerson(@RequestBody Persons person) throws Exception {
		return personService.createPersons(person);
	}
	
	@PutMapping("/person")
	public void updatePerson(@RequestBody Persons persons) throws Exception {
		personService.updatePersons(persons);
	}
	
	@DeleteMapping("/person")
	public void deletePerson(@RequestParam long id) throws Exception {
		personService.deletePersons(id);
	}
	
	@PostMapping("/addListPersons")
	public void addListPerson() {
		personService.createListPersons();
	}
}
