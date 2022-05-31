package br.com.tco.personapi.application.entrypoints.api.person;

import br.com.tco.personapi.application.ObjectTest;
import br.com.tco.personapi.application.entrypoints.api.ValidationErrorResponse;
import br.com.tco.personapi.application.entrypoints.api.person.dto.PersonRequest;
import br.com.tco.personapi.application.entrypoints.api.person.dto.PersonResponse;
import br.com.tco.personapi.domain.person.usecase.FindPerson;
import br.com.tco.personapi.domain.person.usecase.SavePerson;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest extends ObjectTest {

  @Autowired public MockMvc mockMvc;

  @MockBean FindPerson findPerson;
  @MockBean SavePerson savePerson;

  @Test
  void whenGetExistentPerson_returnOkWithPersonResponse() throws Exception {

    final var personTest = createPersonTest();
    Mockito.when(findPerson.findById(personTest.id())).thenReturn(Optional.of(personTest));

    mockMvc
        .perform(get("/v1/person/" + personTest.id()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(personTest.id())))
        .andExpect(jsonPath("$.name", is(personTest.name())))
        .andExpect(jsonPath("$.age", is(personTest.age())));
  }

  @Test
  void whenGetNonexistentPerson_returnNotFound() throws Exception {
    Mockito.when(findPerson.findById(99)).thenReturn(Optional.empty());
    mockMvc.perform(get("/v1/person/" + 99)).andExpect(status().isNotFound());
  }

  @Test
  void whenPostCorrectPersonRequest_returnOkWithPersonResponse() throws Exception {

    final var personTest = createPersonTest();
    Mockito.when(savePerson.createPerson(any())).thenReturn(personTest);

    final var result =
        mockMvc
            .perform(
                post("/v1/person")
                    .content(toJson(createPersonRequestTest()))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id", is(personTest.id())))
            .andExpect(jsonPath("$.name", is(personTest.name())))
            .andExpect(jsonPath("$.age", is(personTest.age())))
            .andReturn();

    final var personResponse =
        fromJson(result.getResponse().getContentAsString(), PersonResponse.class);
    assertNotNull(personResponse.id(), "This value should be not null");
    assertEquals(personTest.name(), personResponse.name(), "This value should be equal");
    assertEquals(personTest.age(), personResponse.age(), "This value should be equal");
  }

  @Test
  void whenPostPersonRequestWithoutName_returnBadRequest() throws Exception {

    var incorrectPersonRequest = new PersonRequest(null, 18);

    final var result =
        mockMvc
            .perform(
                post("/v1/person")
                    .content(toJson(incorrectPersonRequest))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();

    final var validationErrorResponse =
        fromJson(result.getResponse().getContentAsString(), ValidationErrorResponse.class);
    assertEquals(99, validationErrorResponse.getCode(), "This value should be equal");
    assertEquals(
        "name",
        validationErrorResponse.getViolations().get(0).getName(),
        "This value should be equal");
    assertEquals(
        "must not be blank",
        validationErrorResponse.getViolations().get(0).getMessage(),
        "This value should be equal");
  }

  @Test
  void whenPostPersonRequestWithoutAge_returnBadRequest() throws Exception {

    var incorrectPersonRequest = new PersonRequest("User Test", null);

    final var result =
        mockMvc
            .perform(
                post("/v1/person")
                    .content(toJson(incorrectPersonRequest))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();

    final var validationErrorResponse =
        fromJson(result.getResponse().getContentAsString(), ValidationErrorResponse.class);
    assertEquals(99, validationErrorResponse.getCode(), "This value should be equal");
    assertEquals(
        "age",
        validationErrorResponse.getViolations().get(0).getName(),
        "This value should be equal");
    assertEquals(
        "must not be null",
        validationErrorResponse.getViolations().get(0).getMessage(),
        "This value should be equal");
  }

  @Test
  void whenPostPersonRequestWithAgeLessThan18_returnBadRequest() throws Exception {

    var incorrectPersonRequest = new PersonRequest("User Test", 15);

    final var result =
        mockMvc
            .perform(
                post("/v1/person")
                    .content(toJson(incorrectPersonRequest))
                    .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isBadRequest())
            .andReturn();

    final var validationErrorResponse =
        fromJson(result.getResponse().getContentAsString(), ValidationErrorResponse.class);
    assertEquals(99, validationErrorResponse.getCode(), "This value should be equal");
    assertEquals(
        "age",
        validationErrorResponse.getViolations().get(0).getName(),
        "This value should be equal");
    assertEquals(
        "must be greater than or equal to 18",
        validationErrorResponse.getViolations().get(0).getMessage(),
        "This value should be equal");
  }
}
