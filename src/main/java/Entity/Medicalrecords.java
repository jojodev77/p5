package Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
 class Medicalrecords {
String firstName;
String lastName;
String birthdate;
String[] medications;
String [] allergies;
}
