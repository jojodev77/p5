package p5SafetyNet.p5SafetyNet.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "firestations")
public class Firestations {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	String address;
	int station;
}
