package p5SafetyNet.p5SafetyNet.Services;

import p5SafetyNet.p5SafetyNet.Entity.Persons;

public interface UpdatePersonToJsonFile {
public void addPerson(Persons persons) throws Exception;
public void updatePersons(String lastName, String firstName);
public void DeletePersons(String lastName, String firstName);
}
