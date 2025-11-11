package com.pizzaApp.pizzaApp.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PizzaResponseDto {

    private String name;
    private String descriptions;
    private String cookingTime;

}
