package p5SafetyNet.p5SafetyNet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import p5SafetyNet.p5SafetyNet.controllers.FirestationController;
import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.services.FirestationService;

@ExtendWith(MockitoExtension.class)
public class FirestationControllerTest {

	private MockMvc mockMvc;

	@Mock
	FirestationService firestationService;

	@InjectMocks
	FirestationController firestationController;

	private Firestations firestation1 = new Firestations((long) 1, "1509 Culver St", 3);

	@Mock
	private View mockView;

	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(firestationController).build();

	}

	/**
	 * @throws Exception
	 * @Description test create firestation
	 */
//	@Test
//	public void createFirestationWithSucces()  {
//		// GIVEN
//		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
//		ObjectMapper objectMapper = new ObjectMapper();
//		// WHEN
//		// THEN
//		try {
//			mockMvc.perform(post("/firestation").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
//					.content(objectMapper.writeValueAsString(firestation1))).andDo(print()).andExpect(status().isOk());
//		} catch (JsonProcessingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * @throws Exception
	 * @Description test create firestation
	 */
	@Test
	public void createFirestationWithErrors() {
		// GIVEN
		Firestations firestation2 = new Firestations((long) 0, "", 0);
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/firestation").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(firestation2))).andDo(print())
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
	 * @Description test update firestation
	 */
	@Test
	public void updateFirestationWithSucces()  {
		// GIVEN
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		try {
			mockMvc.perform(put("/firestation").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(firestation1))).andDo(print())
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
	 * @throws Exception
	 * @Description test update firestation
	 */
	@Test
	public void updateFirestationWithErrors()  {
		// GIVEN
		Firestations firestation2 = new Firestations((long) 0, "", 0);
		Persons p = new Persons();
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/firestation").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(p))).andDo(print())
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
	 * @Description delete update firestation
	 */
	@Test
	public void deleteupdateWithSucces()  {
		// GIVEN
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "1");
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/firestation").accept(MediaType.APPLICATION_JSON).params(requestParams)).andDo(print())
					.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description test delete firestation
	 */
	@Test
	public void deleteFirestationWithErrors()  {
		// GIVEN
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("ids", "0");
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/firestation").accept(MediaType.APPLICATION_JSON).params(requestParams)).andDo(print())
					.andExpect(status().isBadRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
