package br.com.tco.personapi.domain.person.usecase;

import br.com.tco.personapi.domain.person.entites.Person;
import br.com.tco.personapi.domain.person.usecase.port.PersonRepository;
import br.com.tco.personapi.domain.person.usecase.validator.PersonValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SavePerson {
  final PersonRepository personRepository;

  public Person createPerson(Person person) {
    PersonValidator.validate(person);
    return personRepository.create(person);
  }
}
