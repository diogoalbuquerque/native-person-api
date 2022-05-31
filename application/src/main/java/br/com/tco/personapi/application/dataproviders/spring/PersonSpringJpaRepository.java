package br.com.tco.personapi.application.dataproviders.spring;

import br.com.tco.personapi.application.dataproviders.spring.entity.PersonEntity;
import br.com.tco.personapi.domain.person.entites.Person;
import br.com.tco.personapi.domain.person.usecase.port.PersonRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class PersonSpringJpaRepository implements PersonRepository {

  final PersonJpaRepository personJpaRepository;

  @Override
  public Person create(Person person) {
    final var entity = new PersonEntity(person);
    final var personEntity = personJpaRepository.save(entity);
    return personEntity.toPerson();
  }

  @Override
  public Optional<Person> findById(int id) {
    return personJpaRepository.findById(id).map(PersonEntity::toPerson);
  }
}
