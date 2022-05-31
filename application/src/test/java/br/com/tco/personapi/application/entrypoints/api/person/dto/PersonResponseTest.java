package br.com.tco.personapi.application.entrypoints.api.person.dto;

import br.com.tco.personapi.application.ObjectTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PersonResponseTest extends ObjectTest {
  @Test
  void whenCreatePersonResponseFromPerson_returnPersonResponse() {
    final var person = createPersonTest();
    final var personResponse = new PersonResponse(person);

    assertNotNull(personResponse, "Person response could not be null");
    assertEquals(person.id(), personResponse.id(), "This value should be equal");
    assertEquals(person.name(), personResponse.name(), "This value should be equal");
    assertEquals(person.age(), personResponse.age(), "This value should be equal");
  }
}
