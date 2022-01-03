package p5SafetyNet.p5SafetyNet;

import java.util.ArrayList;
import java.util.List;

import javax.swing.text.View;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import p5SafetyNet.p5SafetyNet.controllers.AlertController;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsInformations;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.servicesImpl.AlertServiceImpl;
import p5SafetyNet.p5SafetyNet.servicesImpl.ReadFileJsonImpl;

@ExtendWith(MockitoExtension.class)
public class alertControllerTest {

	
	   private MockMvc mockMvc;

	@Mock
	static AlertServiceImpl alertServiceImpl;

	@InjectMocks
	private static AlertController alertController;

	@Mock
	static ReadFileJsonImpl readFileJson;
	
	 @Mock private View mockView;

	@BeforeEach
	private void setUpPerTest() {
		mockMvc  = MockMvcBuilders.standaloneSetup(alertController).build();
	}

	@BeforeAll
	private static void setUp() {
		alertServiceImpl = new AlertServiceImpl();
		readFileJson = new ReadFileJsonImpl();
	}

	@Test
	public void coveragePersonsOfStationControllerTest() throws Exception {
		// GIVEN
		List<CoveragePersonsInformations> person = new ArrayList<CoveragePersonsInformations>();
		CoveragePersonsInformations coveragePersonsInformations = new CoveragePersonsInformations();
		CoveragePersonsOfStation coveragePersonsOfStation = new CoveragePersonsOfStation();
		coveragePersonsInformations.setFirstName("Lily");
		coveragePersonsInformations.setLastName("Cooper");
		coveragePersonsInformations.setAddress("489 Manchester St");
		coveragePersonsInformations.setPhone("841-874-9845");
		person.add(coveragePersonsInformations);
		coveragePersonsOfStation.setPerson(person);
		coveragePersonsOfStation.setChildPersons(1);
		coveragePersonsOfStation.setMajorPersons(2);
		LinkedMultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
		requestParams.add("stationNumber", "4");
		// WHEN
		lenient().when(alertServiceImpl.getPersonsByCoverageFireStation(any(int.class))).thenReturn(coveragePersonsOfStation);
		// THEN
		mockMvc.perform(post("/firestation")
				  .accept(MediaType.APPLICATION_JSON)
				.params(requestParams))
					.andDo(print())
						.andExpect(status().isOk());
	}

}
