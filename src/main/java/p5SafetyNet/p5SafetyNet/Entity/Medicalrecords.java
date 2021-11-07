package p5SafetyNet.p5SafetyNet.Entity;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import p5SafetyNet.p5SafetyNet.Entity.Firestations.FirestationsBuilder;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
 public class Medicalrecords {
String firstName;
String lastName;
@JsonFormat(pattern = "dd/MM/yyyy")
Date birthdate;
String[] medications;
String [] allergies;
int age;
}
