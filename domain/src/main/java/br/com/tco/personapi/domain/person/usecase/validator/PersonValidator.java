package br.com.tco.personapi.domain.person.usecase.validator;

import br.com.tco.personapi.domain.person.entites.Person;
import br.com.tco.personapi.domain.person.usecase.exception.PersonRequestInvalidException;

import java.util.Objects;

public class PersonValidator {

  public static void validate(Person person) {
    if (Objects.isNull(person)) {
      throw new PersonRequestInvalidException("Person could not be null");
    } else if (Objects.isNull(person.name())) {
      throw new PersonRequestInvalidException("Person name could not be null");
    } else if (Objects.isNull(person.age())) {
      throw new PersonRequestInvalidException("Person age could not be null");
    } else if (person.age() < 18) {
      throw new PersonRequestInvalidException("Person age could not be less than 18");
    }
  }
}
