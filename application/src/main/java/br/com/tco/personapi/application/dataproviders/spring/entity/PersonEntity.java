package br.com.tco.personapi.application.dataproviders.spring.entity;

import br.com.tco.personapi.domain.person.entites.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "person")
public class PersonEntity {
  @Id @GeneratedValue private Integer id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private int age;

  public PersonEntity(Person person) {
    super();
    this.id = person.id();
    this.name = person.name();
    this.age = person.age();
  }

  public Person toPerson() {
    return new Person(this.id, this.name, this.age);
  }
}
