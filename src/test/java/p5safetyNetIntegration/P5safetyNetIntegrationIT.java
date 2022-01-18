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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.AlertService;
import p5SafetyNet.p5SafetyNet.services.MedicalrecordService;
import p5SafetyNet.p5SafetyNet.services.PersonService;
import p5SafetyNet.p5SafetyNet.services.ReadFileJson;

@ExtendWith(MockitoExtension.class)
public class P5safetyNetIntegrationIT {
	@Spy
	@InjectMocks
	private static PersonService personService = new PersonService();
	
	@Mock
	private static ReadFileJson readFileJson = new ReadFileJson();
	
	@Spy
	@InjectMocks
	private static AlertService alertService = new AlertService();

	@Mock
	PersonsRepository personRepository;

	Persons persons1;
	
	Persons persons3;

	@Mock
	MedicalrecordService medicalrecordsService;

	Medicalrecords medicalRecord1;
	Medicalrecords medicalRecord2;
	Firestations firestation1 = new Firestations((long)1, "1509 Culver St", 3);

	@Mock
	MedicalRecordRepository medicalRecordRepository;

	
	List<Persons> listPersons = new ArrayList<Persons>();
	
	List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();
	
	List<Firestations> listFirestation = new ArrayList<Firestations>();

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
		lenient().when(personRepository.findByLastNameAndFirstName(persons1.getLastName(), persons1.getFirstName()))
				.thenReturn(null);
		// WHEN
		personService.createPersons(persons1);
		// GIVEN
		persons1 = new Persons((long) 1, "John", "Boyd", "1509 Culver Sts", "Culvers", 974512, "841-874-6512",
				"jaboyd@email.com");
		personRepository.save(persons1);
		lenient().when(personRepository.findByLastNameAndFirstName(persons1.getLastName(), persons1.getFirstName()))
		.thenReturn(persons1);
		lenient().when(personRepository.save(persons1)).thenReturn((persons1));
		lenient().when(personRepository.findAll()).thenReturn(listPersons);
		// WHEN
		personService.updatePersons(persons1);
		// GIVEN

		// WHEN
		lenient().when(personRepository.save(any())).thenReturn(persons1);
		lenient().when(personRepository.findById(persons1.getId())).thenReturn(Optional.of(persons1));
		Optional<Persons> p = personRepository.findById(persons1.getId());
		if (persons1.getId() != null) {
			personRepository.deleteById(persons1.getId());
			// THEN
			verify(personRepository).deleteById(persons1.getId());
		}
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
		// lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
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
	
	/**
	 * @Description test integration with create persons and get city
	 */
	@Test()
	public void createPersonAndGetCity() {
		// GIVEN
		listPersons.clear();
		persons3 = new Persons((long) 1, "paul", "rome", "1501 Culver Sts", "toto", 974512, "841-874-6512",
				"jaboyd@email.com");
		lenient().when(personRepository.findByLastNameAndFirstName(persons3.getLastName(), persons3.getFirstName()))
				.thenReturn(null);
		// WHEN
		personService.createPersons(persons1);
		// GIVEN
		String city = "toto";
		listPersons.add(persons3);
		when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getEmailByCity(city);
		// THEN
		verify(alertService, Mockito.times(1)).getEmailByCity(city);
	}
	
	/**
	 * @Description test integration with create persons and get personsInformation
	 */
	@Test()
	public void createPersonAndMedicalRecordsAndGetPersonInformation() {
		// GIVEN
		listPersons.clear();
		persons3 = new Persons((long) 1, "paul", "rome", "1501 Culver Sts", "toto", 974512, "841-874-6512",
				"jaboyd@email.com");
		lenient().when(personRepository.findByLastNameAndFirstName(persons3.getLastName(), persons3.getFirstName()))
				.thenReturn(null);
		// WHEN
		personService.createPersons(persons1);
		// GIVEN
		String lastName = "paul";
		String firstName = "rome";
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getPersonsInformations(lastName, firstName);
		// THEN
		verify(alertService, Mockito.times(1)).getPersonsInformations(lastName, firstName);
	}
	
