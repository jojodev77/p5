package p5SafetyNet.p5SafetyNet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import p5SafetyNet.p5SafetyNet.controllers.AlertController;
import p5SafetyNet.p5SafetyNet.servicesImpl.AlertServiceImpl;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = AlertController.class)
public class alertControllerTest {

	  @Autowired
	    private MockMvc mockMvc;  
	  
	  @InjectMocks
	  AlertServiceImpl alertServiceImpl;
	  
	  @BeforeEach
	    private void setUpPerTest() {
		  
	  }
	  
}
