package p5SafetyNet.p5SafetyNet;

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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.MedicalrecordsService;
import p5SafetyNet.p5SafetyNet.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordServiceTest {

	@Mock
	MedicalRecordRepository medicalRecordRepository;

	@Mock
	MedicalrecordsService medicalrecordsService;

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
	}

	/**
	 * @throws Exception
	 * @Description create medicalrecord with succes
	 */
	@Test
	public void createMedicalRecordWithSucces() throws Exception {
		// GIVEN
		listMedicalRecord.add(medicalRecord2);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN
		medicalrecordsService.createMecicalrecords(medicalRecord2);
		// THEN
		verify(medicalrecordsService).createMecicalrecords(medicalRecord2);
	}
	

	/**
	 * @throws Exception
	 * @Description create medicalrecord with error
	 */
	@Test
	public void createMedicalRecordWithError() throws Exception {
		// GIVEN
		listMedicalRecord.add(medicalRecord1);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(medicalRecord1);
	
		// WHEN
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		medicalrecordsService.createMecicalrecords(medicalRecord1);
		// THEN
		assertEquals(medicalrecordsService.createMecicalrecords(medicalRecord1), null);
	}
	

	/**
	 * @throws Exception
	 * @Description update medicalrecord with succes
	 */
	@Test
	public void updateMedicalRecordWithSucces() throws Exception {
		// GIVEN
		listMedicalRecord.add(medicalRecord1);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(medicalRecord1);
	
		// WHEN
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
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
		listMedicalRecord.add(medicalRecord2);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(medicalRecord2);
	
		// WHEN
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		medicalrecordsService.updateMecicalrecords(medicalRecord2);
		// THEN
		assertEquals(medicalrecordsService.updateMecicalrecords(medicalRecord2), null);
	}
	
	/**
	 * @throws Exception
	 * @Description delete medicalrecord with succes
	 */
	@Test
	public void deleteMedicalRecordWithSucces() throws Exception {
		// GIVEN
		listMedicalRecord.add(medicalRecord1);
		long id = medicalRecord1.getId();
		Optional<Medicalrecords> m = Optional.of(medicalRecord1);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(medicalRecord1);
		// WHEN
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		lenient().when(medicalRecordRepository.findById(any())).thenReturn(m);
		medicalrecordsService.deleteMecicalrecords(medicalRecord1.getId());
		// THEN
		verify(medicalrecordsService).deleteMecicalrecords(medicalRecord1.getId());
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
		Optional<Medicalrecords> m = Optional.of(medicalRecord2);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(medicalRecord2);
	
		// WHEN
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		medicalrecordsService.deleteMecicalrecords(medicalRecord2.getId());
		// THEN
		assertEquals(medicalrecordsService.deleteMecicalrecords(medicalRecord2.getId()), null);
	}

}

