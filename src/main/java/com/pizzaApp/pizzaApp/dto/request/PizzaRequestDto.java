package com.pizzaApp.pizzaApp.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class PizzaRequestDto {

    @NotBlank(message = "Название пиццы обязательно!")
    private String name;

    @NotBlank(message = "Нужно описание пиццы")
    private String descriptions;

    @NotBlank(message = "Сколько требуется время на приготовление пиццы?")
    private String cookingTime;
}
