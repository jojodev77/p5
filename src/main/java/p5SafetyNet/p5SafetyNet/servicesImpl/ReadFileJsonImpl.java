package p5SafetyNet.p5SafetyNet.servicesImpl;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.services.ReadFileJson;

@Service
public class ReadFileJsonImpl implements ReadFileJson {

	/**
	 * @author j.de-la-osa
	 * @param fichier data.json
	 * @return read file json and extract firestation in java object
	 * @throws Exception 
	 */

	@Override
	public List<Firestations> getDataOfFirestations() throws Exception {
		List<Firestations> listFirestation = new ArrayList<Firestations>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(new File("src/main/resources/data.json"));
		if (node == null) {
			throw new Exception("file as null");
		}
		for (final JsonNode objNode : node.get("firestations")) {
			listFirestation.add(objectMapper.treeToValue(objNode, Firestations.class));

		}
		return listFirestation;
	}

	/**
	 * @author j.de-la-osa
	 * @param fichier data.json
	  * @return read file json and extract Persons in java object
	 * @throws Exception 
	 */
	@Override
	public List<Persons> DataOfPersons() throws Exception {
		List<Persons> listPersons = new ArrayList<Persons>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode node = objectMapper.readTree(new File("src/main/resources/data.json"));
		for (final JsonNode objNode : node.get("persons")) {
			listPersons.add(objectMapper.treeToValue(objNode, Persons.class));

		}
		return listPersons;
	}

	/**
	 * @author j.de-la-osa
	 * @param fichier data.json
	 * @return read file json and extract Medicalrecords in java object
	 * @throws Exception 
	 */
	@Override
	public List<Medicalrecords> DataOfMedicalRecords() throws Exception {
		List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();
		ObjectMapper objectMapper =   new ObjectMapper();
		objectMapper.findAndRegisterModules();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		JsonNode node = objectMapper.readTree(new File("src/main/resources/data.json"));
		if (node == null) {
			throw new Exception("file as null");
		}
		for (final JsonNode objNode : node.get("medicalrecords")) {
			listMedicalRecord.add(objectMapper.treeToValue(objNode, Medicalrecords.class));

		}
		return listMedicalRecord;
	}

}
