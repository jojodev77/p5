package p5SafetyNet.p5SafetyNet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonProcessingException;


import p5SafetyNet.p5SafetyNet.Dto.CoveragePersonsInformations;
import p5SafetyNet.p5SafetyNet.Dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.Entity.Firestations;
import p5SafetyNet.p5SafetyNet.Entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.Entity.Persons;
import p5SafetyNet.p5SafetyNet.ServicesImpl.AlertServiceImpl;
import p5SafetyNet.p5SafetyNet.ServicesImpl.ReadFileJsonImpl;

@ExtendWith(MockitoExtension.class)
public class AlertServiceImplTest {
	
@InjectMocks
ReadFileJsonImpl readFileJson;

@Mock
List<Persons> listPersons = new ArrayList<Persons>();

@Mock
List<Firestations> listFirestation = new ArrayList<Firestations>();

@Mock
List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();

@Mock
 AlertServiceImpl alertServiceImpl;

@Mock
CoveragePersonsOfStation coveragePersonsOfStation;

	@BeforeEach
    private void setUpPerTest() throws Exception {
		Firestations firestation1 = new Firestations("1509 Culver St", 3 ); 
		Firestations firestation2 = new Firestations("947 E. Rose Dr", 1 ); 
		listFirestation.add(firestation1);
		listFirestation.add(firestation2);
		Persons persons1 = new Persons("John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512", "jaboyd@email.com");
		Persons persons2 = new Persons("Brian", "Stelzer", "947 E. Rose Dr", "Culver", 97451,"841-874-7784", "bstel@email.com");
		listPersons.add(persons1);
		listPersons.add(persons2);
		Medicalrecords medicalRecord1 = new Medicalrecords();
		medicalRecord1.setFirstName("John");
		medicalRecord1.setLastName("Boyd");
		medicalRecord1.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/1984")  );
		String[] medication = {"aznol:350mg", "hydrapermazol:100mg"};
		String[] allergies = {"nillacilan"};
		medicalRecord1.setMedications(medication);
		medicalRecord1.setAllergies(allergies);
		Medicalrecords medicalRecord2 = new Medicalrecords();
		medicalRecord2.setFirstName("John");
		medicalRecord2.setLastName("Boyd");
		medicalRecord2.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/1984")  );
		String[] medication2 = {"aznol:350mg", "hydrapermazol:100mg"};
		String[] allergies2 = {"nillacilan"};
		medicalRecord2.setMedications(medication2);
		medicalRecord2.setAllergies(allergies2);
		listMedicalRecord.add(medicalRecord1);
		listMedicalRecord.add(medicalRecord2);
    }
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getPersonsByCoverageFireStation with succes
	 */
	@Test
	public void getPersonsByCoverageFireStationCallMethodSuccesTest() throws Exception {
		//GIVEN
		int station = 1;
		//WHEN
		alertServiceImpl.getPersonsByCoverageFireStation(station);
		//THEN 
	     verify(alertServiceImpl, Mockito.times(1)).getPersonsByCoverageFireStation(station);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description test error when object waiting is null
	 */
	@Test
	public void getPersonsByCoverageFireStationErrorWhenObjectIsNull() throws Exception {
		//GIVEN
		CoveragePersonsInformations coveragePersonsInformations = null;
		List<CoveragePersonsInformations> listCoveragePersonsOfStation = new ArrayList<CoveragePersonsInformations>();
		
		int station = 1;
		//WHEN
		listCoveragePersonsOfStation.add(coveragePersonsInformations);
		//THEN 
		assertEquals(alertServiceImpl.getPersonsByCoverageFireStation(station), null);
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description test error when object waiting is null
	 */
	@Test
	public void getPersonsByCoverageFireStationErrorWhenListFirestationIsNull() throws Exception {
		//GIVEN
		CoveragePersonsInformations coveragePersonsInformations = null;
		List<CoveragePersonsInformations> listCoveragePersonsOfStation = new ArrayList<CoveragePersonsInformations>();
		int station = 1;
		//WHEN
		when(alertServiceImpl.listFirestation).thenReturn(null);
		//THEN 
	}
	
	/**
	 * 
	 * @throws Exception
	 * @description verify then call method getChildByAdress with succes
	 */
	@Test
	public void getChildByAdressCallMethodSuccesTest() throws Exception {
		//GIVEN
		String adress = "1509 Culver St";
		//WHEN
		 alertServiceImpl.getChildByAdress(adress);
		//THEN 
	     verify(alertServiceImpl, Mockito.times(1)).getChildByAdress(adress);
	}
	
	
}
