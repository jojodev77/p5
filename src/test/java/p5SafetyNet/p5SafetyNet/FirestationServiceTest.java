package p5SafetyNet.p5SafetyNet;

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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.FirestationRepository;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.FirestationService;
import p5SafetyNet.p5SafetyNet.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class FirestationServiceTest {

	@Mock
	FirestationRepository firestationRepository;

	@Mock
	FirestationService firestationService;

	Firestations firestation1;
	Firestations firestation2;

	@Mock
	List<Firestations> listFirestation = new ArrayList<Firestations>();

	@BeforeEach
	private void setUpPerTest() throws Exception {
		 firestation1 = new Firestations((long)1, "1509 Culver St", 3);
		 firestation2 = new Firestations((long) 0, "", 1);
	}

	/**
	 * @throws Exception
	 * @Description create person with succes
	 */
	@Test
	public void createPersonWithSucces() throws Exception {
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
	 * @Description create person with error
	 */
	@Test
	public void createPersonWithError() throws Exception {
		// GIVEN
		listFirestation.add(firestation1);
		lenient().when(firestationRepository.save(any())).thenReturn(firestation1);
	
		// WHEN
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		firestationService.createFirestations(firestation1);
		// THEN
		assertEquals(firestationService.createFirestations(firestation1), null);
	}
	

	/**
	 * @throws Exception
	 * @Description update person with succes
	 */
	@Test
	public void updatePersonWithSucces() throws Exception {
		// GIVEN
		listFirestation.add(firestation1);
		lenient().when(firestationRepository.save(any())).thenReturn(firestation1);
	
		// WHEN
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		firestationService.updateFirestations(firestation1);
		// THEN
		verify(firestationService).updateFirestations(firestation1);
	}
	
	/**
	 * @throws Exception
	 * @Description update person with error
	 */
	@Test
	public void updatePersonWithError() throws Exception {
		// GIVEN
		listFirestation.add(firestation2);
		lenient().when(firestationRepository.save(any())).thenReturn(firestation2);
	
		// WHEN
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		firestationService.updateFirestations(firestation2);
		// THEN
		assertEquals(firestationService.updateFirestations(firestation2), null);
	}
	
	/**
	 * @throws Exception
	 * @Description delete person with succes
	 */
	@Test
	public void deletePersonWithSucces() throws Exception {
		// GIVEN
		listFirestation.add(firestation1);
		long id = firestation1.getId();
		Optional<Firestations> f = Optional.of(firestation1);
		lenient().when(firestationRepository.save(any())).thenReturn(firestation1);
		// WHEN
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(firestationRepository.findById(any())).thenReturn(f);
		firestationService.deleteFirestations(firestation1.getId());
		// THEN
		verify(firestationService).deleteFirestations(firestation1.getId());
	}
	
	/**
	 * @throws Exception
	 * @Description delete person with error
	 */
	@Test
	public void deletePersonWithError() throws Exception {
		// GIVEN
		listFirestation.add(firestation2);
		long id = firestation2.getId();
		Optional<Firestations> f = Optional.of(firestation2);
		lenient().when(firestationRepository.save(any())).thenReturn(firestation2);
	
		// WHEN
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		firestationService.deleteFirestations(firestation2.getId());
		// THEN
		assertEquals(firestationService.deleteFirestations(firestation2.getId()), null);
	}
}
