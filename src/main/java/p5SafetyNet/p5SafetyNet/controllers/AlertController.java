package p5SafetyNet.p5SafetyNet.controllers;

import java.io.IOException;
import java.util.HashSet;
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

import p5SafetyNet.p5SafetyNet.dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.dto.FireAddress;
import p5SafetyNet.p5SafetyNet.dto.FloodStations;
import p5SafetyNet.p5SafetyNet.dto.FloodStationsInformations;
import p5SafetyNet.p5SafetyNet.dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.services.AlertService;
import p5SafetyNet.p5SafetyNet.services.ReadFileJson;
import p5SafetyNet.p5SafetyNet.servicesImpl.ReadFileJsonImpl;

@RestController
@RequestMapping
public class AlertController {
	
	@Autowired
	ReadFileJson readFileJson;
	
	@Autowired
	AlertService alertService;

	@GetMapping("/t")
	public List<Firestations> test() throws Exception {
		return readFileJson.getDataOfFirestations();
	}
	
	@PostMapping("/firestation")
	public CoveragePersonsOfStation  coveragePersonsOfStation(@RequestParam int stationNumber ) throws Exception {
		return alertService.getPersonsByCoverageFireStation(stationNumber);
	}
	
	@PostMapping("/childAlert")
	public HashSet<ChildPersons>  childAdress(@RequestParam String address ) throws Exception {
		return alertService.getChildByAdress(address);
	}
	
	@PostMapping("/phoneAlert")
	public List<String>  childAdress(@RequestParam int firestation ) throws Exception {
		return alertService.getPhoneNumberPersonsByStation(firestation);
	}
	
	@PostMapping("/fire")
	public List<FireAddress>  getFireAdress(@RequestParam String address ) throws Exception {
		return alertService.getFireAdress(address);
	}
	
	@PostMapping("/personInfo")
	public List<PersonsInfos>  getPersonsInformations(@RequestParam String lastName, @RequestParam String firstName ) throws Exception {
		return alertService.getPersonsInformations(lastName, firstName);
	}
	
	@PostMapping("/communityEmail")
	public List<String>  getEmailByCity(@RequestParam String city ) throws Exception {
		return alertService.getEmailByCity(city);
	}
	
	@PostMapping("/flood/stations")
	public List<FloodStationsInformations>  getAdressByListStations(@RequestParam int[] stations ) throws Exception {
		return alertService.getListAdressByStation(stations);
	}
}
