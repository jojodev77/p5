package p5SafetyNet.p5SafetyNet;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	PersonsRepository personRepository;

	@Mock
	PersonService personService;

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
	 * @throws Exception
	 * @Description create person with succes
	 */
	@Test
	public void createPersonWithSucces() throws Exception {
		// GIVEN
		listPersons.add(persons2);
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		// WHEN
		personService.createPersons(persons2);
		// THEN
		verify(personService).createPersons(persons2);
	}
	

	/**
	 * @throws Exception
	 * @Description create person with error
	 */
	@Test
	public void createPersonWithError() throws Exception {
		// GIVEN
		listPersons.add(persons1);
		lenient().when(personRepository.save(any())).thenReturn(persons1);
	
		// WHEN
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		personService.createPersons(persons1);
		// THEN
		assertEquals(personService.createPersons(persons1), null);
	}

}
