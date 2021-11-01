package Services;

import java.util.List;

import Entity.Firestations;
import Entity.Persons;

public interface AlertService {
List<Firestations> getPersonsByFireStation(int station);
List<Persons> getChildByAdress(String adress);
List<String> getPhoneNumberPersonsByStation(int station);
List<Persons> getPersonsByAdress(String adress);
List<Persons> getAdressByStation(String adress);
List<Persons> getMedicalRecordByPerson(String lastName, String firstName);
List<Persons> getEmailByCity(String city);
}
