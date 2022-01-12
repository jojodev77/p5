package p5SafetyNet.p5SafetyNet;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.servicesImpl.ReadFileJsonImpl;

@RunWith(MockitoJUnitRunner.class)
public class ReadFileJsonServiceTest {
	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode node;
	
	@Mock
	private ObjectMapper mapper = new ObjectMapper();
	
	@Mock
	private JsonNode nodeMock;
	
	
	@Mock
	ReadFileJsonImpl readFileJsonImpl;
	
    @BeforeEach
    public void setUpPerTest() {

    	try {
			node = objectMapper.readTree(new File("src/main/resources/data.json"));
		//	nodeMock = objectMapper.readTree(new File("src/main/resources/data.json"));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    /**
     * @throws Exception 
     * @Description testPresencPersonInfileJson
     */
//    @Test
//    public void  callObjectPersonsInFileTest() throws Exception {
//    	//GIVEN
//    	boolean present = false;
//    	//WHEN
//    	
//    		//THEN
//    		lenient().when(nodeMock).thenReturn(objectMapper.readTree(new File("src/main/resources/data.json")));
//    		readFileJsonImpl.DataOfPersons();
//    		verify(readFileJsonImpl).DataOfPersons();
//    	
//    }
    /**
     * @Description testPresencPersonInfileJson
     */
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
    
    /**
     * @Description testPresencFirestationInfileJson
     */
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
    
    /**
     * @Description testPresencMedicalrecordInfileJson
     */
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