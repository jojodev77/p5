package p5safetyNetIntegration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.PersonService;
import p5SafetyNet.p5SafetyNet.servicesImpl.PersonServiceImpl;

@ExtendWith(MockitoExtension.class)
public class P5safetyNetIntegrationIT {
	@Mock
	PersonServiceImpl personService;

	@Mock
	PersonsRepository personRepository;

	Persons persons1;
	Persons persons2;

	@Mock
	List<Persons> listPersons = new ArrayList<Persons>();

	@BeforeEach
	private void setUpPerTest() throws Exception {
		persons1 = new Persons((long) 1, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512",
				"jaboyd@email.com");
	}

	/***
	 * @throws Exception
	 * @Description add, update and delete persons
	 */
	@Test
	public void createAndUpdateAndDeletePersons() throws Exception {
		// GIVEN
		listPersons.add(persons1);
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		lenient().when(personRepository.save(any())).thenReturn(persons1);
		// WHEN
		personService.createPersons(persons1);
		// THEN
		verify(personService).createPersons(persons1);
		
		
		// GIVEN
		//persons1.setLastName("jojo");
		listPersons.add(persons1);
		lenient().when(personRepository.save(any())).thenReturn(persons1);

		// WHEN
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		personService.updatePersons(persons1);
		// THEN
		verify(personService).updatePersons(persons1);
		// GIVEN
		long id = persons1.getId();
		Optional<Persons> p = Optional.of(persons1);
		lenient().when(personRepository.save(any())).thenReturn(persons1);
		// WHEN
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		lenient().when(personRepository.findById(any())).thenReturn(p);
		personService.deletePersons(persons1.getId());
		personRepository.delete(persons1);
		Optional<Persons> pers = personRepository.findById(persons1.getId());
		
		// THEN
		assertEquals(null, pers.get());
	}

}
