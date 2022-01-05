package p5SafetyNet.p5SafetyNet.servicesImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;

public class MedicalrecordServiceImpl {

	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	public Medicalrecords createMecicalrecords(Medicalrecords medicalrecords) throws Exception {
		if (medicalrecords == null) {
			throw new Exception("medicalrecords is null");
		} 
		for(Medicalrecords m: medicalRecordRepository.findAll()) {
			if (m.getLastName() == medicalrecords.getLastName() && m.getFirstName() == medicalrecords.getFirstName()) {
				throw new Exception("medicalrecords exist in database");
			} else {
				medicalRecordRepository.save(medicalrecords);
			}
		}
			
		
		return medicalrecords;
	}

	public Medicalrecords updateMecicalrecords(Medicalrecords medicalrecords) throws Exception {
		if (medicalrecords == null) {
			throw new Exception("persons is null");
		} 
		for(Medicalrecords m: medicalRecordRepository.findAll()) {
			if (m.getLastName() != medicalrecords.getLastName() && m.getFirstName() != medicalrecords.getFirstName()) {
				throw new Exception("persons not exist in database");
			} else {
				Medicalrecords med = medicalRecordRepository.findByLastNameAndFirstName(medicalrecords.getLastName(), medicalrecords.getLastName());
				medicalRecordRepository.save(med);
			}
		}
		return null;
	}

	public Medicalrecords deleteMecicalrecords(long id) throws Exception {
		if (id > 0) {
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
