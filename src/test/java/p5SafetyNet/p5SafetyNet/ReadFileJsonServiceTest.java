package p5SafetyNet.p5SafetyNet;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.services.ReadFileJson;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.isA;

@ExtendWith(MockitoExtension.class)
public class ReadFileJsonServiceTest {
	ObjectMapper objectMapper = new ObjectMapper();
	JsonNode node;
	
	@Mock
	private ObjectMapper mapper = new ObjectMapper();
	
	@Mock
	private JsonNode nodeMock;
	
	@Mock
	 JsonNode objNode;
	@Spy
	@InjectMocks
	private static ReadFileJson readFileJsonImpl = new ReadFileJson();
	
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
    	 MockitoAnnotations.initMocks(this);
    }
    /**
     * @Description testPresencPersonInfileJson
     */
    @Test
    public void  existObjectPersonsInFileTest() {
    	readFileJsonImpl.DataOfPersons();
    	verify(readFileJsonImpl).DataOfPersons();
    }
    
    /**
     * @Description testPresencFirestationInfileJson
     */
    @Test
    public void  existObjectFirestationTest() {
    	readFileJsonImpl.getDataOfFirestations();
    	verify(readFileJsonImpl).getDataOfFirestations();
    	
    }
    
    /**
     * @Description testPresencMedicalrecordInfileJson
     */
    @Test
    public void  existObjectMedicalRecordTest() {
    	readFileJsonImpl.DataOfMedicalRecords();
    	verify(readFileJsonImpl).DataOfMedicalRecords();
    	
    }
    
    /**
     * @Description testOfTypeMedicalrecordInfileJson
     */
    @Test
    public void  typeObjectMedicalRecordTest() {
    	readFileJsonImpl.DataOfMedicalRecords();
    	assertEquals(readFileJsonImpl.DataOfMedicalRecords().get(0).getClass(), Medicalrecords.class);
    	
    }
    
    /**
     * @Description testOfTypeFirestationInfileJson
     */
    @Test
    public void  typeObjectFirestationTest() {
    	readFileJsonImpl.getDataOfFirestations();
    	assertEquals(readFileJsonImpl.getDataOfFirestations().get(0).getClass(), Firestations.class);
    	
    }
    
    
    /**
     * @Description testOfTypePersonsInfileJson
     */
    @Test
    public void  typeObjectPersonsTest() {
    	readFileJsonImpl.DataOfPersons();
    	assertEquals(readFileJsonImpl.DataOfPersons().get(0).getClass(), Persons.class);
    	
    }
 
}