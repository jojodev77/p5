package p5SafetyNet.p5SafetyNet.Controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import p5SafetyNet.p5SafetyNet.Dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.Dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.Dto.FireAddress;
import p5SafetyNet.p5SafetyNet.Dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.Entity.Firestations;
import p5SafetyNet.p5SafetyNet.Services.AlertService;
import p5SafetyNet.p5SafetyNet.ServicesImpl.ReadFileJsonImpl;

@RestController
@RequestMapping
public class AlertController {
	
	@Autowired
	ReadFileJsonImpl readFileJsonImpl;
	
	@Autowired
	AlertService alertService;

	@GetMapping("/t")
	public List<Firestations> test() throws Exception {
		return readFileJsonImpl.getDataOfFirestations();
	}
	
	@PostMapping("/firestation")
	public CoveragePersonsOfStation  goveragePersonsOfStation(@RequestParam int stationNumber ) throws Exception {
		return alertService.getPersonsByCoverageFireStation(stationNumber);
	}
	
	@PostMapping("/childAlert")
	public List<ChildPersons>  childAdress(@RequestParam String address ) throws Exception {
		return alertService.getChildByAdress(address);
	}
	
	@PostMapping("/phoneAlert")
	public List<String>  childAdress(@RequestParam int firestation_number ) throws Exception {
		return alertService.getPhoneNumberPersonsByStation(firestation_number);
	}
	
	@PostMapping("/fire")
	public List<FireAddress>  getFireAdress(@RequestParam String address ) throws Exception {
		return alertService.getFireAdress(address);
	}
	
	@PostMapping("/personInfo")
	public List<PersonsInfos>  getPersonsInformations(@RequestParam String lastName, @RequestParam String firstName ) throws Exception {
		return alertService.getPersonsInformations(lastName, firstName);
	}
	
	@PostMapping("/commununityEmail")
	public List<String>  getEmailByCity(@RequestParam String city ) throws Exception {
		return alertService.getEmailByCity(city);
	}
}
