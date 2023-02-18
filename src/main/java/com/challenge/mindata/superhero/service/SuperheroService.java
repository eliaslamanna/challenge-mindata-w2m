package com.challenge.mindata.superhero.service;

import com.challenge.mindata.exception.exception.ItemNotFoundException;
import com.challenge.mindata.superhero.controller.request.CreateSuperheroRequest;
import com.challenge.mindata.superhero.controller.request.UpdateSuperheroRequest;
import com.challenge.mindata.superhero.controller.response.SuperheroResponse;
import com.challenge.mindata.superhero.repository.SuperheroRepository;
import com.challenge.mindata.superhero.repository.entity.Superhero;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class SuperheroService {

    private final SuperheroRepository superheroRepository;

    private final ModelMapper modelMapper;

    public SuperheroResponse create(CreateSuperheroRequest createSuperheroRequest) {
        var createdSuperhero = superheroRepository.save(modelMapper.map(createSuperheroRequest, Superhero.class));
        return modelMapper.map(createdSuperhero, SuperheroResponse.class);
    }

    public List<SuperheroResponse> getAll() {
        return superheroRepository.findAll().stream().map(superhero -> modelMapper.map(superhero, SuperheroResponse.class)).collect(toList());
    }

    public SuperheroResponse getById(String id) {
        var superhero = superheroRepository.findById(id).orElseThrow(ItemNotFoundException::new);
        return modelMapper.map(superhero, SuperheroResponse.class);
    }

    public SuperheroResponse update(UpdateSuperheroRequest updatesRequest, String id) {
        var superheroToUpdate = modelMapper.map(this.getById(id), Superhero.class);
        modelMapper.map(updatesRequest, superheroToUpdate);

        return modelMapper.map(superheroRepository.save(superheroToUpdate), SuperheroResponse.class);
    }

    public void delete(String id) {
        superheroRepository.deleteById(id);
    }

}
