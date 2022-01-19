package p5SafetyNet.p5SafetyNet.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import p5SafetyNet.p5SafetyNet.entity.Persons;

@Repository
public interface PersonsRepository extends JpaRepository<Persons, Long>{
Persons findByLastNameAndFirstName(String lastName, String firstName);
Persons findById(long id);
@Query("Select p from Persons p  left join Firestations b on p.address=b.address WHERE b.station = :station")
ArrayList<Persons> findByStation(@Param("station") int station);
ArrayList<Persons> findByAddress(String addres);
}
