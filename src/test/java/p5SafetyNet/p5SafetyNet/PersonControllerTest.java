package p5SafetyNet.p5SafetyNet;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.swing.text.View;

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
import p5SafetyNet.p5SafetyNet.controllers.PersonController;
import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.services.PersonService;

@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
	private MockMvc mockMvc;

	@Mock
	PersonService personService;

	@InjectMocks
	PersonController personController;

	private Persons  persons1 = new Persons((long)4, "John", "Boyd", "1509 Culver St", "Culver", 97451, "841-874-6512",
			"jaboyd@email.com");

	@Mock
	private View mockView;

	@BeforeEach
	private void setUpPerTest() {
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

	}

	/**
	 * @throws Exception
	 * @Description test create person
	 */
	@Test
	public void createPersonsWithSucces() {
		// GIVEN
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		try {
			mockMvc.perform(post("/person").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(persons1))).andDo(print()).andExpect(status().isOk());
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
	 * @Description test create person
	 */
	@Test
	public void createPersonWithErrors() throws JsonProcessingException {
		// GIVEN
		Persons persons2 = new Persons((long)0, "", "", "", "", 0, "","");
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/person").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(persons2))).andDo(print())
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
	 * @Description test update person
	 */
	@Test
	public void updatePersonWithSucces() {
		// GIVEN
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		Persons persons2 = new Persons((long)0, "", "", "", "", 0, "","");
		// WHEN
		// THEN
		try {
			mockMvc.perform(put("/person").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(persons2))).andDo(print())
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
	 * @Description test update person
	 */
	@Test
	public void updatePersonWithErrors() {
		// GIVEN
		Persons persons2 = new Persons((long)0, "", "", "", "", 0, "","");
		Firestations f = new Firestations();
		MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
		ObjectMapper objectMapper = new ObjectMapper();
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/person").accept(MediaType.APPLICATION_JSON).contentType(MEDIA_TYPE_JSON_UTF8)
					.content(objectMapper.writeValueAsString(f))).andDo(print())
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
	 * @Description delete persons person
	 */
	@Test
	public void deletePersonWithSucces()  {
		// GIVEN
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("id", "1");
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/person").accept(MediaType.APPLICATION_JSON).params(requestParams)).andDo(print())
					.andExpect(status().isOk());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @throws Exception
	 * @Description test delete person
	 */
	@Test
	public void deletePersonWithErrors()  {
		// GIVEN
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("ids", "0");
		// WHEN
		// THEN
		try {
			mockMvc.perform(delete("/person").accept(MediaType.APPLICATION_JSON).params(requestParams)).andDo(print())
					.andExpect(status().isBadRequest());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
