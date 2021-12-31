package p5SafetyNet.p5SafetyNet.services;

import p5SafetyNet.p5SafetyNet.entity.Persons;

public interface UpdatePersonToJsonFile {
public void addPerson(Persons persons) throws Exception;
public void updatePersons(String lastName, String firstName);
public void DeletePersons(String lastName, String firstName);
}
