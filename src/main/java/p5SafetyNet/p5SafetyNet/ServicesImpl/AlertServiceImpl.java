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
import p5SafetyNet.p5SafetyNet.Dto.ChildPersons;
import p5SafetyNet.p5SafetyNet.Dto.CoveragePersonsOfStation;
import p5SafetyNet.p5SafetyNet.Dto.MecicalRecordByPerson;
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
		List<CoveragePersonsOfStation> listCoveragePersonsOfStation = new ArrayList<CoveragePersonsOfStation>();
		CoveragePersonsOfStation coveragePersonsOfStation = new CoveragePersonsOfStation();
		listFirestation = readFileJson.getDataOfFirestations().stream().filter(f -> f.getStation() == station)
				.collect(Collectors.toList());
		for (final Firestations adress : listFirestation) {
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.getAddress().equals(p.getAddress()))
					.collect(Collectors.toList());

			for (final Persons p : listPersons) {
				listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
						.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
				
			}
		}
		System.out.println("xxxxx" + listMedicalRecord.get(0).getFirstName());
		coveragePersonsOfStation.setPerson(listPersons.stream().toArray(Persons[]::new));
		coveragePersonsOfStation.setChildPersons(minorPersons(listMedicalRecord));
		coveragePersonsOfStation.setMajorPersons(majorPersons(listMedicalRecord));
		return coveragePersonsOfStation;
	}

	@Override
	public List<ChildPersons> getChildByAdress(String adress) throws Exception {
		listPersons = readFileJson.DataOfPersons().stream().filter(p -> adress.equals(p.getAddress()))
				.collect(Collectors.toList());
		for (final Persons p : listPersons) {
			listMedicalRecord = readFileJson.DataOfMedicalRecords().stream()
					.filter(m -> p.getLastName().equals(m.getLastName())).collect(Collectors.toList());
		}
		listMedicalRecord.stream()
		.filter(i -> getAgePersons(i).getYears() < 18);
		
		for(final Medicalrecords mr: listMedicalRecord) {
			listPersons = readFileJson.DataOfPersons().stream().filter(p -> mr.equals(p.getAddress()))
					.collect(Collectors.toList());
		}
		return null;
	}

	@Override
	public List<PhoneNumber> getPhoneNumberPersonsByStation(int station) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MecicalRecordByPerson> getPersonsByAdress(String adress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdressPersons> getAdressByStation(String adress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdressPersons> getMedicalRecordByPerson(String lastName, String firstName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getEmailByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @param listMedicalRecord
	 * @return calcul le nombre de mineur
	 * @throws Exception
	 */
	private long minorPersons(List<Medicalrecords> listMedicalRecord) throws Exception {
		 long numberIfMinor = listMedicalRecord.stream()
					.filter(i -> getAgePersons(i).getYears() < 18)
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
		 long numberIfMajor = listMedicalRecord.stream()
				.filter(i -> getAgePersons(i).getYears() > 17)
                .collect(Collectors.counting());
		return numberIfMajor;
	}

	  private static Period getAgePersons(Medicalrecords listMedicalRecord) {
		  LocalDate now = LocalDate.now();
		  LocalDateTime birthday = null;
		  Period period = null;
				 birthday = listMedicalRecord.getBirthdate().toInstant()
					      .atZone(ZoneId.systemDefault())
					      .toLocalDateTime();
        return period.between(birthday.toLocalDate(), now);
    }
}
