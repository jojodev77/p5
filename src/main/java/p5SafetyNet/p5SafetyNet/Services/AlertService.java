package p5SafetyNet.p5SafetyNet.Services;

import java.util.List;

import p5SafetyNet.p5SafetyNet.Dto.AdressPersons;
import p5SafetyNet.p5SafetyNet.Dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.Dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.Dto.MecicalRecordByPerson;
import p5SafetyNet.p5SafetyNet.Dto.PhoneNumber;
import p5SafetyNet.p5SafetyNet.Entity.Firestations;
import p5SafetyNet.p5SafetyNet.Entity.Persons;

public interface AlertService {
CoveragePersonsOfStation getPersonsByCoverageFireStation(int station) throws Exception;
List<ChildPersons> getChildByAdress(String adress) throws Exception;
List<PhoneNumber> getPhoneNumberPersonsByStation(int station);
List<MecicalRecordByPerson> getPersonsByAdress(String adress);
List<AdressPersons> getAdressByStation(String adress);
List<AdressPersons> getMedicalRecordByPerson(String lastName, String firstName);
List<String> getEmailByCity(String city);
}
