package p5SafetyNet.p5SafetyNet.ServicesImpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p5SafetyNet.p5SafetyNet.Dto.AdressPersons;
import p5SafetyNet.p5SafetyNet.Dto.ChildInformations;
import p5SafetyNet.p5SafetyNet.Dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.Dto.CoveragePersonsInformations;
import p5SafetyNet.p5SafetyNet.Dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.Dto.FamilyInformations;
import p5SafetyNet.p5SafetyNet.Dto.FireAddress;
import p5SafetyNet.p5SafetyNet.Dto.FloodStations;
import p5SafetyNet.p5SafetyNet.Dto.FloodStationsInformations;
import p5SafetyNet.p5SafetyNet.Dto.MecicalRecordByPerson;
import p5SafetyNet.p5SafetyNet.Dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.Dto.PhoneNumber;
import p5SafetyNet.p5SafetyNet.Entity.Firestations;
import p5SafetyNet.p5SafetyNet.Entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.Entity.Persons;
import p5SafetyNet.p5SafetyNet.Services.AlertService;

@Service
public class AlertServiceImpl implements AlertService {

	@Autowired
	ReadFileJsonImpl readFileJson;

	List<Persons> listPersons = new ArrayList<Persons>();
	List<Firestations> listFirestation = new ArrayList<Firestations>();
	List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();

	@Override
	public CoveragePersonsOfStation getPersonsByCoverageFireStation(int station) throws Exception {
		List<CoveragePersonsInformations> listCoveragePersonsOfStation = new ArrayList<CoveragePersonsInformations>();
		CoveragePersonsOfStation coveragePersonsOfStation = new CoveragePersonsOfStation();
		listFirestation = readFileJson.getDataOfFirestations().stream().filter(f -> f.getStation() == station)
				.collect(Collectors.toList());
		for (final Firestations adress : listFirestation) {
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());

			for (final Persons p : listPersons) {
				listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
						.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
				CoveragePersonsInformations coveragePersonsInformations = new CoveragePersonsInformations();
				coveragePersonsInformations.setFirstName(p.getFirstName());
				coveragePersonsInformations.setLastName(p.getLastName());
				coveragePersonsInformations.setAddress(p.getAddress());
				coveragePersonsInformations.setPhone(p.getPhone());
				listCoveragePersonsOfStation.add(coveragePersonsInformations);
				System.out.println("t---------->" + listCoveragePersonsOfStation);
			}
		}
		
		coveragePersonsOfStation.setPerson(listCoveragePersonsOfStation);

