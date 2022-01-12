package p5SafetyNet.p5SafetyNet;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import p5SafetyNet.p5SafetyNet.dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.dto.FireAddress;
import p5SafetyNet.p5SafetyNet.dto.FloodStationsInformations;
import p5SafetyNet.p5SafetyNet.dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.services.AlertService;
import p5SafetyNet.p5SafetyNet.services.ReadFileJson;
import p5SafetyNet.p5SafetyNet.servicesImpl.ReadFileJsonImpl;



@ExtendWith(MockitoExtension.class)
public class AlertServiceTest {

	@Mock
	 static ReadFileJson readFileJson;

	@Mock
	List<Persons> listPersons = new ArrayList<Persons>();

	@Mock
	List<Firestations> listFirestation = new ArrayList<Firestations>();

	@Mock
	List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();

	@Mock
	private  static AlertService alertService;
	


	@Mock
	CoveragePersonsOfStation coveragePersonsOfStation;

	@BeforeEach
	private void setUpPerTest() throws Exception {
		Firestations firestation1 = new Firestations((long)1, "1509 Culver St", 3);
		Firestations firestation2 = new Firestations((long) 2, "947 E. Rose Dr", 1);
		listFirestation.add(firestation1);
		listFirestation.add(firestation2);
		Persons persons1 = new Persons((long)1, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512",
				"jaboyd@email.com");
		Persons persons2 = new Persons((long)2, "Brian", "Stelzer", "947 E. Rose Dr", "Culver", 97451, "841-874-7784",
				"bstel@email.com");
		listPersons.add(persons1);
		listPersons.add(persons2);
		Medicalrecords medicalRecord1 = new Medicalrecords();
		medicalRecord1.setFirstName("John");
		medicalRecord1.setLastName("Boyd");
		medicalRecord1.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/1984"));
		String[] medication = { "aznol:350mg", "hydrapermazol:100mg" };
		String[] allergies = { "nillacilan" };
		medicalRecord1.setMedications(medication);
		medicalRecord1.setAllergies(allergies);
		Medicalrecords medicalRecord2 = new Medicalrecords();
		medicalRecord2.setFirstName("John");
		medicalRecord2.setLastName("Boyd");
		medicalRecord2.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/1984"));
		String[] medication2 = { "aznol:350mg", "hydrapermazol:100mg" };
		String[] allergies2 = { "nillacilan" };
		medicalRecord2.setMedications(medication2);
		medicalRecord2.setAllergies(allergies2);
		listMedicalRecord.add(medicalRecord1);
		listMedicalRecord.add(medicalRecord2);
	}
		@BeforeAll
		private static void setUp() {
//			 alertService = new AlertService();
			 readFileJson = new ReadFileJsonImpl();
		}
		
		
		@Test
		void contextLoads() {
		}
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with succes
	 *              
	 */
	@Test
	public void getPersonsByCoverageFireStationCallMethodSuccesTest() throws Exception {
		// GIVEN
		int station = 1;
	
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getPersonsByCoverageFireStation(station);
//		// THEN
		verify(alertService).getPersonsByCoverageFireStation(station);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with succes
	 *              
	 */
	@Test
	public void getPersonsByCoverageFireStationCallMethodSuccesVerifyTypeTest() throws Exception {
		// GIVEN
		int station = 1;
	
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		when(alertService.getPersonsByCoverageFireStation(station)).thenReturn(coveragePersonsOfStation);
		// WHEN
		alertService.getPersonsByCoverageFireStation(station);
//		// THEN
		assertThat(alertService.getPersonsByCoverageFireStation(station), instanceOf(CoveragePersonsOfStation.class));
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with error
	 *              
	 */
	@Test
	public void getPersonsByCoverageFireStationCallMethodErrorWhenListFirestationIsNullTest() throws Exception {
		// GIVEN
		int station = 1;
		listFirestation = null;
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getPersonsByCoverageFireStation(station);
//		// THEN
		assertEquals(alertService.getPersonsByCoverageFireStation(station), null);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getChildByAdress with succes
	 */
	@Test
	public void getChildByAdressCallMethodSuccesTest() throws Exception {
		// GIVEN
		String adress = "1509 Culver St";
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		lenient().when(readFileJson.DataOfMedicalRecords()).thenReturn(listMedicalRecord);
		// WHEN
		alertService.getChildByAdress(adress);
		// THEN
		verify(alertService, Mockito.times(1)).getChildByAdress(adress);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getChildByAdress with error
	 */
	@Test
	public void getChildByAdressCallMethodErrorWhenAdressIsNull() throws Exception {
		// GIVEN
		HashSet<ChildPersons> cp = new HashSet<ChildPersons>();
		cp.clear();
		String adress = null;
		// WHEN
		alertService.getChildByAdress(adress);
		// THEN
		assertEquals(alertService.getChildByAdress(adress), cp);
	}

	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPhoneNumberPersonsByStation with succes
	 */
	@Test
	public void getPhoneNumberPersonsByStationCallMethodSuccesTest() throws Exception {
		// GIVEN
		int station = 1;
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getPhoneNumberPersonsByStation(station);
		// THEN
		verify(alertService, Mockito.times(1)).getPhoneNumberPersonsByStation(station);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPhoneNumberPersonsByStation with error
	 */
	@Test
	public void getPhoneNumberPersonsByStationCallMethodErrorWhenStationNotExistTest() throws Exception {
		// GIVEN
		List<String> phoneNumber = new ArrayList<String>();
		phoneNumber.clear();
		int station = 0;
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getPhoneNumberPersonsByStation(station);
		// THEN
		verify(alertService, Mockito.times(1)).getPhoneNumberPersonsByStation(station);
		assertEquals(alertService.getPhoneNumberPersonsByStation(station), phoneNumber);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getFireAdress with succes
	 */
	@Test
	public void getFireAdressCallMethodSuccesTest() throws Exception {
		// GIVEN
		String adress = "1509 Culver St";
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getFireAdress(adress);
		// THEN
		verify(alertService, Mockito.times(1)).getFireAdress(adress);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getFireAdress with error
	 */
	@Test
	public void getFireAdressCallMethodErrorWhenAdressIsNullTest() throws Exception {
		// GIVEN
		List<FireAddress> fireAdress = new ArrayList<FireAddress>();
		fireAdress.clear();
		String adress = null;
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getFireAdress(adress);
		// THEN
		assertEquals(alertService.getFireAdress(adress), fireAdress);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getListAdressByStation with succes
	 */
	@Test
	public void getListAdressByStationCallMethodSuccesTest() throws Exception {
		// GIVEN
		int[] station = null;
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getListAdressByStation(station);
		// THEN
		verify(alertService, Mockito.times(1)).getListAdressByStation(station);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getListAdressByStation with error
	 */
	@Test
	public void getListAdressByStationCallMethodErrorWhenStationIsNullTest() throws Exception {
		// GIVEN
		List<FloodStationsInformations> adressStation = new ArrayList<FloodStationsInformations>();
		adressStation.clear();
		int[] station = null;
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getListAdressByStation(station);
		// THEN
		assertEquals(alertService.getListAdressByStation(station), adressStation);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsInformations with succes
	 */
	@Test
	public void getPersonsInformationsCallMethodSuccesTest() throws Exception {
		// GIVEN
		String lastName = "John";
		String firstName = "Boyd";
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getPersonsInformations(lastName, firstName);
		// THEN
		verify(alertService, Mockito.times(1)).getPersonsInformations(lastName, firstName);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsInformations with error
	 */
	@Test
	public void getPersonsInformationsCallMethodErrorWhenLastNametAndFirstNameIsNullTest() throws Exception {
		// GIVEN
		List<PersonsInfos> personInfos = new ArrayList<PersonsInfos>();
		personInfos.clear();
		String lastName = null;
		String firstName = null;
		lenient().when(readFileJson.getDataOfFirestations()).thenReturn(listFirestation);
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getPersonsInformations(lastName, firstName);
		// THEN
		assertEquals(alertService.getPersonsInformations(lastName, firstName), personInfos);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getEmailByCity with succes
	 */
	@Test
	public void getEmailByCityCallMethodSuccesTest() throws Exception {
		// GIVEN
		String city = "Culver";
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
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
	public void getEmailByCityCallMethodErrorWhenLastNametAndFirstNameIsNullTest() throws Exception {
		// GIVEN
		List<PersonsInfos> personInfos = new ArrayList<PersonsInfos>();
		personInfos.clear();
		String city = null;
		lenient().when(readFileJson.DataOfPersons()).thenReturn(listPersons);
		// WHEN
		alertService.getEmailByCity(city);
		// THEN
		assertEquals(alertService.getEmailByCity(city), personInfos);
	}
	

}
