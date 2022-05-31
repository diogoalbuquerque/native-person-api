package br.com.tco.personapi.domain.person.usecase.port;

import br.com.tco.personapi.domain.person.entites.Person;

import java.util.Optional;

public interface PersonRepository {
  Person create(Person person);

  Optional<Person> findById(int id);
}
