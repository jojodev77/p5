package Dto;

import Entity.Persons;
import lombok.Data;

@Data
public class ChildPersons {
String firstName;
String lastName;
Persons family;
}
