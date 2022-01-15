package p5SafetyNet.p5SafetyNet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;


@Service
public class PersonService  {

	@Autowired
	PersonsRepository personsRepository;

	public Persons createPersons(Persons persons) {
		if (persons == null) {
			throw new RuntimeException("persons is null");
		} 
		
		Optional<Persons> p = Optional.ofNullable(personsRepository.findByLastNameAndFirstName(persons.getLastName(), persons.getFirstName()));
		if (!p.isPresent()) {
			personsRepository.save(persons);
		} else {
			throw new RuntimeException("persons exist in db");
		}
		return persons;
			
		
		
	}

	public Persons updatePersons(Persons persons)  {
		if (persons == null) {
			throw new RuntimeException("persons is null");
		}
			Optional<Persons> p = Optional.ofNullable(personsRepository.findByLastNameAndFirstName(persons.getLastName(), persons.getFirstName()));
			if (p.isPresent()) {
				personsRepository.save(p.get());
			} else {
				throw new RuntimeException("persons  not present in db");
			}
		return persons;
	}

	public Persons deletePersons(long id)  {
		if (id < 1) {
			throw new RuntimeException("persons is null");
		}
		Optional<Persons> p = Optional.ofNullable(personsRepository.findById(id));
		if (p.isPresent()) {
			personsRepository.delete(p.get());
		} else {
			throw new RuntimeException("persons  not present in db");
		}

		return p.get();
	}
}
