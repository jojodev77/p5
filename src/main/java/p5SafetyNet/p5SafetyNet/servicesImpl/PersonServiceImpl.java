package p5SafetyNet.p5SafetyNet.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	PersonsRepository personsRepository;
	
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
}
