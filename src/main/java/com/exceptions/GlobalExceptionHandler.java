package com.exceptions;

import com.Application;
import com.model.ExceptionObject;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Restful global exception handler
 *
 * GlobalExceptionHandler.java
 *
 * @author samuel Maina
 *
 * 03-10-2022
 *
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for creating a record that exists
     *
     * @return ExceptionObject and HttpStatus.CONFLICT
     */
    @ExceptionHandler(ResourceAlreadyExistException.class)
    public ResponseEntity<?> resourceAlreadyExists() {
        ExceptionObject exceptionObject = new ExceptionObject();
        exceptionObject.setMessage("Record exists");
        exceptionObject.setDate(new Date());
        return new ResponseEntity<>(exceptionObject, HttpStatus.CONFLICT);
    }

    /**
     * Exception handler for ResourceNotFoundException
     *
     * @return ResponseEntity HttpStatus.NOT_FOUND
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceDoesNotExist() {
        ExceptionObject exceptionObject = new ExceptionObject();
        exceptionObject.setMessage("Target resource not found");
        exceptionObject.setDate(new Date());
        return new ResponseEntity<>(exceptionObject, HttpStatus.NOT_FOUND);
    }

    /**
     * Exception handler for StaleSaleException
     *
     * @return ResponseEntity HttpStatus.CONFLICT
     */
    @ExceptionHandler(StaleSaleException.class)
    public ResponseEntity<?> saleAlreadyConfirmed() {
        ExceptionObject exceptionObject = new ExceptionObject();
        exceptionObject.setDate(new Date());
        exceptionObject.setMessage("Failed: customer member purchases must be confirmed within five minutes");
        Application.logger.error(exceptionObject);
        return new ResponseEntity<>(exceptionObject, HttpStatus.CONFLICT);
    }

    /**
     * Exception handler for SQLIntegrityConstraintViolationException
     *
     * @return exception body, HttpStatus.FORBIDDEN
     */
    @ExceptionHandler(java.sql.SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> sQLIntegrityConstraintViolationException() {
        ExceptionObject exception = new ExceptionObject();
        exception.setDate(new Date());
        exception.setMessage("Action forbidden, may interfere with data consistency");
        return new ResponseEntity<>(exception, HttpStatus.FORBIDDEN);
    }
}
