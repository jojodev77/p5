package p5SafetyNet.p5SafetyNet.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.repository.FirestationRepository;

@Service
public class FirestationService {

	@Autowired
	FirestationRepository firestationRepository;

	public List<Firestations> listFirestation = new ArrayList<Firestations>();

	@Autowired
	ReadFileJson readFileJson;

	public Firestations createFirestations(Firestations firestations) {
		if (firestations == null) {
			throw new RuntimeException("firestations is null");
		}
		Optional<Firestations> f = Optional.ofNullable(
				firestationRepository.findByAddressAndStation(firestations.getAddress(), firestations.getStation()));

		if (!f.isPresent()) {
			firestationRepository.save(firestations);
		} else {
			throw new RuntimeException("firestations  is present in db");
		}
		return firestations;
	}

	public Firestations updateFirestations(Firestations firestations) {
		if (firestations == null) {
			throw new RuntimeException("firestations is null");
		}
		Optional<Firestations> f = Optional.ofNullable(
				firestationRepository.findByAddressAndStation(firestations.getAddress(), firestations.getStation()));

		if (f.isPresent()) {
			firestationRepository.save(f.get());
		} else {
			throw new RuntimeException("firestations  not present in db");
		}

		return firestations;
	}

	public Firestations deleteFirestations(long id) {
		if (id < 0) {
			throw new RuntimeException("firestations is null");
		}
		Optional<Firestations> fir = Optional.ofNullable(firestationRepository.findById(id));
		if (!fir.isPresent()) {
			throw new RuntimeException("not firestations with id");
		} else {
			firestationRepository.deleteById(fir.get().getId());
		}
		return null;
	}

	public void createListFireStation() {
		listFirestation = readFileJson.getDataOfFirestations().stream().collect(Collectors.toList());
		firestationRepository.saveAll(listFirestation);
	}

}
