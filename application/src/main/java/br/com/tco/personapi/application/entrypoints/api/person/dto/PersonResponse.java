package br.com.tco.personapi.application.entrypoints.api.person.dto;

import br.com.tco.personapi.domain.person.entites.Person;

public record PersonResponse (int id, String name, int age){
    public PersonResponse(Person person){
        this(person.id(), person.name(), person.age());
    }
}
