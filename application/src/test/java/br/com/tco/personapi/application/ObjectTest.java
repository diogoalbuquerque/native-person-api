package br.com.tco.personapi.application;

import br.com.tco.personapi.application.dataproviders.spring.entity.PersonEntity;
import br.com.tco.personapi.application.entrypoints.api.person.dto.PersonRequest;
import br.com.tco.personapi.domain.person.entites.Person;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.json.JsonParseException;

import java.io.IOException;

public class ObjectTest {
  protected final Person createPersonTest() {
    return new Person(1, "User Test", 18);
  }

  protected final Person createPersonToBeSavedTest() {
    return new Person(null, "Another Person", 18);
  }

  protected final PersonEntity createPersonEntityTest() {
    final var personEntity = new PersonEntity();
    personEntity.setId(1);
    personEntity.setName("User Test");
    personEntity.setAge(18);
    return personEntity;
  }

  protected final PersonRequest createPersonRequestTest() {
    return new PersonRequest("User Test", 18);
  }

  protected String toJson(Object object) throws Exception {
    return new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .writeValueAsString(object);
  }

  protected <T> T fromJson(String json, Class<T> clazz) throws JsonParseException, IOException {
    ObjectMapper objectMapper =
        new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.registerModule(new JavaTimeModule());
    return objectMapper.readValue(json, clazz);
  }
}
