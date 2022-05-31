package br.com.tco.personapi.domain.person.usecase.validator;

import br.com.tco.personapi.domain.person.entites.Person;
import br.com.tco.personapi.domain.person.usecase.exception.PersonRequestInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonValidatorTest {

  @Test
  void whenNullPerson_throwsException() {
    Exception ex =
        assertThrows(PersonRequestInvalidException.class, () -> PersonValidator.validate(null));
    assertEquals("Person could not be null", ex.getMessage());
  }

  @Test
  void whenNullPersonName_throwsException() {
    var personTest = new Person(1, null, 18);
    Exception ex =
        assertThrows(
            PersonRequestInvalidException.class, () -> PersonValidator.validate(personTest));
    assertEquals("Person name could not be null", ex.getMessage());
  }

  @Test
  void whenNullPersonAge_throwsException() {
    var personTest = new Person(1, "User Test", null);
    Exception ex =
        assertThrows(
            PersonRequestInvalidException.class, () -> PersonValidator.validate(personTest));
    assertEquals("Person age could not be null", ex.getMessage());
  }

  @Test
  void whenPersonAgeLessThan18_throwsException() {
    var personTest = new Person(1, "User Test", 15);
    Exception ex =
        assertThrows(
            PersonRequestInvalidException.class, () -> PersonValidator.validate(personTest));
    assertEquals("Person age could not be less than 18", ex.getMessage());
  }
}
