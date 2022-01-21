package p5SafetyNet.p5SafetyNet.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;

@Repository
public interface MedicalRecordRepository extends JpaRepository<Medicalrecords, Long> {
	Medicalrecords findByLastNameAndFirstName(String lastName, String firstName);

	Medicalrecords findById(long id);

	ArrayList<Medicalrecords> findByLastName(String lastname);

	@Query("Select m from Medicalrecords m WHERE m.lastName = :lastName AND m.firstName = :firstName")
	ArrayList<Medicalrecords> findListByLastNameAndFirstName(@Param("lastName") String lastName,
			@Param("firstName") String firstName);
}
