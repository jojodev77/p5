package p5SafetyNet.p5SafetyNet.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.repository.FirestationRepository;
import p5SafetyNet.p5SafetyNet.services.FirestationService;

@Service
public class FirestationServiceImpl implements FirestationService{

	@Autowired
	FirestationRepository firestationRepository;
	
	@Override
	public Firestations createFirestations(Firestations firestations) throws Exception {
		if (firestations == null) {
			throw new Exception("firestations is null");
		} 
		for(Firestations f: firestationRepository.findAll()) {
			if (f.getAddress() == firestations.getAddress() && f.getStation() == firestations.getStation()) {
				throw new Exception("persons exist in database");
			} else {
				firestationRepository.save(firestations);
			}
		}
		return null;
	}
	

	@Override
	public Firestations updateFirestations(Firestations firestations) throws Exception {
		if (firestations == null) {
			throw new Exception("firestations is null");
		} 
		for(Firestations f: firestationRepository.findAll()) {
			if (f.getAddress() != firestations.getAddress() && f.getStation() != firestations.getStation()) {
				throw new Exception("persons not exist in database");
			} else {
				Firestations med = firestationRepository.findByAddressAndStation(firestations.getAddress(), firestations.getStation());
				firestationRepository.save(med);
			}
		}
		return null;
	}

	@Override
	public Firestations deleteFirestations(long id) throws Exception {
		if (id > 0) {
			throw new Exception("firestations is null");
		} 
		Firestations pers = firestationRepository.findById(id);
		if (pers == null) {
			throw new Exception("not firestations with id");
		} else {
			firestationRepository.delete(pers);
		}
		return null;
	}

}
