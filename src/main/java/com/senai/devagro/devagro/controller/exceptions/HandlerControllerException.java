package com.senai.devagro.devagro.controller.exceptions;

import com.senai.devagro.devagro.dto.StandardErrorDTO;
import com.senai.devagro.devagro.service.exceptions.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Objects;

@RestControllerAdvice
public class HandlerControllerException {

    /*
    INTERNAL SERVER ERRORS
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<StandardErrorDTO> nullPointer(NullPointerException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is empty or null");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(EntityNullException.class)
    public ResponseEntity<StandardErrorDTO> entityNull(EntityNullException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is empty or null");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    /*
    BAD REQUESTS
     */

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardErrorDTO> handle(MethodArgumentNotValidException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is invalid");
        error.setMessage(Objects.requireNonNull(e.getFieldError()).getDefaultMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<StandardErrorDTO> handle(IllegalArgumentException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is invalid");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidQuantityException.class)
    public ResponseEntity<StandardErrorDTO> invalidQuantity(InvalidQuantityException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is invalid");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<StandardErrorDTO> invalidEnum(InvalidEnumException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is invalid");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InsufficientGrainStockException.class)
    public ResponseEntity<StandardErrorDTO> insufficientGrainStock(InsufficientGrainStockException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is invalid");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<StandardErrorDTO> entityAlreadyExists(EntityAlreadyExistsException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is invalid");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityAlreadyAssociatedException.class)
    public ResponseEntity<StandardErrorDTO> entityAlreadyAssociated(EntityAlreadyAssociatedException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is invalid");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

   @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<StandardErrorDTO> violatesForeignKey(SQLException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource is invalid");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /*
    NOT FOUND
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<StandardErrorDTO> entityNotFound(EntityNotFoundException e, HttpServletRequest request) {
        StandardErrorDTO error = new StandardErrorDTO();
        error.setInstantError(Instant.now());
        error.setError("Resource not found");
        error.setMessage(e.getLocalizedMessage());
        error.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
