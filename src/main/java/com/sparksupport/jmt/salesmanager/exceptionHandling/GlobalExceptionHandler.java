package com.sparksupport.jmt.salesmanager.exceptionHandling;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
     * Handles ResourceNotFoundException and returns a custom error response with details
     * including timestamp, error message, requested URI, and HTTP status code 404 (NOT FOUND).
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest request) {
        ErrorDetails ed = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false),
                HttpStatus.NOT_FOUND);
        return new ResponseEntity<Object>(ed, HttpStatus.NOT_FOUND);
    }

    /*
     * Handles MethodArgumentNotValidException and returns a map containing field names as keys
     * and corresponding error messages as values. This is useful for providing detailed
     * validation error information in the response body.
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errorMap = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach((error) -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return errorMap;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
    }
}
