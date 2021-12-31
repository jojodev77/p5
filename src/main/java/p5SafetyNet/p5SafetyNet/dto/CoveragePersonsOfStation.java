package p5SafetyNet.p5SafetyNet.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;

@Data
public class CoveragePersonsOfStation {
List<CoveragePersonsInformations> person = new ArrayList<CoveragePersonsInformations>();
long majorPersons;
long childPersons;


}
