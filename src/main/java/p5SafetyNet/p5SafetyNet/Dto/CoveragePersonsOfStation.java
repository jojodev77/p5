package p5SafetyNet.p5SafetyNet.Dto;

import p5SafetyNet.p5SafetyNet.Entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.Entity.Persons;

import java.util.List;

import lombok.Data;

@Data
public class CoveragePersonsOfStation {
CoveragePersonsInformations[] person;
long majorPersons;
long childPersons;
}
