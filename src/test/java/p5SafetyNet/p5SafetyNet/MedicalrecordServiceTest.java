package p5SafetyNet.p5SafetyNet;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.text.SimpleDateFormat;
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

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.MedicalrecordService;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordServiceTest {

	@Mock
	MedicalRecordRepository medicalRecordRepository;

	@Spy
	@InjectMocks
	private static MedicalrecordService medicalrecordsService = new MedicalrecordService();

	Medicalrecords medicalRecord1;
	Medicalrecords medicalRecord2;

	@Mock
	List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();

	@BeforeEach
	private void setUpPerTest() throws Exception {
		 medicalRecord1 = new Medicalrecords();
		 medicalRecord1.setId((long) 1);
		medicalRecord1.setFirstName("John");
		medicalRecord1.setLastName("Boyd");
		String[] medication = { "aznol:350mg", "hydrapermazol:100mg" };
		String[] allergies = { "nillacilan" };
		medicalRecord1.setMedications(medication);
		medicalRecord1.setAllergies(allergies);
		medicalRecord2 = new Medicalrecords();
		 medicalRecord2.setId((long) 0);
		medicalRecord2.setFirstName("");
		medicalRecord2.setLastName("");
		String[] medication2 = { "aznol:350mg", "hydrapermazol:100mg" };
		String[] allergies2 = { "nillacilan" };
		medicalRecord2.setMedications(medication2);
		medicalRecord2.setAllergies(allergies2);
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * @throws Exception
	 * @Description create medicalrecord with succes
	 */
	@Test
	public void createMedicalRecordWithSucces() throws Exception {
		// GIVEN
		lenient().when(medicalRecordRepository.findByLastNameAndFirstName(medicalRecord1.getLastName(), medicalRecord1.getFirstName()))
				.thenReturn(null);
		// WHEN
		medicalrecordsService.createMecicalrecords(medicalRecord1);
		// THEN
		verify(medicalrecordsService).createMecicalrecords(medicalRecord1);
	}
	

	/**
	 * @throws Exception
	 * @Description create medicalrecord with error
	 */
	@Test
	public void createMedicalRecordWithError() throws Exception {
		// GIVEN
//		PersonService personService = Mockito.mock(PersonService.class);
		listMedicalRecord.add(medicalRecord1);
		lenient().when(medicalRecordRepository.findByLastNameAndFirstName(medicalRecord1.getLastName(), medicalRecord1.getFirstName()))
				.thenReturn(medicalRecord1);

		// WHEN
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			medicalrecordsService.createMecicalrecords(medicalRecord1);
		});
		assertEquals("medicalrecord  is present in db", exception.getMessage());
	}
	

	/**
	 * @throws Exception
	 * @Description update medicalrecord with succes
	 */
	@Test
	public void updateMedicalRecordWithSucces() throws Exception {
		// GIVEN
		lenient().when(medicalRecordRepository.save(any())).thenReturn(medicalRecord1);
	
		// WHEN
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		lenient().when(medicalRecordRepository.findByLastNameAndFirstName(medicalRecord1.getLastName(), medicalRecord1.getFirstName())).thenReturn(medicalRecord1);
		medicalrecordsService.updateMecicalrecords(medicalRecord1);
		// THEN
		verify(medicalrecordsService).updateMecicalrecords(medicalRecord1);
	}
	
	/**
	 * @throws Exception
	 * @Description update medicalrecord with error
	 */
	@Test
	public void updateMedicalRecordWithError() throws Exception {
		// GIVEN
//		PersonService personService = Mockito.mock(PersonService.class);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(null);

		// WHEN
		lenient().when(medicalRecordRepository.findByLastNameAndFirstName("f", "l"))
				.thenReturn(null);
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			medicalrecordsService.updateMecicalrecords(medicalRecord2);
		});
		assertEquals("medicalrecord  not present in db", exception.getMessage());
	}
	
	/**
	 * @throws Exception
	 * @Description delete medicalrecord with succes
	 */
	@Test
	public void deleteMedicalRecordWithSucces() throws Exception {
		medicalRecordRepository.save(medicalRecord2);
		lenient().when(medicalrecordsService.createMecicalrecords(medicalRecord2)).thenReturn(medicalRecord2);
		lenient().when(medicalRecordRepository.findById(medicalRecord2.getId())).thenReturn(Optional.of(medicalRecord2));
		Optional<Medicalrecords> p = medicalRecordRepository.findById(medicalRecord2.getId());
		if (medicalRecord2.getId() != null) {
			medicalRecordRepository.deleteById(medicalRecord2.getId());
			 verify(medicalRecordRepository).deleteById(medicalRecord2.getId());
		}
	}
	
	/**
	 * @throws Exception
	 * @Description delete medicalrecord with error
	 */
	@Test
	public void deleteMedicalRecordWithError() throws Exception {
		// GIVEN
		listMedicalRecord.add(medicalRecord2);
		long id = medicalRecord2.getId();
		Optional<Medicalrecords> p = Optional.of(medicalRecord2);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(medicalRecord2);

		// WHEN
		final RuntimeException run = new RuntimeException();

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			medicalrecordsService.deleteMecicalrecords(id);
		});
		assertEquals("medicalrecord is null", exception.getMessage());
	}

}

