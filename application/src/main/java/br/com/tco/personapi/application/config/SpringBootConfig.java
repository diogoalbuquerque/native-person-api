package br.com.tco.personapi.application.config;

import br.com.tco.personapi.application.dataproviders.spring.PersonJpaRepository;
import br.com.tco.personapi.application.dataproviders.spring.PersonSpringJpaRepository;
import br.com.tco.personapi.domain.person.usecase.FindPerson;
import br.com.tco.personapi.domain.person.usecase.SavePerson;
import br.com.tco.personapi.domain.person.usecase.port.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SpringBootConfig {

  final PersonJpaRepository personJpaRepository;

  @Bean
  public PersonRepository repository() {
    return new PersonSpringJpaRepository(personJpaRepository);
  }

  @Bean
  public FindPerson createFindPerson() {
    return new FindPerson(this.repository());
  }

  @Bean
  public SavePerson createSavePerson() {
    return new SavePerson(this.repository());
  }
}
