package p5SafetyNet.p5SafetyNet.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import p5SafetyNet.p5SafetyNet.dto.ChildInformations;
import p5SafetyNet.p5SafetyNet.dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsInformations;
import p5SafetyNet.p5SafetyNet.dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.dto.FamilyInformations;
import p5SafetyNet.p5SafetyNet.dto.FireAddress;
import p5SafetyNet.p5SafetyNet.dto.FloodStationsInformations;
import p5SafetyNet.p5SafetyNet.dto.PersonsInfos;
import p5SafetyNet.p5SafetyNet.entity.Firestations;
import p5SafetyNet.p5SafetyNet.entity.Medicalrecords;
import p5SafetyNet.p5SafetyNet.entity.Persons;
import p5SafetyNet.p5SafetyNet.repository.MedicalRecordRepository;
import p5SafetyNet.p5SafetyNet.repository.PersonsRepository;


@Service
public class AlertService {

	@Autowired
	ReadFileJson readFileJson;

	public List<Persons> listPersons = new ArrayList<Persons>();
	public List<Firestations> listFirestation = new ArrayList<Firestations>();
	public List<Medicalrecords> listMedicalRecord = new ArrayList<Medicalrecords>();
	
	@Autowired
	PersonsRepository personsRepository;
	
	@Autowired
	MedicalRecordRepository medicalRecordRepository;
	
/**
 * 
 * @author j.de-la-osa
 * @return list of personn by coverage of station
 */
	public CoveragePersonsOfStation getPersonsByCoverageFireStation(int station) {
		if (station < 1) {
			throw new RuntimeException("station is null");
		}
		
		ArrayList<Persons> pers = personsRepository.findByStation(station);

		List<CoveragePersonsInformations> listCoveragePersonsOfStation = new ArrayList<CoveragePersonsInformations>();
		CoveragePersonsOfStation coveragePersonsOfStation = new CoveragePersonsOfStation();
			for (final Persons p : pers) {
				listMedicalRecord = medicalRecordRepository.findAll().stream()
						.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
				if (listMedicalRecord.isEmpty()) {
					throw new RuntimeException("listMedicalRecord is null");
				}
				CoveragePersonsInformations coveragePersonsInformations = new CoveragePersonsInformations();
				coveragePersonsInformations.setFirstName(p.getFirstName());
				coveragePersonsInformations.setLastName(p.getLastName());
				coveragePersonsInformations.setAddress(p.getAddress());
				coveragePersonsInformations.setPhone(p.getPhone());
				listCoveragePersonsOfStation.add(coveragePersonsInformations);
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
	public HashSet<ChildPersons> getChildByAdress(String address) {
		if (address == null) {
			throw new RuntimeException("adress is null");
		}
		ArrayList<Persons> pers = personsRepository.findByAddress(address);
		List<Medicalrecords> lmP = new ArrayList<Medicalrecords>();
		List<Medicalrecords> lmC = new ArrayList<Medicalrecords>();
		System.out.println("!!!!!!!!!!!!!!!!" +  medicalRecordRepository.findAll());
		if (pers.isEmpty()) {
			throw new RuntimeException("listPersons is null");
		}
		for ( Persons p : pers) {
			lmC = medicalRecordRepository.findAll().stream()
					.filter(m -> p.getLastName().equals(m.getLastName())).filter(i -> getAgePersons(i).getYears() < 18)
					.collect(Collectors.toList());
			
			if (lmC.isEmpty()) {
				throw new RuntimeException("not  child found");
			}
	
			lmP =  medicalRecordRepository.findAll().stream().filter(m -> p.getLastName().equals(m.getLastName()))
					.filter(i -> getAgePersons(i).getYears() > 18).collect(Collectors.toList());
		}
		if (lmP.isEmpty()) {
			throw new RuntimeException("not parent  found");
		}
		HashSet<ChildPersons> listChildPersons = new HashSet<ChildPersons>();

		for (final Medicalrecords mr : lmP) {
			for (final Medicalrecords lm : lmC) {
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
	 * @throws Exception 
	 */
	public List<String> getPhoneNumberPersonsByStation(int station)   {
		if (station < 0) {
			throw new RuntimeException("station is null");
		}
		List<String> listPhoneNumber = new ArrayList<>();
		String phoneNumber;
		listFirestation = readFileJson.getDataOfFirestations().stream().filter(f -> f.getStation() == station)
				.collect(Collectors.toList());
		if (listFirestation.isEmpty()) {
			throw new RuntimeException("listFirestation is null");
		}
		for (final Firestations adress : listFirestation) {
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());
			if (listPersons.isEmpty()) {
				throw new RuntimeException("listPersons is null");
			}
			for (final Persons ph : listPersons) {
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
	
	public List<FireAddress> getFireAdress(String address)  {
		if (address == null) {
			throw new RuntimeException("adress is null");
			
		}
		List<FireAddress> listFireAdress = new ArrayList<FireAddress>();
		FireAddress fireAdress = new FireAddress();
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> address.equals(p.getAddress()))
				.collect(Collectors.toList());
		if (listPersons.isEmpty()) {
			throw new RuntimeException("listPersons is null");
			
		}
		for (final Persons p : listPersons) {
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
			if (listMedicalRecord.size() < 0) {
				throw new RuntimeException("listMedicalRecord is null");
				
			}
			for (final Medicalrecords mr : listMedicalRecord) {
				listFirestation = readFileJson.getDataOfFirestations().stream()
						.filter(f -> f.getAddress().equals(p.getAddress())).collect(Collectors.toList());
				if (listFirestation.size() < 0) {
					throw new RuntimeException("listFirestation is null");
					
				}
				for (final Firestations fs : listFirestation) {
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

	public List<FloodStationsInformations> getListAdressByStation(int[] station) {
		if (station == null) {
			throw new RuntimeException("station is null");
		}
		List<FloodStationsInformations> listFloodStationsInformations = new ArrayList<FloodStationsInformations>();
		listFirestation = readFileJson.getDataOfFirestations().stream()
				.filter(f -> Arrays.stream(station).boxed().collect(Collectors.toList()).contains(f.getStation()))
				.collect(Collectors.toList());
		if (listFirestation.size() < 1) {
			throw new RuntimeException("listFirestation is null");
		}

		for (final Firestations adress : listFirestation) {
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());
			if (listPersons.size() < 1) {
				throw new RuntimeException("listPersons is null");
			}
			for (final Persons p : listPersons) {
				listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
						.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
				if (listMedicalRecord.size() != 0) {
					throw new RuntimeException("listMedicalRecord is null");
				}
				FloodStationsInformations floodStationsInformations = new FloodStationsInformations();
				floodStationsInformations.setLastName(p.getLastName());
				floodStationsInformations.setPhone(p.getPhone());

				for (final Medicalrecords mr : listMedicalRecord) {
					
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

	public List<PersonsInfos> getPersonsInformations(String lastName, String firstName)  {
		if (lastName == null && firstName == null) {
			throw new RuntimeException("lastNameandfirstname is null");
		}
		List<PersonsInfos> listPersonsInfos = new ArrayList<PersonsInfos>();
		listPersons = readFileJson.DataOfPersons().stream()
				.filter(p -> lastName.equals(p.getLastName()) || firstName.equals(p.getFirstName()))
				.collect(Collectors.toList());
		if (listPersons.size() != 0) {
			throw new RuntimeException("listPersons is null");
		}
		for (final Persons p : listPersons) {
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName()) || firstName.equals(p.getFirstName()))
					.collect(Collectors.toList());
			if (listMedicalRecord.size() < 1) {
				System.out.println("::::::::::" + listMedicalRecord);
				throw new RuntimeException("listMedicalRecords is null");
			}
			for (final Medicalrecords mr : listMedicalRecord) {
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

	public List<String> getEmailByCity(String city)  {
		if (city == null) {
			throw new RuntimeException("city is null");
		}
		List<String> listEmailByCity = new ArrayList<String>();
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> city.equals(p.getCity()))
				.collect(Collectors.toList());
		if (listPersons.size() < 1) {
			throw new RuntimeException("listPersons is null");
		}
		for (final Persons email : listPersons) {
			if (email == null) {
				throw new RuntimeException("email is null");
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
	private long minorPersons(List<Medicalrecords> lm)  {
		if (lm.isEmpty()) {
			throw new RuntimeException("listMedicalRecord is null");
		}
		long numberIfMinor = lm.stream().filter(i -> getAgePersons(i).getYears() < 18)
				.collect(Collectors.counting());
		return numberIfMinor;
	}

	/**
	 * 
	 * @param listMedicalRecord
	 * @return calcul major number
	 * @throws Exception
	 */
	private long majorPersons(List<Medicalrecords> lm) {
		if (lm.isEmpty()) {
			throw new RuntimeException("listMedicalRecord is null");
		}
		long numberIfMajor = lm.stream().filter(i -> getAgePersons(i).getYears() > 17)
				.collect(Collectors.counting());
		return numberIfMajor;
	}

	private static Period getAgePersons(Medicalrecords lm) {
		if (lm == null) {
			throw new RuntimeException("medicalRecord is null");
		}
		LocalDate now = LocalDate.now();
		LocalDateTime birthday = null;
		Period period = null;
		birthday = lm.getBirthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		if (period.between(birthday.toLocalDate(), now) == null) {
			throw new RuntimeException("no period");
		}
		return period.between(birthday.toLocalDate(), now);
	}
	
}