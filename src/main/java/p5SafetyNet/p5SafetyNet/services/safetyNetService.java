package p5SafetyNet.p5SafetyNet.services;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;

public interface safetyNetService {
Persons createPersons(Persons persons) throws Exception;
Persons updatePersons(Persons persons) throws Exception;
Persons deletePersons(long id) throws Exception;

Medicalrecords createMecicalrecords(Medicalrecords medicalrecords) throws Exception;
Medicalrecords updateMecicalrecords(Medicalrecords medicalrecords) throws Exception;
Medicalrecords deleteMecicalrecords(long id) throws Exception;

Firestations createFirestations(Firestations firestations) throws Exception;
Firestations updateFirestations(Firestations firestations) throws Exception;
Firestations delteFirestations(long id) throws Exception;
}