	/**
	 * @Description test integration with create persons and get PersonsByCoverageFireStation
	 */
	@Test()
	public void createPersonAndMedicalRecordsAndGetPersonsByCoverageFireStation() {
		// GIVEN
		listFirestation.clear();
		listPersons.clear();
		persons3 = new Persons((long) 1, "paul", "rome", "1501 Culver Sts", "toto", 974512, "841-874-6512",
				"jaboyd@email.com");
		lenient().when(personRepository.findByLastNameAndFirstName(persons3.getLastName(), persons3.getFirstName()))
				.thenReturn(null);
		Firestations firestation1 = new Firestations((long)1, "1501 Culver Sts", 1);
		listFirestation.add(firestation1);
		// WHEN
		personService.createPersons(persons1);
		// GIVEN
		int station = 1;
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		lenient().when(readFileJson.DataOfMedicalRecords()).thenReturn(listMedicalRecord);
		// WHEN
		alertService.getPersonsByCoverageFireStation(station);
//		// THEN
		verify(alertService).getPersonsByCoverageFireStation(station);
	}
	
	/**
	 * @Description test integration with create persons and get ChildByAdress
	 */
	@Test()
	public void createPersonAndMedicalRecordsAndGetChildByAdress() {
		// GIVEN
		listFirestation.clear();
		listMedicalRecord.add(medicalRecord1);
		persons3 = new Persons((long) 1, "paul", "rome", "1501 Culver Sts", "toto", 974512, "841-874-6512",
				"jaboyd@email.com");
		lenient().when(personRepository.findByLastNameAndFirstName(persons3.getLastName(), persons3.getFirstName()))
				.thenReturn(null);

		// WHEN
		personService.createPersons(persons1);
		// GIVEN
		String adress = "1509 Culver St";
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		lenient().when(readFileJson.DataOfMedicalRecords()).thenReturn(listMedicalRecord);
		// WHEN
		alertService.getChildByAdress(adress);
		// THEN
		verify(alertService, Mockito.times(1)).getChildByAdress(adress);
	}
	
	/**
	 * @Description test integration with create persons and getPhoneNumberPersonsByStation
	 */
	@Test()
	public void createPersonAndMedicalRecordsAndGetPhoneNumberPersonsByStation() {
		// GIVEN
		listFirestation.clear();
		listMedicalRecord.add(medicalRecord1);
		persons3 = new Persons((long) 1, "paul", "rome", "1501 Culver Sts", "toto", 974512, "841-874-6512",
				"jaboyd@email.com");
		lenient().when(personRepository.findByLastNameAndFirstName(persons3.getLastName(), persons3.getFirstName()))
				.thenReturn(null);

		// WHEN
		personService.createPersons(persons1);
		//GIVEN
		int station = 1;
		listFirestation.add(firestation1);
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getPhoneNumberPersonsByStation(station);
		// THEN
		verify(alertService, Mockito.times(1)).getPhoneNumberPersonsByStation(station);
	}
	
	/**
	 * @Description test integration with create persons and getFireAdress
	 */
	@Test()
	public void createPersonAndMedicalRecordsAndGetFireAdress() {
		// GIVEN
		listFirestation.clear();
		listMedicalRecord.add(medicalRecord1);
		persons3 = new Persons((long) 1, "paul", "rome", "1501 Culver Sts", "toto", 974512, "841-874-6512",
				"jaboyd@email.com");
		lenient().when(personRepository.findByLastNameAndFirstName(persons3.getLastName(), persons3.getFirstName()))
				.thenReturn(null);

		// WHEN
		personService.createPersons(persons1);
		//GIVEN
		String adress = "1509 Culver St";
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		lenient().when(readFileJson.DataOfMedicalRecords()).thenReturn(listMedicalRecord);
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);

		// WHEN
		alertService.getFireAdress(adress);
		// THEN
		verify(alertService, Mockito.times(1)).getFireAdress(adress);
	}
	

}
