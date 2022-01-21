package p5SafetyNet.p5SafetyNet;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;

import p5SafetyNet.p5SafetyNet.dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.dto.FireAddress;
import p5SafetyNet.p5SafetyNet.dto.FloodStationsInformations;
import p5SafetyNet.p5SafetyNet.dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.FirestationRepository;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;
import p5SafetyNet.p5SafetyNet.services.AlertService;
import p5SafetyNet.p5SafetyNet.services.ReadFileJson;

@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {

	@Mock
	static ReadFileJson readFileJson = new ReadFileJson();

	@Mock
	PersonsRepository personsRepository;

	@Mock
	FirestationRepository firestationRepository;

	@Mock
	MedicalRecordRepository medicalRecordRepository;

	List<Persons> listPersons = new ArrayList<Persons>();

	List<Firestations> listFirestation = new ArrayList<Firestations>();

	List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();

	@Spy
	@InjectMocks
	private static AlertService alertService = new AlertService();

	Firestations firestation1 = new Firestations((long) 1, "1509 Culver St", 3);
	Firestations firestation2 = new Firestations((long) 2, "947 E. Rose Dr", 1);
	Persons persons1 = new Persons((long) 1, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512",
			"jaboyd@email.com");
	Persons persons2 = new Persons((long) 2, "Brian", "Stelzer", "947 E. Rose Dr", "Culver", 97451, "841-874-7784",
			"bstel@email.com");
	Medicalrecords medicalRecord1 = new Medicalrecords();
	Medicalrecords medicalRecord2 = new Medicalrecords();

	@Mock
	CoveragePersonsOfStation coveragePersonsOfStation;

	@BeforeEach
	private void setUpPerTest() {

		listFirestation.add(firestation1);
		listFirestation.add(firestation2);

		listPersons.add(persons1);
		listPersons.add(persons2);

		medicalRecord1.setId((long) 1);
		medicalRecord1.setFirstName("John");
		medicalRecord1.setLastName("Boyd");
		try {
			medicalRecord1.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/1984"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] medication = { "aznol:350mg", "hydrapermazol:100mg" };
		String[] allergies = { "nillacilan" };
		medicalRecord1.setMedications(medication);
		medicalRecord1.setAllergies(allergies);
		medicalRecord2.setId((long) 2);
		medicalRecord2.setFirstName("John");
		medicalRecord2.setLastName("Boyd");
		try {
			medicalRecord2.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/2016"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] medication2 = { "aznol:350mg", "hydrapermazol:100mg" };
		String[] allergies2 = { "nillacilan" };
		medicalRecord2.setMedications(medication2);
		medicalRecord2.setAllergies(allergies2);
		listMedicalRecord.add(medicalRecord1);
		listMedicalRecord.add(medicalRecord2);
		MockitoAnnotations.initMocks(this);
	}

	@BeforeAll
	private static void setUp() {
//	
	}

	@Test
	void contextLoads() {
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with
	 *              succes
	 * 
	 */
	@Test
	public void getPersonsByCoverageFireStationCallMethodSuccesTest() {
		// GIVEN
		listFirestation.clear();
		listMedicalRecord.add(medicalRecord1);
		Firestations firestation1 = new Firestations((long) 1, "1509 Culver St", 3);
		int station = 1;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN
		alertService.getPersonsByCoverageFireStation(station);
//		// THEN
		verify(alertService).getPersonsByCoverageFireStation(station);
	}

	/**
	 * 
	 * @throws IOException
	 * @throws IllegalArgumentException
	 * @throws JsonProcessingException
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with
	 *              succes
	 * 
	 */
	@Test
	public void getPersonsByCoverageFireStationTypeOfMethodSuccesVerifyTypeTest() {
		// GIVEN
		listFirestation.clear();
		listMedicalRecord.add(medicalRecord1);
		Firestations firestation1 = new Firestations((long) 1, "1509 Culver St", 3);
		int station = 1;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN
		alertService.getPersonsByCoverageFireStation(station);
//				// THEN
//		// THEN
		assertEquals(alertService.getPersonsByCoverageFireStation(station).getClass(), CoveragePersonsOfStation.class);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with
	 *              error
	 * 
	 */
	@Test
	public void getPersonsByCoverageFireStationCallMethodErrorWhenStationIsNullTest() {
		// GIVEN
		int station = -2;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN
//		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getPersonsByCoverageFireStation(station);
		});
		assertEquals("station is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with
	 *              error
	 * 
	 */
	@Test
	public void getPersonsByCoverageFireStationCallMethodErrorWhenListPersonsIsNullTest() {
		// GIVEN
		int station = 3;
		listMedicalRecord.clear();
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		lenient().when(personsRepository.findByStation(station)).thenReturn(listPers);
		lenient().when(medicalRecordRepository.findByLastName(anyString())).thenReturn(null);
		// WHEN
//		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getPersonsByCoverageFireStation(station);
		});
		assertEquals(null, exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with
	 *              error
	 * 
	 */
	@Test
	public void getPersonsByCoverageFireStationCallMethodErrorWhenListMedicalrecordIsNullTest() {
		// GIVEN
		int station = 1;
		listMedicalRecord.clear();
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Medicalrecords> listMed = (ArrayList<Medicalrecords>) listMedicalRecord;
		lenient().when(personsRepository.findByStation(station)).thenReturn(listPers);
		lenient().when(medicalRecordRepository.findByLastName(anyString())).thenReturn(listMed);
		// WHEN
//		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getPersonsByCoverageFireStation(station);
		});
		assertEquals("listMedicalRecord is null", exception.getMessage());
	}

//	/**
//	 * 
//	 * @throws Exception
//	 * @description verify then call method getChildByAdress with succes
//	 */
//	@Test
//	public void getChildByAdressCallMethodSuccesTest() {
//		// GIVEN
//		String adress = "1509 Culver St";
//		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
//		System.out.println("^^^^^^^^^^^^" + listMedicalRecord)  ;
//		lenient().when(personsRepository.findByAddress(anyString())).thenReturn(listPers);
//		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
//		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
//		// WHEN
//		alertService.getChildByAdress(adress);
//		// THEN
//		verify(alertService, Mockito.times(1)).getChildByAdress(adress);
//	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getChildByAdress with error
	 */
	@Test
	public void getChildByAdressCallMethodErrorWhenAdressIsNull() {
		// GIVEN
		HashSet<ChildPersons> cp = new HashSet<ChildPersons>();
		cp.clear();
		String adress = null;
		// WHEN
		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getChildByAdress(adress);
		});
		assertEquals("adress is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getChildByAdress with error
	 */
	@Test
	public void getChildByAdressCallMethodErrorWhenListPersonAndListMedicarecordIsNull() {
		// GIVEN
		HashSet<ChildPersons> cp = new HashSet<ChildPersons>();
		cp.clear();
		String adress = "adress test ";
		listMedicalRecord.clear();
		// WHEN
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getChildByAdress(adress);
		});
		assertEquals("listPersons is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getChildByAdress with error
	 */
	@Test
	public void getChildByAdressCallMethodErrorWhenListMedicarecordIsNull() {
		// GIVEN
		HashSet<ChildPersons> cp = new HashSet<ChildPersons>();
		cp.clear();
		String adress = "adress test ";
		listMedicalRecord.clear();
		// WHEN
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(null);
		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getChildByAdress(adress);
		});
		assertEquals("listPersons is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getChildByAdress with error
	 */
	@Test
	public void getChildByAdressCallMethodErrorWhenListPersonsIsNull() {
		// GIVEN
		String adress = "1509 Culver St";
		Medicalrecords medicalRecord5 = new Medicalrecords();
		medicalRecord5.setId((long) 3);
		medicalRecord5.setFirstName("John");
		medicalRecord5.setLastName("Boyd");
		try {
			medicalRecord5.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("00/00/0000"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<Medicalrecords> listMedicalrecordMock = new ArrayList<Medicalrecords>();
		listMedicalrecordMock.add(medicalRecord5);
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		// WHEN
		lenient().when(personsRepository.findByAddress(anyString())).thenReturn(listPers);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalrecordMock);
		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getChildByAdress(adress);
		});
		assertEquals("not parent and child  found", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPhoneNumberPersonsByStation with
	 *              succes
	 */
	@Test
	public void getPhoneNumberPersonsByStationCallMethodSuccesTest() {
		// GIVEN
		int station = 1;
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Medicalrecords> listMec = (ArrayList<Medicalrecords>) listMedicalRecord;
		ArrayList<Firestations> listFire = (ArrayList<Firestations>) listFirestation;
		lenient().when(firestationRepository.findByStation(station)).thenReturn(listFire);
		lenient().when(personsRepository.findByAddress(anyString())).thenReturn(listPers);
		// WHEN
		alertService.getPhoneNumberPersonsByStation(station);
		// THEN
		verify(alertService, Mockito.times(1)).getPhoneNumberPersonsByStation(station);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPhoneNumberPersonsByStation with
	 *              succes
	 */
	@Test
	public void getPhoneNumberPersonsByStationTypeOfMethodSuccesTest() {
		// GIVEN
		int station = 1;
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Medicalrecords> listMec = (ArrayList<Medicalrecords>) listMedicalRecord;
		ArrayList<Firestations> listFire = (ArrayList<Firestations>) listFirestation;
		lenient().when(firestationRepository.findByStation(station)).thenReturn(listFire);
		lenient().when(personsRepository.findByAddress(anyString())).thenReturn(listPers);

		// WHEN
		alertService.getPhoneNumberPersonsByStation(station);
		// THEN
		assertEquals(alertService.getPhoneNumberPersonsByStation(station).getClass(), ArrayList.class);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPhoneNumberPersonsByStation with
	 *              error
	 */
//	@Test
//	public void getPhoneNumberPersonsByStationCallMethodErrorWhenStationNotExistTest() {
//		// GIVEN
//		listFirestation.add(firestation1);
//		listFirestation.add(firestation2);
//		List<String> phoneNumber = new ArrayList<String>();
//		phoneNumber.clear();
//		int station = 0;
//		when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
//		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
//		// WHEN
//		alertService.getPhoneNumberPersonsByStation(station);
//		// THEN
//		verify(alertService, Mockito.times(1)).getPhoneNumberPersonsByStation(station);
//		assertEquals(alertService.getPhoneNumberPersonsByStation(station), phoneNumber);
//	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPhoneNumberPersonsByStation with
	 *              error
	 */
	@Test
	public void getPhoneNumberPersonsByStationCallMethodErrorWhenStationIsNullTest() {
		// GIVEN
		List<String> phoneNumber = new ArrayList<String>();
		phoneNumber.clear();
		int station = -2;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getPhoneNumberPersonsByStation(station);
		});
		assertEquals("station is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPhoneNumberPersonsByStation with
	 *              error
	 */
	@Test
	public void getPhoneNumberPersonsByStationCallMethodErrorWhenListFirestationIsEmptyTest() {
		// GIVEN
		List<String> phoneNumber = new ArrayList<String>();
		phoneNumber.clear();
		int station = 1;
		listFirestation.clear();
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getPhoneNumberPersonsByStation(station);
		});
		assertEquals("listFirestation is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPhoneNumberPersonsByStation with
	 *              error
	 */
	@Test
	public void getPhoneNumberPersonsByStationCallMethodErrorWhenListMedicalRecordIsEmptyTest() {
		// GIVEN
		List<String> phoneNumber = new ArrayList<String>();
		phoneNumber.clear();
		int station = 1;
		listPersons.clear();
		listFirestation.add(firestation1);
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getPhoneNumberPersonsByStation(station);
		});
		assertEquals("listFirestation is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getFireAdress with succes
	 */
	@Test
	public void getFireAdressCallMethodSuccesTest() {
		// GIVEN
		String adress = "1509 Culver St";

		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Firestations> listFire = (ArrayList<Firestations>) listFirestation;
		ArrayList<Medicalrecords> listMec = (ArrayList<Medicalrecords>) listMedicalRecord;
		lenient().when(personsRepository.findByAddress(anyString())).thenReturn(listPers);
		lenient().when(medicalRecordRepository.findByLastName(anyString())).thenReturn(listMec);
		lenient().when(firestationRepository.findByAddress(anyString())).thenReturn(listFire);

		// WHEN
		alertService.getFireAdress(adress);
		// THEN
		verify(alertService, Mockito.times(1)).getFireAdress(adress);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getFireAdress with succes
	 */
	@Test
	public void getFireAdressTypeOfethodSuccesTest() {
		// GIVEN
		String adress = "1509 Culver St";

		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Firestations> listFire = (ArrayList<Firestations>) listFirestation;
		ArrayList<Medicalrecords> listMec = (ArrayList<Medicalrecords>) listMedicalRecord;
		lenient().when(personsRepository.findByAddress(anyString())).thenReturn(listPers);
		lenient().when(medicalRecordRepository.findByLastName(anyString())).thenReturn(listMec);
		lenient().when(firestationRepository.findByAddress(anyString())).thenReturn(listFire);

		// WHEN
		alertService.getFireAdress(adress);
		// THEN
		assertEquals(alertService.getFireAdress(adress).getClass(), ArrayList.class);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getFireAdress with error
	 */
	@Test
	public void getFireAdressCallMethodErrorWhenAdressIsNullTest() {
		// GIVEN
		List<FireAddress> fireAdress = new ArrayList<FireAddress>();
		fireAdress.clear();
		String adress = null;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getFireAdress(adress);
		});
		assertEquals("adress is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getFireAdress with error
	 */
	@Test
	public void getFireAdressCallMethodErrorWhenListFirestationIsNullTest() {
		// GIVEN
		List<FireAddress> fireAdress = new ArrayList<FireAddress>();
		fireAdress.clear();
		String adress = "adress test";
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getFireAdress(adress);
		});
		assertEquals("listPersons is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getFireAdress with error
	 */
	@Test
	public void getFireAdressCallMethodErrorWhenListPersonnIsNullTest() {
		// GIVEN
		List<FireAddress> fireAdress = new ArrayList<FireAddress>();
		fireAdress.clear();
		listPersons.clear();
		String adress = "adress test";
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getFireAdress(adress);
		});
		assertEquals("listPersons is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getListAdressByStation with succes
	 */
	@Test
	public void getListAdressByStationCallMethodSuccesTest() {
		// GIVEN
		int[] station = { 1 };
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Firestations> listFire = (ArrayList<Firestations>) listFirestation;
		ArrayList<Medicalrecords> listMec = (ArrayList<Medicalrecords>) listMedicalRecord;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findByAddress(anyString())).thenReturn(listPers);
		lenient().when(medicalRecordRepository.findListByLastNameAndFirstName(anyString(), anyString()))
				.thenReturn(listMec);
		// WHEN
		alertService.getListAdressByStation(station);
		// THEN
		verify(alertService, Mockito.times(1)).getListAdressByStation(station);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getListAdressByStation with succes
	 */
	@Test
	public void getListAdressByStationTypeOfSuccesTest() {
		// GIVEN
		int[] station = { 1 };
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Firestations> listFire = (ArrayList<Firestations>) listFirestation;
		ArrayList<Medicalrecords> listMec = (ArrayList<Medicalrecords>) listMedicalRecord;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findByAddress(anyString())).thenReturn(listPers);
		lenient().when(medicalRecordRepository.findListByLastNameAndFirstName(anyString(), anyString()))
				.thenReturn(listMec);
		// WHEN
		alertService.getListAdressByStation(station);
		// THEN
		assertEquals(alertService.getListAdressByStation(station).getClass(), ArrayList.class);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getListAdressByStation with error
	 */
	@Test
	public void getListAdressByStationCallMethodErrorWhenStationIsNullTest() {
		// GIVEN
		List<FloodStationsInformations> adressStation = new ArrayList<FloodStationsInformations>();
		adressStation.clear();
		int[] station = null;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getListAdressByStation(station);
		});
		assertEquals("station is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getListAdressByStation with error
	 */
	@Test
	public void getListAdressByStationCallMethodErrorWhenLostFirestationIsNullTest() {
		// GIVEN
		List<FloodStationsInformations> adressStation = new ArrayList<FloodStationsInformations>();
		adressStation.clear();
		int[] station = { 1 };
		listFirestation = null;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getListAdressByStation(station);
		});
		assertEquals(null, exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getListAdressByStation with error
	 */
	@Test
	public void getListAdressByStationCallMethodErrorWhenListPersonIsNullTest() {
		// GIVEN
		List<FloodStationsInformations> adressStation = new ArrayList<FloodStationsInformations>();
		adressStation.clear();
		int[] station = { 1 };
		listPersons.clear();
		listMedicalRecord.clear();

		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getListAdressByStation(station);
		});
		assertEquals("listPersons is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsInformations with succes
	 */
	@Test
	public void getPersonsInformationsCallMethodSuccesTest() {
		// GIVEN
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Firestations> listFire = (ArrayList<Firestations>) listFirestation;
		ArrayList<Medicalrecords> listMec = (ArrayList<Medicalrecords>) listMedicalRecord;
		String lastName = "John";
		String firstName = "Boyd";
		lenient().when(personsRepository.findListByLastNameAndFirstName(anyString(), anyString())).thenReturn(listPers);
		lenient().when(medicalRecordRepository.findListByLastNameAndFirstName(anyString(), anyString()))
				.thenReturn(listMec);
		// WHEN
		alertService.getPersonsInformations(lastName, firstName);
		// THEN
		verify(alertService, Mockito.times(1)).getPersonsInformations(lastName, firstName);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsInformations with succes
	 */
	@Test
	public void getPersonsInformationsTypeOffSuccesTest() {
		// GIVEN
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		ArrayList<Firestations> listFire = (ArrayList<Firestations>) listFirestation;
		ArrayList<Medicalrecords> listMec = (ArrayList<Medicalrecords>) listMedicalRecord;
		String lastName = "John";
		String firstName = "Boyd";
		lenient().when(personsRepository.findListByLastNameAndFirstName(anyString(), anyString())).thenReturn(listPers);
		lenient().when(medicalRecordRepository.findListByLastNameAndFirstName(anyString(), anyString()))
				.thenReturn(listMec);
		// WHEN
		alertService.getPersonsInformations(lastName, firstName);
		// THEN
		assertEquals(alertService.getPersonsInformations(lastName, firstName).getClass(), ArrayList.class);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsInformations with error
	 */
	@Test
	public void getPersonsInformationsCallMethodErrorWhenListFirestationIsNullTest() {
		// GIVEN
		String lastName = "John";
		String firstName = "Boyd";
		listFirestation.clear();
		listPersons.clear();
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN
		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getPersonsInformations(lastName, firstName);
		});
		assertEquals("listPersons is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsInformations with error
	 */
	@Test
	public void getPersonsInformationsCallMethodErrorWhenLastNametAndFirstNameIsNullTest() {
		// GIVEN
		String lastName = null;
		String firstName = null;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHENs
		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getPersonsInformations(lastName, firstName);
		});
		assertEquals("lastName or firstname is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getEmailByCity with succes
	 */
	@Test
	public void getEmailByCityCallMethodSuccesTest() {
		// GIVEN
		String city = "Culver";
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(personsRepository.findByCity(city)).thenReturn(listPers);
		// WHEN
		alertService.getEmailByCity(city);
		// THEN
		verify(alertService, Mockito.times(1)).getEmailByCity(city);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getEmailByCity with error
	 */
	@Test
	public void getEmailByCityCallMethodErrorWhenCityIsNullTest() {
		// GIVEN
		List<PersonsInfos> personInfos = new ArrayList<PersonsInfos>();
		personInfos.clear();
		String city = null;
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getEmailByCity(city);
		});
		assertEquals("city is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getEmailByCity with error
	 */
	@Test
	public void getEmailByCityCallMethodErrorWhenListPersonsIsNullTest() {
		// GIVEN
		List<PersonsInfos> personInfos = new ArrayList<PersonsInfos>();
		personInfos.clear();
		listPersons.clear();
		String city = "custer";
		lenient().when(firestationRepository.findAll()).thenReturn(listFirestation);
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(medicalRecordRepository.findAll()).thenReturn(listMedicalRecord);
		// WHEN

		// THEN
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			alertService.getEmailByCity(city);
		});
		assertEquals("listPersons is null", exception.getMessage());
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getEmailByCity with succes
	 */
	@Test
	public void getEmailByCityTypeOfMethodSuccesTest() {
		// GIVEN
		String city = "Culver";
		ArrayList<Persons> listPers = (ArrayList<Persons>) listPersons;
		lenient().when(personsRepository.findAll()).thenReturn(listPersons);
		lenient().when(personsRepository.findByCity(city)).thenReturn(listPers);
		// WHEN
		alertService.getEmailByCity(city);
		// THEN
		assertEquals(alertService.getEmailByCity(city).getClass(), ArrayList.class);
	}

}
