package com.mercadolivre.desafioquality.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> exceptionHandlerApi(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> errorHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {

        return new ResponseEntity<>("Invalid field", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> nullPointerException(NullPointerException e) {

        return new ResponseEntity<>("Invalid body", HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(DistrictNotFound.class)
    public ResponseEntity<Object> districtNotFound(){
        return new ResponseEntity<>("District not found", HttpStatus.BAD_REQUEST);
    }

}
