package p5SafetyNet.p5SafetyNet.Dto;

import p5SafetyNet.p5SafetyNet.Entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.Entity.Persons;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class CoveragePersonsOfStation {
List<CoveragePersonsInformations> person = new ArrayList<CoveragePersonsInformations>();
long majorPersons;
long childPersons;


}
