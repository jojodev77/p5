package p5SafetyNet.p5SafetyNet;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.AlertService;
import p5SafetyNet.p5SafetyNet.services.FirestationService;
import p5SafetyNet.p5SafetyNet.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	PersonsRepository personRepository;

	@Spy
	@InjectMocks
	private static PersonService personService = new PersonService();

	Persons persons1 = new Persons((long) 1, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512",
			"jaboyd@email.com");;
	Persons persons2;

	List<Persons> listPersons = new ArrayList<Persons>();

	@BeforeEach
	public void setUpPerTest() throws Exception {

		persons2 = new Persons((long) 0, "", "", "1509 Culver St", "", 97451, "841-874-6512", "");
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	public static void setBef() {

	}

	/**
	 * @throws Exception
	 * @Description create person with succes
	 */
	@Test
	public void createPersonWithSucces() {
		// GIVEN
		lenient().when(personRepository.findByLastNameAndFirstName(persons1.getLastName(), persons1.getFirstName()))
				.thenReturn(null);
		// WHEN
		personService.createPersons(persons1);
		// THEN
		verify(personService).createPersons(persons1);
	}

	/**
	 * @throws Exception
	 * @Description create person with error
	 */
	@Test
	public void createPersonWithError() {
		// GIVEN
//		PersonService personService = Mockito.mock(PersonService.class);
		listPersons.add(persons1);
		lenient().when(personRepository.findByLastNameAndFirstName(persons1.getLastName(), persons1.getFirstName()))
				.thenReturn(persons1);

		// WHEN
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			personService.createPersons(persons1);
		});
		assertEquals("persons exist in db", exception.getMessage());
	}

	/**
	 * @throws Exception
	 * @Description update person with succes
	 */
	@Test
	public void updatePersonWithSucces() {
		// GIVEN
		// PersonService personService = Mockito.mock(PersonService.class);
		listPersons.add(persons2);
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		// WHEN
		personService.createPersons(persons2);
		// THEN
		verify(personService).createPersons(persons2);
	}

	/**
	 * @throws Exception
	 * @Description update person with error
	 */
	@Test
	public void updatePersonWithError() {
		// GIVEN
//		PersonService personService = Mockito.mock(PersonService.class);
		lenient().when(personRepository.save(any())).thenReturn(persons1);

		// WHEN
		lenient().when(personRepository.findByLastNameAndFirstName(persons1.getLastName(), persons1.getFirstName()))
				.thenReturn(null);
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			personService.updatePersons(persons1);
		});
		assertEquals("persons  not present in db", exception.getMessage());
	}

	/**
	 * @throws Exception
	 * @Description delete person with succes
	 */
//	@Test
//	public void deletePersonWithSucces() {
//		// GIVEN
//		lenient().when(personRepository.findByLastNameAndFirstName(persons1.getLastName(), persons1.getFirstName()))
//				.thenReturn(null);
//// WHEN
//		personService.createPersons(persons1);
//		// WHEN
//		lenient().when(personRepository.findById(persons1.getId())).thenReturn(Optional.of(persons1));
//		personService.deletePersons(persons1.getId());
//		// THEN
//		verify(personService).deletePersons(persons1.getId());
//	}

	/**
	 * @throws Exception
	 * @Description delete person with error
	 */
	@Test
	public void deletePersonWithError() {
		// GIVEN
//		PersonService personService = Mockito.mock(PersonService.class);
		listPersons.add(persons2);
		long id = persons2.getId();
		Optional<Persons> p = Optional.of(persons2);
		lenient().when(personRepository.save(any())).thenReturn(persons2);

		// WHEN
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			personService.deletePersons(id);
		});
		assertEquals("persons is null", exception.getMessage());
	}

}
