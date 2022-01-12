package p5SafetyNet.p5SafetyNetIntegration.p5SafetyNet;

import static org.mockito.Mockito.lenient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.servicesImpl.PersonServiceImpl;





@ExtendWith(MockitoExtension.class)
public class p5SafetyNetIntegrationIT {

	
	@Mock
	PersonsRepository personRepository;

	@Mock
	PersonServiceImpl personService;
	
	Persons persons1;
	Persons persons2;
	
	@Mock
	List<Persons> listPersons = new ArrayList<Persons>();
	
	@BeforeEach
	private void setUpPerTest() throws Exception {
		persons1 = new Persons((long) 1, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512",
				"jaboyd@email.com");

		persons2 = new Persons((long) 0, "", "", "1509 Culver St", "", 97451, "841-874-6512", "");
	}

	
	/**
	 * 
	 * @throws Exception 
	 * @Description test integration circuit 1
	 * 
	 */
	@Test
	public void integerPersonAndFirestationAndMedicalRecordAndGetFirestation() throws Exception {
		//GIVEN
		listPersons.add(persons2);
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		//WHEN
		personService.createPersons(persons2);
		
		//THEN
	}

}
