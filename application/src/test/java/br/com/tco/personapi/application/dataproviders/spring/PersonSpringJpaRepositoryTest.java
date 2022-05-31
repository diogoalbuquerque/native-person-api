package br.com.tco.personapi.application.dataproviders.spring;

import br.com.tco.personapi.application.ObjectTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PersonSpringJpaRepositoryTest extends ObjectTest {

  @Autowired PersonJpaRepository personJpaRepository;
  PersonSpringJpaRepository personSpringJpaRepository;

  @BeforeEach
  void setUp() {
    personSpringJpaRepository = new PersonSpringJpaRepository(personJpaRepository);
  }

  @Test
  void whenCreatePerson_returnCreatedPerson() {
    final var person = createPersonTest();
    final var personCreated = personSpringJpaRepository.create(person);
    assertEquals(person.id(), personCreated.id(), "This value should be equal");
    assertEquals(person.name(), personCreated.name(), "This value should be equal");
    assertEquals(person.age(), personCreated.age(), "This value should be equal");
  }

  @Test
  void whenCreateAndFindPerson_returnNewCreatedPerson() {
    final var personCreated = personSpringJpaRepository.create(createPersonToBeSavedTest());
    final var optionalPersonFound = personSpringJpaRepository.findById(personCreated.id());
    assertTrue(optionalPersonFound.isPresent(), "Person should be found");
    final var personFound = optionalPersonFound.get();
    assertEquals(personCreated.id(), personFound.id(), "This value should be equal");
    assertEquals(personCreated.name(), personFound.name(), "This value should be equal");
    assertEquals(personCreated.age(), personFound.age(), "This value should be equal");
  }

  @Test
  void whenFindNonexistentPerson_returnEmpty() {
    final var optionalPersonFound = personSpringJpaRepository.findById(99);
    assertTrue(optionalPersonFound.isEmpty(), "Person should be not found");
  }
}
