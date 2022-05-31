package br.com.tco.personapi.application.dataproviders.spring.entity;

import br.com.tco.personapi.application.ObjectTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonEntityTest extends ObjectTest {

  @Test
  void whenCreatePersonEntityFromPerson_returnPersonEntity() {
    final var person = createPersonTest();
    final var personEntity = new PersonEntity(person);

    assertNotNull(personEntity, "Person entity could not be null");
    assertEquals(person.id(), personEntity.getId(), "This value should be equal");
    assertEquals(person.name(), personEntity.getName(), "This value should be equal");
    assertEquals(person.age(), personEntity.getAge(), "This value should be equal");
  }

  @Test
  void whenTransformPersonEntityToPerson_returnPerson() {
    final var personEntity = createPersonEntityTest();
    final var person = personEntity.toPerson();

    assertNotNull(person, "Person entity could not be null");
    assertEquals(personEntity.getId(), person.id(), "This value should be equal");
    assertEquals(personEntity.getName(), person.name(), "This value should be equal");
    assertEquals(personEntity.getAge(), person.age(), "This value should be equal");
  }
}
