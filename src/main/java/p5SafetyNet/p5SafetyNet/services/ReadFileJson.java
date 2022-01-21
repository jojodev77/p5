package p5SafetyNet.p5SafetyNet.services;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;


@Service
public class ReadFileJson {

	/**
	 * @author j.de-la-osa
	 * @param fichier data.json
	 * @return read file json and extract firestation in java object
	 * @throws Exception 
	 */
	public List<Firestations> getDataOfFirestations() {
		List<Firestations> listFirestation = new ArrayList<Firestations>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = objectMapper.readTree(new File("src/main/resources/data.json"));
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (final JsonNode objNode : node.get("firestations")) {
			try {
				listFirestation.add(objectMapper.treeToValue(objNode, Firestations.class));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return listFirestation;
	}

	/**
	 * @author j.de-la-osa
	 * @param fichier data.json
	  * @return read file json and extract Persons in java object
	 * @throws Exception 
	 */
	public List<Persons> DataOfPersons()  {
		List<Persons> listPersons = new ArrayList<Persons>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = objectMapper.readTree(new File("src/main/resources/data.json"));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (final JsonNode objNode : node.get("persons")) {
			try {
				listPersons.add(objectMapper.treeToValue(objNode, Persons.class));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return listPersons;
	}

	/**
	 * @author j.de-la-osa
	 * @param fichier data.json
	 * @return read file json and extract Medicalrecords in java object
	 * @throws Exception 
	 */
	public List<Medicalrecords> DataOfMedicalRecords()  {
		List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();
		ObjectMapper objectMapper =   new ObjectMapper();
		objectMapper.findAndRegisterModules();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		JsonNode node = null;
		try {
			node = objectMapper.readTree(new File("src/main/resources/data.json"));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (final JsonNode objNode : node.get("medicalrecords")) {
			try {
				listMedicalRecord.add(objectMapper.treeToValue(objNode, Medicalrecords.class));
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return listMedicalRecord;
	}

}
