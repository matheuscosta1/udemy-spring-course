package com.udemy.spring.course.resources.exception;

import com.udemy.spring.course.services.exception.AuthorizationException;
import com.udemy.spring.course.services.exception.DataIntegrityException;
import com.udemy.spring.course.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException exception, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), Date.from(Instant.now()));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException exception, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), exception.getMessage(), Date.from(Instant.now()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> argumentNotValid(MethodArgumentNotValidException exception, HttpServletRequest request){
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error", Date.from(Instant.now()));
        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            error.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> objectNotFound(AuthorizationException exception, HttpServletRequest request){
        StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), exception.getMessage(), Date.from(Instant.now()));
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

}