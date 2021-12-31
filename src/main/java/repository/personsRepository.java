package repository;

import org.springframework.data.jpa.repository.JpaRepository;


import p5SafetyNet.p5SafetyNet.entity.Persons;

public interface personsRepository extends JpaRepository<Persons, Long>{

}
