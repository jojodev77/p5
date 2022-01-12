package p5SafetyNet.p5SafetyNet.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

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
@Table(name = "medicalrecords")
public class Medicalrecords {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 Long id;
	String firstName;
	String lastName;
	@JsonFormat(pattern = "dd/MM/yyyy")
	Date birthdate;
	String[] medications;
	String[] allergies;
	int age;
}
