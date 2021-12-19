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
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

	public List<Persons> listPersons = new ArrayList<Persons>();
	public List<Firestations> listFirestation = new ArrayList<Firestations>();
	public List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();
	
	
	
/**
 * 
 * @author j.de-la-osa
 * @return list of personn by coverage of station
 */
	@Override
	public CoveragePersonsOfStation getPersonsByCoverageFireStation(int station) throws Exception {
		if (station < 0) {
			throw new Exception("int station not valid");
		}
		List<CoveragePersonsInformations> listCoveragePersonsOfStation = new ArrayList<CoveragePersonsInformations>();
		CoveragePersonsOfStation coveragePersonsOfStation = new CoveragePersonsOfStation();
		listFirestation = readFileJson.getDataOfFirestations().stream().filter(f -> f.getStation() == station)
				.collect(Collectors.toList());
		for (final Firestations adress : listFirestation) {
			if (adress == null) {
				throw new Exception("not data for filter1");
			}
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());

			for (final Persons p : listPersons) {
				if (p == null) {
					throw new Exception("not data for filter2");
				}
				listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
						.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
				CoveragePersonsInformations coveragePersonsInformations = new CoveragePersonsInformations();
				coveragePersonsInformations.setFirstName(p.getFirstName());
				coveragePersonsInformations.setLastName(p.getLastName());
				coveragePersonsInformations.setAddress(p.getAddress());
				coveragePersonsInformations.setPhone(p.getPhone());
				listCoveragePersonsOfStation.add(coveragePersonsInformations);
			}
		}

		coveragePersonsOfStation.setPerson(listCoveragePersonsOfStation);
		coveragePersonsOfStation.setChildPersons(minorPersons(listMedicalRecord));
		coveragePersonsOfStation.setMajorPersons(majorPersons(listMedicalRecord));
		return coveragePersonsOfStation;
	}
	
	/***
	 * @author j.de-la-osa
	 * @return the list of chil with then familly
	 */
	@Override
	public HashSet<ChildPersons> getChildByAdress(String address) throws Exception {
		if (address == null) {
			throw new Exception("adress is empty");
		}
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> address.equals(p.getAddress()))
				.collect(Collectors.toList());
		List<Medicalrecords> lmr = new ArrayList<Medicalrecords>();

		lmr = listMedicalRecord;

		for (final Persons p : listPersons) {
			if (p == null) {
				throw new Exception("not data for filter1");
			}
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName())).filter(i -> getAgePersons(i).getYears() < 18)
					.collect(Collectors.toList());

			lmr = readFileJson.DataOfMedicalRecords().stream().filter(m -> p.getLastName().equals(m.getLastName()))
					.filter(i -> getAgePersons(i).getYears() > 18).collect(Collectors.toList());
		}
		HashSet<ChildPersons> listChildPersons = new HashSet<ChildPersons>();

		for (final Medicalrecords mr : listMedicalRecord) {
			if (mr == null) {
				throw new Exception("not data for filter2");
			}
			for (final Medicalrecords lm : lmr) {
				if (lm == null) {
					throw new Exception("not data for filter3");
				}

				ChildPersons cp = new ChildPersons();
				FamilyInformations fp = new FamilyInformations();
				ChildInformations childInformation = new ChildInformations();
				childInformation.setFirstName(mr.getFirstName());
				childInformation.setLastName(mr.getLastName());
				childInformation.setAge(getAgePersons(mr).getYears());
				cp.setChild(childInformation);
				fp.setFirstName(lm.getFirstName());
				fp.setLastName(lm.getLastName());
				fp.setAge(getAgePersons(lm).getYears());
				cp.setFamily(fp);
				listChildPersons.add(cp);

			}
		}

		return listChildPersons;
	}

	/**
	 * 
	 * @author j.de-la-osa
	 * @return the list of phone of persons by station coverage
	 */
	@Override
	public List<String> getPhoneNumberPersonsByStation(int station) throws Exception {
		if (station < 0) {
			throw new Exception("station is not valid");
		}
		List<String> listPhoneNumber = new ArrayList<>();
		String phoneNumber;
		listFirestation = readFileJson.getDataOfFirestations().stream().filter(f -> f.getStation() == station)
				.collect(Collectors.toList());
		for (final Firestations adress : listFirestation) {
			if (adress == null) {
				throw new Exception("not data for filter1");
			}
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());
			for (final Persons ph : listPersons) {
				if (ph == null) {
					throw new Exception("not data for filter2");
				}
				phoneNumber = ph.getPhone().toString();
				listPhoneNumber.add(phoneNumber);

			}
		}
		return listPhoneNumber;
	}

	/***
	 * @author j.de-la-osa
	 * @return the list of information of personn by adress 
	 */
	@Override
	public List<FireAddress> getFireAdress(String address) throws Exception {
		if (address == null) {
			throw new Exception("adress is empty");
		}
		List<FireAddress> listFireAdress = new ArrayList<FireAddress>();
		FireAddress fireAdress = new FireAddress();
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> address.equals(p.getAddress()))
				.collect(Collectors.toList());
		for (final Persons p : listPersons) {
			if (p == null) {
				throw new Exception("not data for filter1");
			}
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
			for (final Medicalrecords mr : listMedicalRecord) {
				if (mr == null) {
					throw new Exception("not data for filter2");
				}
				listFirestation = readFileJson.getDataOfFirestations().stream()
						.filter(f -> f.getAddress().equals(p.getAddress())).collect(Collectors.toList());
				for (final Firestations fs : listFirestation) {
					if (fs == null) {
						throw new Exception("not data for filter3");
					}
					fireAdress.setLastName(p.getLastName());
					fireAdress.setPhone(p.getPhone());
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

	/***
	 * @author j.de-la-osa
	 * @return the list with informations persons with parameters list station
	 */
	@Override
	public List<FloodStationsInformations> getListAdressByStation(int[] station) throws Exception {
		if (station == null) {
			throw new Exception("the list of station is empty");
		}
		List<FloodStationsInformations> listFloodStationsInformations = new ArrayList<FloodStationsInformations>();

		listFirestation = readFileJson.getDataOfFirestations().stream()
				.filter(f -> Arrays.stream(station).boxed().collect(Collectors.toList()).contains(f.getStation()))
				.collect(Collectors.toList());

		for (final Firestations adress : listFirestation) {
			if (adress == null) {
				throw new Exception("not data for filter1");
			}
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());

			for (final Persons p : listPersons) {
				if (p == null) {
					throw new Exception("not data for filter2");
				}
				listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
						.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());

				FloodStationsInformations floodStationsInformations = new FloodStationsInformations();
				floodStationsInformations.setLastName(p.getLastName());
				floodStationsInformations.setPhone(p.getPhone());

				for (final Medicalrecords mr : listMedicalRecord) {
					if (mr == null) {
						throw new Exception("not data for filter3");
					}
					floodStationsInformations.setLastName(p.getLastName());
					floodStationsInformations.setPhone(p.getPhone());
					floodStationsInformations.setAge(getAgePersons(mr).getYears());
					floodStationsInformations.setAllergies(mr.getAllergies());
					floodStationsInformations.setMedications(mr.getMedications());
					listFloodStationsInformations.add(floodStationsInformations);
				}
			}
		}
		return listFloodStationsInformations;
	}

	@Override
	public List<PersonsInfos> getPersonsInformations(String lastName, String firstName) throws Exception {
		if (lastName == null && firstName == null) {
			throw new Exception("lastName and firstName is null");
		}
		List<PersonsInfos> listPersonsInfos = new ArrayList<PersonsInfos>();
		listPersons = readFileJson.DataOfPersons().stream()
				.filter(p -> lastName.equals(p.getLastName()) || firstName.equals(p.getFirstName()))
				.collect(Collectors.toList());
		for (final Persons p : listPersons) {
			if (p == null) {
				throw new Exception("not data for filter1");
			}
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName()) || firstName.equals(p.getFirstName()))
					.collect(Collectors.toList());
			for (final Medicalrecords mr : listMedicalRecord) {
				if (mr == null) {
					throw new Exception("not data for filter2");
				}
				PersonsInfos personsInfos = new PersonsInfos();
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
		if (city == null) {
			throw new Exception("the city is null");
		}
		List<String> listEmailByCity = new ArrayList<String>();
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> city.equals(p.getCity()))
				.collect(Collectors.toList());
		for (final Persons email : listPersons) {
			if (email == null) {
				throw new Exception("the list of email is null");
			}
			listEmailByCity.add(email.getEmail());
		}
		return listEmailByCity;
	}

	/**
	 * 
	 * @param listMedicalRecord
	 * @return calcul minor number
	 * @throws Exception
	 */
	private long minorPersons(List<Medicalrecords> listMedicalRecord) throws Exception {
		if (listMedicalRecord == null) {
			throw new Exception("list of medical record is null");
		}
		long numberIfMinor = listMedicalRecord.stream().filter(i -> getAgePersons(i).getYears() < 18)
				.collect(Collectors.counting());
		return numberIfMinor;
	}

	/**
	 * 
	 * @param listMedicalRecord
	 * @return calcul major number
	 * @throws Exception
	 */
	private long majorPersons(List<Medicalrecords> listMedicalRecord) throws Exception {
		if (listMedicalRecord == null) {
			throw new Exception("list of medical record is null");
		}
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
