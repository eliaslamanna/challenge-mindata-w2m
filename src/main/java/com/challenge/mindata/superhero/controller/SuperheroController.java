package com.challenge.mindata.superhero.controller;

import com.challenge.mindata.superhero.controller.request.CreateSuperheroRequest;
import com.challenge.mindata.superhero.controller.response.SuperheroResponse;
import com.challenge.mindata.superhero.service.SuperheroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequiredArgsConstructor
@RequestMapping("/superheroes")
public class SuperheroController {

    private final ModelMapper modelMapper;

    private final SuperheroService superheroService;

    @PostMapping
    public ResponseEntity<SuperheroResponse> create(@Valid @RequestBody CreateSuperheroRequest createSuperheroRequest) {
        return ok(modelMapper.map(superheroService.create(createSuperheroRequest), SuperheroResponse.class));
    }

    @GetMapping
    public ResponseEntity<List<SuperheroResponse>> getAll() {
        var superheros = superheroService.getAll().stream().map(superhero -> modelMapper.map(superhero, SuperheroResponse.class)).toList();
        return ok(superheros);
    }

}
