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
import p5SafetyNet.p5SafetyNet.servicesImpl.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

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
	

	/**
	 * @throws Exception
	 * @Description update person with succes
	 */
	@Test
	public void updatePersonWithSucces() throws Exception {
		// GIVEN
		listPersons.add(persons1);
		lenient().when(personRepository.save(any())).thenReturn(persons1);
	
		// WHEN
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		personService.updatePersons(persons1);
		// THEN
		verify(personService).updatePersons(persons1);
	}
	
	/**
	 * @throws Exception
	 * @Description update person with error
	 */
	@Test
	public void updatePersonWithError() throws Exception {
		// GIVEN
		listPersons.add(persons2);
		lenient().when(personRepository.save(any())).thenReturn(persons2);
	
		// WHEN
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		personService.updatePersons(persons2);
		// THEN
		assertEquals(personService.updatePersons(persons2), null);
	}
	
	/**
	 * @throws Exception
	 * @Description delete person with succes
	 */
	@Test
	public void deletePersonWithSucces() throws Exception {
		// GIVEN
		listPersons.add(persons1);
		long id = persons1.getId();
		Optional<Persons> p = Optional.of(persons1);
		lenient().when(personRepository.save(any())).thenReturn(persons1);
		// WHEN
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		lenient().when(personRepository.findById(any())).thenReturn(p);
		personService.deletePersons(persons1.getId());
		// THEN
		verify(personService).deletePersons(persons1.getId());
	}
	
	/**
	 * @throws Exception
	 * @Description delete person with error
	 */
	@Test
	public void deletePersonWithError() throws Exception {
		// GIVEN
		listPersons.add(persons2);
		long id = persons2.getId();
		Optional<Persons> p = Optional.of(persons2);
		lenient().when(personRepository.save(any())).thenReturn(persons2);
	
		// WHEN
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		personService.deletePersons(persons2.getId());
		// THEN
		assertEquals(personService.deletePersons(persons2.getId()), null);
	}

}
