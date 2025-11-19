package com.pizzaApp.pizzaApp.service;

import com.pizzaApp.pizzaApp.dto.request.PizzaRequestDto;
import com.pizzaApp.pizzaApp.dto.response.PizzaResponseDto;
import com.pizzaApp.pizzaApp.entity.Pizza;
import com.pizzaApp.pizzaApp.exception.NotFoundException;
import com.pizzaApp.pizzaApp.mapper.PizzaMapper;
import com.pizzaApp.pizzaApp.mapper.PizzaPatchMapper;
import com.pizzaApp.pizzaApp.repository.PizzaRepository;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.RequestToViewNameTranslator;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

@AllArgsConstructor
@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;
    private final PizzaPatchMapper pizzaPatchMapper;

    private final RequestToViewNameTranslator requestToViewNameTranslator;


    public PizzaResponseDto postPizza(PizzaRequestDto dto) {
        Pizza pizza = PizzaMapper.toEntity(dto);
        pizzaRepository.save(pizza);
        return PizzaMapper.toDto(pizza);
    }

    public List<PizzaResponseDto> getAllPizza() {
        return pizzaRepository.findAll()
                .stream()
                .map(PizzaMapper::toDto)
                .toList();
    }

    public PizzaResponseDto getByName(String name) {
        Pizza pizza = pizzaRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Пиццу под названием " + name + " не удалось найти!"));
        return PizzaMapper.toDto(pizza);
    }

    public Page<PizzaResponseDto> getPizzaByPage(int page,int size,String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return pizzaRepository.findAll(pageable)
                .map(PizzaMapper::toDto);
    }



    public PizzaResponseDto updatePizza(String name, PizzaRequestDto dto) {
        Pizza pizza = pizzaRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Пиццу под названием " + name + " не удалось найти!"));
        pizzaPatchMapper.updatePizzaFromDto(dto, pizza);
        pizzaRepository.save(pizza);
        return PizzaMapper.toDto(pizza);
    }


    public void deletePizza(String name) {
        if (!pizzaRepository.existsByName(name)) {
            throw new NotFoundException("Не удалось найти пиццу под названием " + name);
        }
        pizzaRepository.deleteByName(name);
    }
}
