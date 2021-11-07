package p5SafetyNet.p5SafetyNet.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import p5SafetyNet.p5SafetyNet.Entity.Firestations.FirestationsBuilder;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Persons {
String firstName;
String lastName;
String address;
String city;
int zip;
String phone;
String email;
}
