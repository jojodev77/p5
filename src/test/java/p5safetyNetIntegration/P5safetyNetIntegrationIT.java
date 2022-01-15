package p5safetyNetIntegration;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.MedicalrecordService;
import p5SafetyNet.p5SafetyNet.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class P5safetyNetIntegrationIT {
	@Mock
	PersonService personService;

	@Mock
	PersonsRepository personRepository;

	Persons persons1;

	@Mock
	MedicalrecordService medicalrecordsService;

	Medicalrecords medicalRecord1;

	@Mock
	MedicalRecordRepository medicalRecordRepository;

	@Mock
	List<Persons> listPersons = new ArrayList<Persons>();
	@Mock
	List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();

	@BeforeEach
	private void setUpPerTest() throws Exception {
		persons1 = new Persons((long) 1, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512",
				"jaboyd@email.com");
		medicalRecord1 = new Medicalrecords();
		medicalRecord1.setId((long) 1);
		medicalRecord1.setFirstName("John");
		medicalRecord1.setLastName("Boyd");
		String[] medication = { "aznol:350mg", "hydrapermazol:100mg" };
		String[] allergies = { "nillacilan" };
		medicalRecord1.setMedications(medication);
		medicalRecord1.setAllergies(allergies);
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
		// persons1.setLastName("jojo");
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
		lenient().when(personRepository.save(any())).thenReturn(p);
		// WHEN
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		lenient().when(personRepository.findById(any())).thenReturn(p);
		personService.deletePersons(p.get().getId());
		personRepository.delete(p.get());
		Optional<Persons> pers = personRepository.findById(p.get().getId());

		// THEN
		verify(personRepository).delete(p.get());
	}

	/***
	 * @throws Exception
	 * @Description add, update and delete medicalrecord
	 */
	@Test
	public void createAndUpdateAndDeleteMedicalrecord() {
		// GIVEN
		listMedicalRecord.add(medicalRecord1);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN
		try {
			medicalrecordsService.createMecicalrecords(medicalRecord1);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// THEN
		try {
			verify(medicalrecordsService).createMecicalrecords(medicalRecord1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GIVEN
		listMedicalRecord.add(medicalRecord1);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(medicalRecord1);

		// WHEN
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		try {
			medicalrecordsService.updateMecicalrecords(medicalRecord1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// THEN
		try {
			verify(medicalrecordsService).updateMecicalrecords(medicalRecord1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GIVEN
		listMedicalRecord.add(medicalRecord1);
		long id = medicalRecord1.getId();
		Optional<Medicalrecords> m = Optional.of(medicalRecord1);
		lenient().when(medicalRecordRepository.save(any())).thenReturn(m);
		// WHEN
		//lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		lenient().when(medicalRecordRepository.findById(any())).thenReturn(m);
		try {
			medicalrecordsService.deleteMecicalrecords(m.get().getId());
			Optional<Medicalrecords> med = medicalRecordRepository.findById(m.get().getId());
			
			// THEN
			verify(medicalrecordsService).deleteMecicalrecords(m.get().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
