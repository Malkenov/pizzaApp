package com.pizzaApp.pizzaApp.service;

import com.pizzaApp.pizzaApp.dto.request.PizzaRequestDto;
import com.pizzaApp.pizzaApp.dto.response.PizzaResponseDto;
import com.pizzaApp.pizzaApp.entity.Pizza;
import com.pizzaApp.pizzaApp.entity.User;
import com.pizzaApp.pizzaApp.exception.NotFoundException;
import com.pizzaApp.pizzaApp.mapper.PizzaMapper;
import com.pizzaApp.pizzaApp.mapper.PizzaPatchMapper;
import com.pizzaApp.pizzaApp.repository.PizzaRepository;
import com.pizzaApp.pizzaApp.repository.UserRepository;
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
    private final PizzaMapper pizzaMapper;
    private final UserRepository userRepository;


    public PizzaResponseDto postPizza(PizzaRequestDto dto,String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("Пользователь не найден!"));
        Pizza pizza = pizzaMapper.toEntity(dto);
        pizza.setUser(user);
        pizzaRepository.save(pizza);
        return pizzaMapper.toDto(pizza);
    }

    public List<PizzaResponseDto> getAllPizza(String email) {
        return pizzaRepository.findByUserEmail(email)
                .stream()
                .map(pizzaMapper::toDto)
                .toList();
    }

    public PizzaResponseDto getByName(String name,String email) {
        Pizza pizza = pizzaRepository.findByNameAndUserEmail(name, email)
                .orElseThrow(() ->
                        new RuntimeException("Пиццу под названием " + name + " не удалось найти у данного пользователя!")
                );
        return pizzaMapper.toDto(pizza);
    }

    public Page<PizzaResponseDto> getPizzaByPage(int page,int size,String sortBy){
        Pageable pageable = PageRequest.of(page,size, Sort.by(sortBy));
        return pizzaRepository.findAll(pageable)
                .map(pizzaMapper::toDto);
    }


    public PizzaResponseDto updatePizza(String name, PizzaRequestDto dto,String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new NotFoundException("Пользователь не найден!"));
        Pizza pizza = pizzaRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("Пиццу под названием " + name + " не удалось найти!"));
        pizza.setUser(user);
        pizzaPatchMapper.updatePizzaFromDto(dto, pizza);
        pizzaRepository.save(pizza);
        return pizzaMapper.toDto(pizza);
    }


    public void deletePizza(String name,String email) {
        if (!pizzaRepository.existsByName(name)) {
            throw new NotFoundException("Не удалось найти пиццу под названием " + name);
        }
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Нету такого пользователя!"));

        pizzaRepository.deleteByNameAndUserEmail(name,email);
    }
}
