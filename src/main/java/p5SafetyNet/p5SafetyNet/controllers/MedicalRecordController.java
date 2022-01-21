package p5SafetyNet.p5SafetyNet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.services.MedicalrecordService;

@RestController
public class MedicalRecordController {

	@Autowired
	MedicalrecordService medicalrecordsService;

	@PostMapping("/medicalRecord")
	public void addMedicalRecord(@RequestBody Medicalrecords medicalrecords) throws Exception {
		medicalrecordsService.createMecicalrecords(medicalrecords);
	}

	@PutMapping("/medicalRecord")
	public void updateMedicalRecord(@RequestBody Medicalrecords medicalrecords) throws Exception {
		medicalrecordsService.updateMecicalrecords(medicalrecords);
	}

	@DeleteMapping("/medicalRecord")
	public void deleteMedicalRecord(@RequestParam long id) throws Exception {
		medicalrecordsService.deleteMecicalrecords(id);
	}

	@PostMapping("/addListMedicarecord")
	public void addListMedicalrecord() {
		medicalrecordsService.createListMedicarecord();
	}

}
