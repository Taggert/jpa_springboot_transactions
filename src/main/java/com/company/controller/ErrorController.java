package com.company.controller;

import com.company.model.exceptions.AbstractAPIException;
import com.company.model.web.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> getErrorResponse(AbstractAPIException e){
        return new ResponseEntity<>(e.getErrorResponse(), HttpStatus.BAD_REQUEST);
    }

}