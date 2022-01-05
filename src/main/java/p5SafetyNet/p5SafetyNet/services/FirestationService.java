package p5SafetyNet.p5SafetyNet.services;

import p5SafetyNet.p5SafetyNet.entity.Firestations;

public interface FirestationService {
	Firestations createFirestations(Firestations firestations) throws Exception;
	Firestations updateFirestations(Firestations firestations) throws Exception;
	Firestations delteFirestations(long id) throws Exception;
}
