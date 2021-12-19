package p5SafetyNet.p5SafetyNet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import p5SafetyNet.p5SafetyNet.Entity.Persons;

@ExtendWith(MockitoExtension.class)
public class ReadFileJsonImplTest {
	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode node;
	
	@Mock
	private ObjectMapper mapper = new ObjectMapper();
	
	@Mock
	private JsonNode nodeMock;
	
    @BeforeEach
    private void setUpPerTest() {

    	try {
			node = objectMapper.readTree(new File("src/main/resources/data.json"));
			nodeMock = objectMapper.readTree(new File("src/main/resources/data.json"));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
    public void  existObjectPersonsInFileTest() {
    	//GIVEN
    	boolean present = false;
    	//WHEN
    	for (final JsonNode objNode : node.get("persons")) {
    		//THEN
    		if (objNode != null) {
    			present = true;
			}
    		
    		assertEquals(true, present);
		}
    	
    }
    @Test
    public void  existObjectFirestationTest() {
    	//GIVEN
    	boolean present = false;
    	//WHEN
    	for (final JsonNode objNode : node.get("firestations")) {
    		//THEN
    		if (objNode != null) {
    			present = true;
			}
				
    		
    		assertEquals(true, present);
		}
    	
    }
    
    @Test
    public void  existObjectMedicalRecordTest() {
    	//GIVEN
    	boolean present = false;
    	//WHEN
    	for (final JsonNode objNode : node.get("medicalrecords")) {
    		//THEN
    		if (objNode != null) {
				present = true;
    		}
    		
    		assertEquals(true, present);
		}
    	
    }
 
}
