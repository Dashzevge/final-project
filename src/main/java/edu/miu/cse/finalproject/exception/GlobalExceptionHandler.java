package edu.miu.cse.finalproject.exception;

import edu.miu.cse.finalproject.exception.user.UserNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String>
    handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<String>
    handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(e.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>
    handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
}
