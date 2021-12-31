package p5SafetyNet.p5SafetyNet.services;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;

public interface ReadFileJson {
List<Firestations> getDataOfFirestations() throws JsonParseException, JsonMappingException, IOException, Exception;
List<Persons> DataOfPersons() throws JsonProcessingException, IllegalArgumentException, IOException, Exception;
List<Medicalrecords> DataOfMedicalRecords() throws JsonProcessingException, IllegalArgumentException, IOException, Exception;
}
