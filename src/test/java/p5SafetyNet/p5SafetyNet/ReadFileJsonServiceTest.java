package p5SafetyNet.p5SafetyNet;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;

@ExtendWith(MockitoExtension.class)
public class ReadFileJsonServiceTest {
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
    
//    /**
//     * @description verify if data json of person is exist
//     */
//    @Test
//    public void  existObjectPersonsInFileTest() {
//    	//GIVEN
//    	boolean present = false;
//    	//WHEN
//    	for (final JsonNode objNode : node.get("persons")) {
//    		//THEN
//    		if (objNode != null) {
//    			present = true;
//			}
//    		
//    		assertEquals(true, present);
//		}
//    	
//    }
//    
//    /**
//     * @description verify if data json of person is not a person
//     */
//    @Test
//    public void  existObjectPersonsInFileIsNotTypeOfPersonTest() {
//    	//GIVEN
//    	boolean present = false;
//    	//WHEN
//    	for (final JsonNode objNode : node.get("firestations")) {
//    		//THEN
//    		
//    		
//    		assertNotEquals(objNode, instanceOf(Persons.class));
//    		
//		}
//    	
//    }
//    
//    /**
//     * @description verify if data json of firestation is exist
//     */
//    @Test
//    public void  existObjectFirestationTest() {
//    	//GIVEN
//    	boolean present = false;
//    	//WHEN
//    	for (final JsonNode objNode : node.get("firestations")) {
//    		//THEN
//    		if (objNode != null) {
//    			present = true;
//			}
//				
//    		
//    		assertEquals(true, present);
//		}
//    	
//    }
//    
//    /**
//     * @description verify if data json of firestation is not type of firestation
//     */
//    @Test
//    public void  existObjectFirestationIsNotTypeOfPersonTest() {
//    	//GIVEN
//    	boolean present = false;
//    	//WHEN
//    	for (final JsonNode objNode : node.get("firestations")) {
//    		//THEN
//    		assertNotEquals(objNode, instanceOf(Firestations.class));
//		}
//    	
//    }
//    
//    /**
//     * @description verify if data json of medicalRecord is exist
//     */
//    @Test
//    public void  existObjectMedicalRecordTest() {
//    	//GIVEN
//    	boolean present = false;
//    	//WHEN
//    	for (final JsonNode objNode : node.get("medicalrecords")) {
//    		//THEN
//    		if (objNode != null) {
//				present = true;
//    		}
//    		
//    		assertEquals(true, present);
//		}
//    	
//    }
//    
//    /**
//     * @description verify if data json of medicalRecord is not type of medicalRecord
//     */
//    @Test
//    public void  existObjectMedicalRecordIsNotTypeOfMedicalrecordTest() {
//    	//GIVEN
//    	boolean present = false;
//    	//WHEN
//    	for (final JsonNode objNode : node.get("medicalrecords")) {
//    		//THEN
//    		assertNotEquals(objNode, instanceOf(Medicalrecords.class));
//		}
//    	
//    }
 
}
