package br.com.tco.personapi.application;

import br.com.tco.personapi.application.entrypoints.api.person.PersonController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ApplicationTests {

  @Autowired PersonController personController;

  @Test
  void contextLoads() {
    assertNotNull(personController);
  }
}
