package p5SafetyNet.p5SafetyNet.services;

import java.util.HashSet;
import java.util.List;

import p5SafetyNet.p5SafetyNet.dto.AdressPersons;
import p5SafetyNet.p5SafetyNet.dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.dto.FireAddress;
import p5SafetyNet.p5SafetyNet.dto.FloodStations;
import p5SafetyNet.p5SafetyNet.dto.FloodStationsInformations;
import p5SafetyNet.p5SafetyNet.dto.MecicalRecordByPerson;
import p5SafetyNet.p5SafetyNet.dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.dto.PhoneNumber;
import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Persons;

public interface AlertService {
CoveragePersonsOfStation getPersonsByCoverageFireStation(int station) throws Exception;
HashSet<ChildPersons> getChildByAdress(String adress) throws Exception;
List<String> getPhoneNumberPersonsByStation(int station) throws Exception;
List<FireAddress> getFireAdress(String adress) throws Exception;
List<FloodStationsInformations> getListAdressByStation(int[] station)throws Exception;
List<PersonsInfos> getPersonsInformations(String lastName, String firstName) throws Exception;
List<String> getEmailByCity(String city) throws Exception;
}
