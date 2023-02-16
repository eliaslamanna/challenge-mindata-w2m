package com.challenge.mindata.superhero.service;

import com.challenge.mindata.superhero.controller.request.CreateSuperheroRequest;
import com.challenge.mindata.superhero.repository.SuperheroRepository;
import com.challenge.mindata.superhero.repository.entity.Superhero;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SuperheroService {

    private final SuperheroRepository superheroRepository;

    private final ModelMapper modelMapper;

    public Superhero create(CreateSuperheroRequest createSuperheroRequest) {
        return superheroRepository.save(modelMapper.map(createSuperheroRequest, Superhero.class));
    }

    public List<Superhero> getAll() {
        return superheroRepository.findAll();
    }

}