		coveragePersonsOfStation.setChildPersons(minorPersons(listMedicalRecord));
		coveragePersonsOfStation.setMajorPersons(majorPersons(listMedicalRecord));
		return coveragePersonsOfStation;
	}

	@Override
	public List<ChildPersons> getChildByAdress(String address) throws Exception {
		ChildPersons childPerson = new ChildPersons();
		List<ChildPersons> listChildPersons = new ArrayList<ChildPersons>();
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> address.equals(p.getAddress()))
				.collect(Collectors.toList());
		for (final Persons p : listPersons) {
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
			for (final Medicalrecords mr : listMedicalRecord) {
				listPersons = readFileJson.DataOfPersons().stream()
						.filter(j -> mr.getLastName().equals(j.getLastName())).collect(Collectors.toList());
				for (final Persons pe : listPersons) {
					ChildInformations childInformation = new ChildInformations();
					FamilyInformations familyInformations = new FamilyInformations();
					if (getAgePersons(mr).getYears() < 18) {
						childInformation.setFirstName(pe.getFirstName());
						childInformation.setLastName(pe.getLastName());
						childInformation.setAge(getAgePersons(mr).getYears());
						childPerson.setChild(childInformation);
						listChildPersons.add(childPerson);
					} else {
						familyInformations.setFirstName(pe.getFirstName());
						childPerson.setFamily(familyInformations);
						listChildPersons.add(childPerson);
					}

				}
			}
		}

		return listChildPersons;
	}

	@Override
	public List<String> getPhoneNumberPersonsByStation(int station) throws Exception {
		List<String> listPhoneNumber = new ArrayList<>();
		String phoneNumber;
		listFirestation = readFileJson.getDataOfFirestations().stream().filter(f -> f.getStation() == station)
				.collect(Collectors.toList());
		for (final Firestations adress : listFirestation) {
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());
			for (final Persons ph : listPersons) {
				phoneNumber = ph.getPhone().toString();
				listPhoneNumber.add(phoneNumber);

			}
		}
		// TODO Auto-generated method stub
		return listPhoneNumber;
	}

	@Override
	public List<FireAddress> getFireAdress(String address) throws Exception {
		List<FireAddress> listFireAdress = new ArrayList<FireAddress>();
		FireAddress fireAdress = new FireAddress();
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> address.equals(p.getAddress()))
				.collect(Collectors.toList());
		for (final Persons p : listPersons) {
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
			for (final Medicalrecords mr : listMedicalRecord) {
				listFirestation = readFileJson.getDataOfFirestations().stream()
						.filter(f -> f.getAddress().equals(p.getAddress())).collect(Collectors.toList());
				for (final Firestations fs : listFirestation) {
					fireAdress.setLastName(p.getLastName());
					fireAdress.setMedications(mr.getMedications());
					fireAdress.setAllergies(mr.getAllergies());
					fireAdress.setAge(getAgePersons(mr).getYears());
					fireAdress.setFirestation(fs.getStation());
					listFireAdress.add(fireAdress);
				}
			}
		}
		return listFireAdress;
	}

	@Override
	public List<FloodStations> getListAdressByStation(int station) throws Exception {
		List<FloodStations> listFloodStation = new ArrayList<FloodStations>();
		FloodStations floodStations = new FloodStations();
		FloodStationsInformations floodStationsInformations = new FloodStationsInformations();
		List<FloodStationsInformations> listFloodStationsInformations = new ArrayList<FloodStationsInformations>();
		listFirestation = readFileJson.getDataOfFirestations().stream().filter(f -> f.getStation() == station)
				.collect(Collectors.toList());
		for (final Firestations adress : listFirestation) {
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());

			for (final Persons p : listPersons) {
				listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
						.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
				CoveragePersonsInformations coveragePersonsInformations = new CoveragePersonsInformations();
				floodStationsInformations.setLastName(p.getLastName());
				floodStationsInformations.setPhone(p.getPhone());
				for (final Medicalrecords mr : listMedicalRecord) {
					floodStationsInformations.setLastName(p.getLastName());
					floodStationsInformations.setPhone(p.getPhone());
					floodStationsInformations.setAge(getAgePersons(mr).getYears());
					floodStationsInformations.setAllergies(mr.getAllergies());
					floodStationsInformations.setMedications(mr.getMedications());
					listFloodStationsInformations.add(floodStationsInformations);
					listFloodStation.add((FloodStations) listFloodStationsInformations);
				}
			}
		}
		return listFloodStation;
	}

	@Override
	public List<PersonsInfos> getPersonsInformations(String lastName, String firstName) throws Exception {
		List<PersonsInfos> listPersonsInfos = new ArrayList<PersonsInfos>();
		PersonsInfos personsInfos = new PersonsInfos();
		listPersons = readFileJson.DataOfPersons().stream()
				.filter(p -> lastName.equals(p.getLastName()) || firstName.equals(p.getFirstName()))
				.collect(Collectors.toList());
		for (final Persons p : listPersons) {
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName()) || firstName.equals(p.getFirstName()))
					.collect(Collectors.toList());
			for (final Medicalrecords mr : listMedicalRecord) {
				personsInfos.setLastName(p.getLastName());
				personsInfos.setAddress(p.getAddress());
				personsInfos.setAge(getAgePersons(mr).getYears());
				personsInfos.setAllergies(mr.getAllergies());
				personsInfos.setMedications(mr.getMedications());
				listPersonsInfos.add(personsInfos);
			}
		}

		// TODO Auto-generated method stub
		return listPersonsInfos;
	}

	@Override
	public List<String> getEmailByCity(String city) throws Exception {
		List<String> listEmailByCity = new ArrayList<String>();
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> city.equals(p.getCity()))
				.collect(Collectors.toList());
		for (final Persons email : listPersons) {
			listEmailByCity.add(email.getEmail());
		}
		return listEmailByCity;
	}

	/**
	 * 
	 * @param listMedicalRecord
	 * @return calcul le nombre de mineur
	 * @throws Exception
	 */
	private long minorPersons(List<Medicalrecords> listMedicalRecord) throws Exception {
		long numberIfMinor = listMedicalRecord.stream().filter(i -> getAgePersons(i).getYears() < 18)
				.collect(Collectors.counting());
		return numberIfMinor;
	}

	/**
	 * 
	 * @param listMedicalRecord
	 * @return le nombre de majeur
	 * @throws Exception
	 */
	private long majorPersons(List<Medicalrecords> listMedicalRecord) throws Exception {
		long numberIfMajor = listMedicalRecord.stream().filter(i -> getAgePersons(i).getYears() > 17)
				.collect(Collectors.counting());
		return numberIfMajor;
	}

	private static Period getAgePersons(Medicalrecords listMedicalRecord) {
		LocalDate now = LocalDate.now();
		LocalDateTime birthday = null;
		Period period = null;
		birthday = listMedicalRecord.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		return period.between(birthday.toLocalDate(), now);
	}
}
