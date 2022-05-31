package br.com.tco.personapi.domain.person.usecase.exception;

import br.com.tco.personapi.domain.exception.BaseDomainException;

public class PersonRequestInvalidException extends BaseDomainException {

  protected static final int PERSON_REQUEST_ERROR_CODE = 1;

  public PersonRequestInvalidException(String message) {
    super(PERSON_REQUEST_ERROR_CODE, message);
  }
}
