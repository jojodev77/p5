package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;

public interface medicalRecordRepository extends JpaRepository<Medicalrecords, Long>{

}
