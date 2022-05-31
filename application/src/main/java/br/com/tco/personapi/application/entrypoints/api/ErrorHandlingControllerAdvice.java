package br.com.tco.personapi.application.entrypoints.api;

import br.com.tco.personapi.domain.exception.BaseDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

import static java.util.stream.Collectors.toList;

@ControllerAdvice
@RequiredArgsConstructor
public class ErrorHandlingControllerAdvice extends ResponseEntityExceptionHandler {

  protected static final int METHOD_ARGUMENT_ERROR_CODE = 99;
  private final MessageSource messageSource;

  @ExceptionHandler(BaseDomainException.class)
  public ResponseEntity<Object> handleBaseBusinesException(
      BaseDomainException ex, WebRequest request) {

    var status = HttpStatus.BAD_REQUEST;
    var validationErrorResponse =
        new ValidationErrorResponse(
            status.value(), ex.getMessage(), ex.getErrorCode(), LocalDateTime.now());

    return this.handleExceptionInternal(
        ex, validationErrorResponse, new HttpHeaders(), status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    var violations =
        ex.getBindingResult().getAllErrors().stream()
            .map(
                error -> {
                  var name = ((FieldError) error).getField();
                  var message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
                  return new Violation(name, message);
                })
            .collect(toList());

    var validationErrorResponse =
        new ValidationErrorResponse(
            status.value(),
            ex.getMessage(),
            METHOD_ARGUMENT_ERROR_CODE,
            LocalDateTime.now(),
            violations);

    return super.handleExceptionInternal(ex, validationErrorResponse, headers, status, request);
  }
}
