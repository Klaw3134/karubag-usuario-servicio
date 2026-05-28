package cl.karubag.usuario.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
* Manejador global de excepciones para todo el microservicio.
*
* Atrapa las excepciones lanzadas en cualquier capa (Controller, Service, etc.)
* y las convierte en respuestas HTTP estructuradas con JSON.
*/
@RestControllerAdvice
public class GlobalExceptionHandler {

/**
* Maneja ResourceNotFoundException → HTTP 404 Not Found
*/
@ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ErrorResponse> handleResourceNotFound(
ResourceNotFoundException ex,
HttpServletRequest request) {

ErrorResponse error = new ErrorResponse(
HttpStatus.NOT_FOUND.value(),
"Not Found",
ex.getMessage(),
request.getRequestURI()
);

return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
}

/**
* Maneja DuplicateResourceException → HTTP 409 Conflict
*/
@ExceptionHandler(DuplicateResourceException.class)
public ResponseEntity<ErrorResponse> handleDuplicateResource(
DuplicateResourceException ex,
HttpServletRequest request) {

ErrorResponse error = new ErrorResponse(
HttpStatus.CONFLICT.value(),
"Conflict",
ex.getMessage(),
request.getRequestURI()
);

return new ResponseEntity<>(error, HttpStatus.CONFLICT);
}

/**
* Maneja cualquier otra excepción inesperada → HTTP 500 Internal Server Error
*/
@ExceptionHandler(Exception.class)
public ResponseEntity<ErrorResponse> handleGenericException(
Exception ex,
HttpServletRequest request) {

ErrorResponse error = new ErrorResponse(
HttpStatus.INTERNAL_SERVER_ERROR.value(),
"Internal Server Error",
"Ocurrió un error inesperado: " + ex.getMessage(),
request.getRequestURI()
);

return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
