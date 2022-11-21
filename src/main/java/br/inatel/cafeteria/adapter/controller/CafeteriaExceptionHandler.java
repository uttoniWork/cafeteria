package br.inatel.cafeteria.adapter.controller;

import br.inatel.cafeteria.adapter.dto.ExceptionDescription;
import br.inatel.cafeteria.domain.exception.ItemNotExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class CafeteriaExceptionHandler {

    @ExceptionHandler(ItemNotExistException.class)
    public ResponseEntity<ExceptionDescription> handleBusinessException(ItemNotExistException ex){

        return ResponseEntity.badRequest().body(new ExceptionDescription(ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionDescription>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        final List<ExceptionDescription> errorMessages= new ArrayList<>();

        for (ObjectError error: ex.getAllErrors()) {
            errorMessages.add(new ExceptionDescription(error.getDefaultMessage(), LocalDateTime.now()));
        }

        return ResponseEntity.badRequest().body(errorMessages);
    }
}
