package p5SafetyNet.p5SafetyNet.services;

import p5SafetyNet.p5SafetyNet.entity.Persons;

public interface PersonService {
	Persons createPersons(Persons persons) throws Exception;
	Persons updatePersons(Persons persons) throws Exception;
	Persons deletePersons(long id) throws Exception;
}
