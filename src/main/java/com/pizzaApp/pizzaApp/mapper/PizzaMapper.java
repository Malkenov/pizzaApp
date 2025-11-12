package com.pizzaApp.pizzaApp.mapper;

import com.pizzaApp.pizzaApp.dto.request.PizzaRequestDto;
import com.pizzaApp.pizzaApp.dto.response.PizzaResponseDto;
import com.pizzaApp.pizzaApp.entity.Pizza;
import org.springframework.stereotype.Component;

@Component
public class PizzaMapper {

    public static Pizza toEntity(PizzaRequestDto dto) {
        return Pizza.builder()
                .name(dto.getName())
                .descriptions(dto.getDescriptions())
                .cookingTime(dto.getCookingTime())
                .build();
    }

    public static PizzaResponseDto toDto(Pizza pizza) {
        return PizzaResponseDto.builder()
                .name(pizza.getName())
                .descriptions(pizza.getDescriptions())
                .cookingTime(pizza.getCookingTime())
                .build();
    }
}
