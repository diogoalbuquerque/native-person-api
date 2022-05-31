package br.com.tco.personapi.application.entrypoints.api.person.dto;

import br.com.tco.personapi.domain.person.entites.Person;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PersonRequest {
  @NotBlank private String name;

  @NotNull
  @Min(18)
  private Integer age;

  public Person toPerson() {
    return new Person(null, this.name, this.age);
  }
}
