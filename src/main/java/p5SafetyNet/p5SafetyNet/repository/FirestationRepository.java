package p5SafetyNet.p5SafetyNet.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p5SafetyNet.p5SafetyNet.entity.Firestations;


@Repository
public interface FirestationRepository extends JpaRepository<Firestations, Long> {
	Firestations findByAddressAndStation(String address, int station);

	Firestations findById(long id);

	ArrayList<Firestations> findByStation(int station);

	ArrayList<Firestations> findByAddress(String addess);
}
