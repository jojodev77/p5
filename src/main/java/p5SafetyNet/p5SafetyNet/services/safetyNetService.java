package p5SafetyNet.p5SafetyNet.services;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;

public interface safetyNetService {
Persons createPersons(Persons persons);
Persons updatePersons(Persons persons);
Persons deletePersons(long id);

Medicalrecords createMecicalrecords(Medicalrecords medicalrecords);
Medicalrecords updateMecicalrecords(Medicalrecords medicalrecords);
Medicalrecords deleteMecicalrecords(long id);

Firestations createFirestations(Firestations firestations);
Firestations updateFirestations(Firestations firestations);
Firestations delteFirestations(long id);
}
