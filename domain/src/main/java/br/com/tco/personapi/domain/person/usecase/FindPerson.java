package br.com.tco.personapi.domain.person.usecase;

import br.com.tco.personapi.domain.person.entites.Person;
import br.com.tco.personapi.domain.person.usecase.port.PersonRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class FindPerson {
  final PersonRepository personRepository;

  public Optional<Person> findById(int id) {
    return personRepository.findById(id);
  }
}
