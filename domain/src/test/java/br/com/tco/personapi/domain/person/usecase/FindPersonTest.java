package br.com.tco.personapi.domain.person.usecase;

import br.com.tco.personapi.domain.person.entites.Person;
import br.com.tco.personapi.domain.person.usecase.port.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FindPersonTest {
  FindPerson findPerson;
  PersonRepository personRepository;

  @BeforeEach
  void setUp() {
    personRepository = Mockito.mock(PersonRepository.class);
    findPerson = new FindPerson(personRepository);
  }

  @Test
  void whenFindIdExist_returnPerson() {
    Mockito.when(personRepository.findById(1))
        .thenReturn(Optional.of(new Person(1, "User Test", 18)));
    final var person = findPerson.findById(1);
    assertTrue(person.isPresent(), "Person should be found");
  }

  @Test
  void whenFindIdNotExist_returnPerson() {
    final var person = findPerson.findById(1);
    assertTrue(person.isEmpty(), "No results should be found");
  }
}
