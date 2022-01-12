package p5SafetyNet.p5SafetyNet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import p5SafetyNet.p5SafetyNet.entity.Firestations.FirestationsBuilder;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "persons")
public class Persons {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
	String firstName;
	String lastName;
	String address;
	String city;
	int zip;
	String phone;
	String email;
}
