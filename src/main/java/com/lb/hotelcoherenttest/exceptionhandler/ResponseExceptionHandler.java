package com.lb.hotelcoherenttest.exceptionhandler;

import com.lb.hotelcoherenttest.exceptions.ClientNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Object> handleConflict(ClientNotFoundException ex, WebRequest request) {
        Map<String, Object> body= new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleConflict(DateTimeParseException ex, WebRequest request) {
        log.error("Exception while parsing date: {}", ex.getMessage());
        log.error("Trace: {}", ex.getStackTrace());
        Map<String, Object> body= new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Incorrect date format. Please validate whether it is in format d-MM-yyyy");
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}
