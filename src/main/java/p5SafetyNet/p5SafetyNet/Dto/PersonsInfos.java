package p5SafetyNet.p5SafetyNet.Dto;

import lombok.Data;

@Data
public class PersonsInfos {
	String lastName;
	String address;
	long age;
	String[] medications;
	String[] allergies;
}
