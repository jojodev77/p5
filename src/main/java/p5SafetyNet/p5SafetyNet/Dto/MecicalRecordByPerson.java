package p5SafetyNet.p5SafetyNet.Dto;

import lombok.Data;

@Data
public class MecicalRecordByPerson {
String lastName;
PhoneNumber phoneNumber;
int age;
String[] medications;
String[] allergies;
}
