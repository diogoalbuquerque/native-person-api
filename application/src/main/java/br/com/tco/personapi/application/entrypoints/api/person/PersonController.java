package br.com.tco.personapi.application.entrypoints.api.person;

import br.com.tco.personapi.application.entrypoints.api.person.dto.PersonRequest;
import br.com.tco.personapi.application.entrypoints.api.person.dto.PersonResponse;
import br.com.tco.personapi.domain.person.usecase.FindPerson;
import br.com.tco.personapi.domain.person.usecase.SavePerson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/person")
@RequiredArgsConstructor
public class PersonController {
  final FindPerson findPerson;
  final SavePerson savePerson;

  @GetMapping("/{id}")
  public ResponseEntity<PersonResponse> get(@PathVariable Integer id) {
    final var optionalPerson = findPerson.findById(id);

    return optionalPerson
        .map(value -> ResponseEntity.ok(new PersonResponse(value)))
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<PersonResponse> post(
      @Valid @RequestBody PersonRequest personRequest, UriComponentsBuilder uriComponentsBuilder) {

    var person = savePerson.createPerson(personRequest.toPerson());

    UriComponents uriComponents =
        uriComponentsBuilder.path("/v1/person/{id}").buildAndExpand(person.id());
    var location = uriComponents.toUri();

    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    headers.put("Location", List.of(location.toString()));

    return new ResponseEntity<>(new PersonResponse(person), headers, HttpStatus.CREATED);
  }
}
