package p5SafetyNet.p5SafetyNet.controllers;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import p5SafetyNet.p5SafetyNet.dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.dto.FireAddress;
import p5SafetyNet.p5SafetyNet.dto.FloodStationsInformations;
import p5SafetyNet.p5SafetyNet.dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.services.AlertService;
import p5SafetyNet.p5SafetyNet.services.ReadFileJson;

@RestController
@RequestMapping
public class AlertController {

	@Autowired
	ReadFileJson readFileJson;

	@Autowired
	AlertService alertService;

	@PostMapping("/firestation")
	public CoveragePersonsOfStation coveragePersonsOfStation(@RequestParam int stationNumber) {
		return alertService.getPersonsByCoverageFireStation(stationNumber);
	}

	@PostMapping("/childAlert")
	public HashSet<ChildPersons> childAdress(@RequestParam String address) {
		return alertService.getChildByAdress(address);
	}
	

	@PostMapping("/phoneAlert")
	public List<String> childAdress(@RequestParam int firestation) {
		return alertService.getPhoneNumberPersonsByStation(firestation);
	}

	@PostMapping("/fire")
	public List<FireAddress> getFireAdress(@RequestParam String address) {
		return alertService.getFireAdress(address);
	}

	@PostMapping("/personInfo")
	public List<PersonsInfos> getPersonsInformations(@RequestParam String lastName, @RequestParam String firstName) {
		return alertService.getPersonsInformations(lastName, firstName);
	}

	@PostMapping("/communityEmail")
	public List<String> getEmailByCity(@RequestParam String city) {
		return alertService.getEmailByCity(city);
	}

	@PostMapping("/flood/stations")
	public List<FloodStationsInformations> getAdressByListStations(@RequestParam int[] stations) {
		return alertService.getListAdressByStation(stations);
	}
}
