package p5SafetyNet.p5SafetyNet.services;

import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;

public interface MedicalrecordsService {
	Medicalrecords createMecicalrecords(Medicalrecords medicalrecords) throws Exception;
	Medicalrecords updateMecicalrecords(Medicalrecords medicalrecords) throws Exception;
	Medicalrecords deleteMecicalrecords(long id) throws Exception;
}
