package p5SafetyNet.p5SafetyNet.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;


@Service
public class MedicalrecordService  {

	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
	public Medicalrecords createMecicalrecords(Medicalrecords medicalrecords)  {
		if (medicalrecords == null) {
			throw new RuntimeException("medicalrecords is null");
		} 
		Optional<Medicalrecords>m = Optional.ofNullable(medicalRecordRepository.findByLastNameAndFirstName(medicalrecords.getLastName(), medicalrecords.getFirstName()));
		if (!m.isPresent()) {
				medicalRecordRepository.save(medicalrecords);
			} else {
				throw new RuntimeException("medicalrecord  is present in db");
			}
		return medicalrecords;
	}

	public Medicalrecords updateMecicalrecords(Medicalrecords medicalrecords){
		if (medicalrecords == null) {
			throw new RuntimeException("medicalrecord is null");
		}
			Optional<Medicalrecords>m = Optional.ofNullable(medicalRecordRepository.findByLastNameAndFirstName(medicalrecords.getLastName(), medicalrecords.getFirstName()));
			if (!m.isPresent()) {
				medicalRecordRepository.save(medicalrecords);
			} else {
				throw new RuntimeException("medicalrecord  not present in db");
			}
		return null;
	}

	public Medicalrecords deleteMecicalrecords(long id) {
		if (id < 1) {
			throw new RuntimeException("medicalrecord is null");
		} 
		Optional<Medicalrecords> me = Optional.of(medicalRecordRepository.findById(id));
		if (!me.isPresent()) {
			throw new RuntimeException("not medicalrecords with id");
		} else {
			medicalRecordRepository.delete(me.get());
		}
		return null;
	}

}
