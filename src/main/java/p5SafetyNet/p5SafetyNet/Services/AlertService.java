package p5SafetyNet.p5SafetyNet.Services;

import java.util.List;

import p5SafetyNet.p5SafetyNet.Dto.AdressPersons;
import p5SafetyNet.p5SafetyNet.Dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.Dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.Dto.FireAddress;
import p5SafetyNet.p5SafetyNet.Dto.MecicalRecordByPerson;
import p5SafetyNet.p5SafetyNet.Dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.Dto.PhoneNumber;
import p5SafetyNet.p5SafetyNet.Entity.Firestations;
import p5SafetyNet.p5SafetyNet.Entity.Persons;

public interface AlertService {
CoveragePersonsOfStation getPersonsByCoverageFireStation(int station) throws Exception;
List<ChildPersons> getChildByAdress(String adress) throws Exception;
List<String> getPhoneNumberPersonsByStation(int station) throws Exception;
List<FireAddress> getFireAdress(String adress) throws Exception;
List<AdressPersons> getAdressByStation(String adress)throws Exception;
List<PersonsInfos> getPersonsInformations(String lastName, String firstName) throws Exception;
List<String> getEmailByCity(String city) throws Exception;
}
