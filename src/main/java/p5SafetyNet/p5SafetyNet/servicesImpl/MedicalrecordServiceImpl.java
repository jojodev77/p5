package p5SafetyNet.p5SafetyNet.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;
import p5SafetyNet.p5SafetyNet.services.MedicalrecordsService;

@Service
public class MedicalrecordServiceImpl implements MedicalrecordsService {

	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	public Medicalrecords createMecicalrecords(Medicalrecords medicalrecords) throws Exception {
		if (medicalrecords == null) {
			throw new Exception("medicalrecords is null");
		} 
		Optional<Medicalrecords>m = Optional.ofNullable(medicalRecordRepository.findByLastNameAndFirstName(medicalrecords.getLastName(), medicalrecords.getFirstName()));
		if (!m.isPresent()) {
				medicalRecordRepository.save(medicalrecords);
			} else {
				throw new Exception("medicalrecord  is present in db");
			}
		return medicalrecords;
	}

	public Medicalrecords updateMecicalrecords(Medicalrecords medicalrecords) throws Exception {
		if (medicalrecords == null) {
			throw new Exception("persons is null");
		}
			Optional<Medicalrecords>m = Optional.ofNullable(medicalRecordRepository.findByLastNameAndFirstName(medicalrecords.getLastName(), medicalrecords.getFirstName()));
			if (!m.isPresent()) {
				medicalRecordRepository.save(medicalrecords);
			} else {
				throw new Exception("medicalrecord  not present in db");
			}
		return null;
	}

	public Medicalrecords deleteMecicalrecords(long id) throws Exception {
		if (id < 0) {
			throw new Exception("persons is null");
		} 
		Optional<Medicalrecords> pers = Optional.of(medicalRecordRepository.findById(id));
		if (!pers.isPresent()) {
			throw new Exception("not medicalrecords with id");
		} else {
			medicalRecordRepository.delete(pers.get());
		}
		return null;
	}

}
