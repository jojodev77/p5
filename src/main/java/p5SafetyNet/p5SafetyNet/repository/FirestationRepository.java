package p5SafetyNet.p5SafetyNet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Persons;


@Repository
public interface FirestationRepository extends JpaRepository<Firestations, Long> {
Firestations findByAddressAndStation(String address, int station);
Firestations findById(long id);
}
