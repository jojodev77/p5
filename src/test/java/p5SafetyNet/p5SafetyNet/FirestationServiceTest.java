package p5SafetyNet.p5SafetyNet;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.repository.FirestationRepository;
import p5SafetyNet.p5SafetyNet.services.FirestationService;
import p5SafetyNet.p5SafetyNet.services.ReadFileJson;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

	@Mock
	FirestationRepository firestationRepository;

	@Spy
	@InjectMocks
	private static FirestationService firestationService = new FirestationService();

	Firestations firestation1;
	Firestations firestation2;
	Firestations firestation4;

	@Mock
	ReadFileJson readFileJson;

	@Mock
	List<Firestations> listFirestation = new ArrayList<Firestations>();

	@BeforeEach
	private void setUpPerTest() throws Exception {
		firestation1 = new Firestations((long) 1, "1509 Culver St", 3);
		firestation2 = new Firestations((long) 3, "", 1);
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws Exception
	 * @Description create person with succes
	 */
	@Test
	public void createFirestationWithSucces() throws Exception {
		// GIVEN
		lenient().when(
				firestationRepository.findByAddressAndStation(firestation1.getAddress(), firestation1.getStation()))
				.thenReturn(null);
		// WHEN
		firestationService.createFirestations(firestation1);
		// THEN
		verify(firestationService).createFirestations(firestation1);
	}

	/**
	 * @throws Exception
	 * @Description create person with error
	 */
	@Test
	public void createFirestationWithError() throws Exception {
		// GIVEN
		listFirestation.add(firestation1);
		lenient().when(
				firestationRepository.findByAddressAndStation(firestation1.getAddress(), firestation1.getStation()))
				.thenReturn(firestation1);

		// WHEN
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			firestationService.createFirestations(firestation1);
		});
		assertEquals("firestations  is present in db", exception.getMessage());
	}

	/**
	 * @throws Exception
	 * @Description update person with succes
	 */
	@Test
	public void updateFirestationWithSucces() throws Exception {
		// GIVEN
		listFirestation.add(firestation2);
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		// WHEN
		firestationService.createFirestations(firestation2);
		// THEN
		verify(firestationService).createFirestations(firestation2);
	}

	/**
	 * @throws Exception
	 * @Description update person with error
	 */
	@Test
	public void updateFirestationWithError() throws Exception {
		// GIVEN
//		PersonService personService = Mockito.mock(PersonService.class);
		lenient().when(firestationRepository.save(any())).thenReturn(firestation1);

		// WHEN
		lenient().when(
				firestationRepository.findByAddressAndStation(firestation1.getAddress(), firestation1.getStation()))
				.thenReturn(null);
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			firestationService.updateFirestations(firestation1);
		});
		assertEquals("firestations  not present in db", exception.getMessage());
	}

	/**
	 * @throws Exception
	 * @Description update person with error
	 */
	@Test
	public void updateFirestationWhenAdressAndStationisNullError() throws Exception {
		// GIVEN
		firestation4 = null;
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			firestationService.updateFirestations(firestation4);
		});
		assertEquals("firestations is null", exception.getMessage());
	}

	/**
	 * @throws Exception
	 * @Description delete person with succes
	 */
	@Test
	public void deleteFirestationWithSucces() throws Exception {
		firestationRepository.save(firestation2);
		lenient().when(firestationService.createFirestations(firestation2)).thenReturn(firestation2);
		lenient().when(firestationRepository.findById(firestation2.getId())).thenReturn(firestation2);
		Optional<Firestations> p = Optional.ofNullable(firestationRepository.findById(firestation2.getId()));
		if (firestation2.getId() > 0) {
			firestationRepository.deleteById(firestation2.getId());
			verify(firestationRepository).deleteById(firestation2.getId());
		}
	}

	/**
	 * @throws Exception
	 * @Description delete person with error
	 */
	@Test
	public void deleteFirestationWithError() throws Exception {
		listFirestation.add(firestation2);
		long id = firestation2.getId();
		Optional<Firestations> p = Optional.of(firestation2);
		lenient().when(firestationRepository.save(any())).thenReturn(firestation2);

		// WHEN
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			firestationService.deleteFirestations(id);
		});
		assertEquals("not firestations with id", exception.getMessage());
	}

	/**
	 * @throws Exception
	 * @Description delete person with error
	 */
	@Test
	public void deleteFirestationWhenIdIsBadError() throws Exception {
		listFirestation.add(firestation2);
		long id = -2;
		Optional<Firestations> p = Optional.of(firestation2);
		lenient().when(firestationRepository.save(any())).thenReturn(firestation2);

		// WHEN
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			firestationService.deleteFirestations(id);
		});
		assertEquals("firestations is null", exception.getMessage());
	}

	/**
	 * @Description test create list persons
	 * 
	 */
	@Test
	public void createListMdicalrecordWithSucces() {
		listFirestation.add(firestation1);
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);

		firestationService.createListFireStation();
		// THEN
		verify(firestationService).createListFireStation();
	}

	/**
	 * @Description test create list persons
	 * 
	 */
	@Test
	public void createListPersonsWithError() {
		listFirestation.add(firestation1);
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(null);

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			firestationService.createListFireStation();
		});
		assertEquals(null, exception.getMessage());
	}
}
