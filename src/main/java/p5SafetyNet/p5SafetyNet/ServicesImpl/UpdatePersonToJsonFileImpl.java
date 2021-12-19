package p5SafetyNet.p5SafetyNet.ServicesImpl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import p5SafetyNet.p5SafetyNet.Entity.Persons;
import p5SafetyNet.p5SafetyNet.Services.UpdatePersonToJsonFile;

@Service
public class UpdatePersonToJsonFileImpl implements UpdatePersonToJsonFile {

	@Autowired
	ReadFileJsonImpl readFileJson;

	@Override
	public void addPerson(Persons persons) throws Exception {

		List<Persons> listPersons = new ArrayList<Persons>();
		listPersons = readFileJson.DataOfPersons().stream().collect(Collectors.toList());
		listPersons.add(persons);

		ObjectMapper objectMapper = new ObjectMapper();
//			JsonNode node = objectMapper.readTree(new File("src/main/resources/data.json"));

		FileReader reader = new FileReader("src/main/resources/data.json");
//			JsonObject  root = gson.fromJson(reader, JsonObject.class);
//			JsonObject pe = (JsonObject) root.getAsJsonObject().get("persons");
//			
//			
//			try {
//	            String jsonString = gson.toJson(listPersons);
//	            FileWriter writer = new FileWriter("src/main/resources/datas.json");
//	            
//	            writer.write(jsonString);
//	            writer.close();
//	        } catch (IOException e) {
//	            System.out.println("exception " + e.getMessage());
//	            e.printStackTrace();
//	        }
		File jsonFile = new File("src/main/resources/data.json").getAbsoluteFile();

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject root = gson.fromJson(Files.newBufferedReader(jsonFile.toPath()), JsonObject.class);
		JsonNode node = objectMapper.readTree(new File("src/main/resources/data.json"));

		ObjectMapper mapper = new ObjectMapper();
		 objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
		ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
		writer.writeValue(new File("src/main/resources/datas.json"), ( listPersons));

	}

	@Override
	public void updatePersons(String lastName, String firstName) {
		// TODO Auto-generated method stub
	}

	@Override
	public void DeletePersons(String lastName, String firstName) {
		// TODO Auto-generated method stub
	}

}
