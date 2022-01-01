package p5SafetyNet.p5SafetyNet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import p5SafetyNet.p5SafetyNet.entity.Persons;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Long>{
Persons findByLastNameAndFirstName(String lastName, String firstName);
Persons findById(long id);
}
