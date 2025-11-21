package com.pizzaApp.pizzaApp.exception;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlerNotFound(NotFoundException ex) {
        ExceptionResponse exception = new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionResponse> handlerBadRequest(BadRequestException ex) {
        ExceptionResponse exception = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PropertyReferenceException.class)
    public void PropertyReference(PropertyReferenceException ex){
        throw new NotFoundException("Передан неверный параметр сортировки " + ex.getPropertyName());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidation(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult() // объект, дает всю информацию о валидации
                .getFieldErrors()              // из всей информации извлекает список ошибок 
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()                   // дает первую ошибку из потока
                .orElse("Validation error");
        ExceptionResponse exception = new ExceptionResponse(
                HttpStatus.BAD_REQUEST,
                message
        );
        return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
    }
}
