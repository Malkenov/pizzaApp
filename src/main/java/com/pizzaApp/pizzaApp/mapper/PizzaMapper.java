package com.pizzaApp.pizzaApp.mapper;


import com.pizzaApp.pizzaApp.dto.request.PizzaRequestDto;
import com.pizzaApp.pizzaApp.dto.response.PizzaResponseDto;
import com.pizzaApp.pizzaApp.entity.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PizzaMapper {

    Pizza toEntity(PizzaRequestDto dto);

    PizzaResponseDto toDto(Pizza pizza);
}
