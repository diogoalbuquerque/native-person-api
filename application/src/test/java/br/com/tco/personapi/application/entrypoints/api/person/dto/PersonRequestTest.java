package br.com.tco.personapi.application.entrypoints.api.person.dto;

import br.com.tco.personapi.application.ObjectTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PersonRequestTest extends ObjectTest {

  @Test
  void whenTransformPersonRequestToPerson_returnPerson() {
    final var personRequest = createPersonRequestTest();
    final var person = personRequest.toPerson();

    assertNotNull(person, "Person could not be null");
    assertEquals(personRequest.getName(), person.name(), "This value should be equal");
    assertEquals(personRequest.getAge(), person.age(), "This value should be equal");
  }
}
