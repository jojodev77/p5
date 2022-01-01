package p5SafetyNet.p5SafetyNet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;

@Repository
public interface MedicalRecordRepository extends JpaRepository<Medicalrecords, Long>{
	Medicalrecords findByLastNameAndFirstName(String lastName, String firstName);
	Medicalrecords findById(long id);
}
