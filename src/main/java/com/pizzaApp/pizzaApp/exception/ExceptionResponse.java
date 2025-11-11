package com.pizzaApp.pizzaApp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Setter
@Getter
public class ExceptionResponse {

    private HttpStatus httpStatus;
    private String message;
    private LocalDateTime dateTime;

    public ExceptionResponse(HttpStatus httpStatus,String name){
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
