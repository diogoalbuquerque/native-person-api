package br.com.tco.personapi.domain.person.usecase;

import br.com.tco.personapi.domain.person.entites.Person;
import br.com.tco.personapi.domain.person.usecase.exception.PersonRequestInvalidException;
import br.com.tco.personapi.domain.person.usecase.port.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SavePersonTest {

  SavePerson savePerson;
  PersonRepository personRepository;

  @BeforeEach
  void setUp() {
    personRepository = Mockito.mock(PersonRepository.class);
    savePerson = new SavePerson(personRepository);
  }

  @Test
  void whenCreateCorrectPerson_returnPerson() {
    final var correctPerson = new Person(1, "User Test", 18);
    Mockito.when(personRepository.create(correctPerson)).thenReturn(correctPerson);
    final var person = savePerson.createPerson(correctPerson);
    assertEquals(correctPerson.id(), person.id(), "This value should be equal");
    assertEquals(correctPerson.name(), person.name(), "This value should be equal");
    assertEquals(correctPerson.age(), person.age(), "This value should be equal");
  }

  @Test
  void whenCreateIncorrectPerson_throwsException() {
    final var incorrectPerson = new Person(1, "User Test", null);
    final var ex =
        assertThrows(
            PersonRequestInvalidException.class, () -> savePerson.createPerson(incorrectPerson));
    assertEquals("Person age could not be null", ex.getMessage());
  }
}
