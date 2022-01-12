package p5SafetyNet.p5SafetyNet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.text.View;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import p5SafetyNet.p5SafetyNet.controllers.MedicalRecordController;
import p5SafetyNet.p5SafetyNet.controllers.PersonController;
import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.services.MedicalrecordService;

@ExtendWith(MockitoExtension.class)
public class MedicalrecordControllerTest {
	private MockMvc mockMvc;

	@Mock
	MedicalrecordService medicalrecordsService;

	@InjectMocks
	MedicalRecordController medicalRecordController;

	static Medicalrecords medicalRecord1 = new Medicalrecords();


	@Mock
	private View mockView;

	@BeforeAll
	private static void setUp() throws ParseException {
		medicalRecord1.setFirstName("John");
		medicalRecord1.setLastName("Boyd");
		medicalRecord1.setBirthdate(new SimpleDateFormat("dd/MM/yyyy").parse("03/06/1984"));
		String[] medication = { "aznol:350mg", "hydrapermazol:100mg" };
		String[] allergies = { "nillacilan" };
		medicalRecord1.setMedications(medication);
		medicalRecord1.setAllergies(allergies);
	}
	
	@BeforeEach
	private void setUpPerTest() throws ParseException {
		mockMvc = MockMvcBuilders.standaloneSetup(medicalRecordController).build();
		

	}
	


	/**
	 * @throws Exception
	 * @Description test create medicalrecord with succes
	 */
	@Test
	public void createMedicalrecordWithSucces() {
		// GIVEN
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		try {
			mockMvc.perform(post("/medicalRecord").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(medicalRecord1))).andDo(print()).andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description test create medicalrecord with error
	 */
	@Test
	public void createMedicalrecordWithErrors()  {
		// GIVEN
		Medicalrecords medicalRecord2 = new Medicalrecords();
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/medicalRecord").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(medicalRecord2))).andDo(print())
					.andExpect(status().isBadRequest());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description test update medicalrecord with succes
	 */
	@Test
	public void updateMedicalrecordWithSucces(){
		// GIVEN
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		Medicalrecords medicalRecord2 = new Medicalrecords();
		// WHEN
		// THEN
		try {
			mockMvc.perform(put("/medicalRecord").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(medicalRecord2))).andDo(print())
					.andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws JsonProcessingException 
	 * @throws Exception
	 * @Description test update medicalrecord with error
	 */
	@Test
	public void updateMedicalrecordWithErrors() throws JsonProcessingException, Exception  {
		// GIVEN
		Medicalrecords medicalRecord2 = new Medicalrecords();
		Firestations f = new Firestations();
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		mockMvc.perform(delete("/medicalRecord").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
				.content(objectMapper.writeValueAsString(f))).andDo(print())
				.andExpect(status().isBadRequest());
	}

	/**
	 * @throws Exception
	 * @Description delete  medicalrecord  with succes
	 */
	@Test
	public void deleteMedicalrecordWithSucces() {
		// GIVEN
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "1");
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/medicalRecord").accept(MediaType.APPLICATION_JSON).params(requestParams)).andDo(print())
					.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description test delete medical record with error
	 */
	@Test
	public void deleteMedicalrecordWithErrors()  {
		// GIVEN
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("ids", "0");
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/medicalRecord").accept(MediaType.APPLICATION_JSON).params(requestParams)).andDo(print())
					.andExpect(status().isBadRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}


