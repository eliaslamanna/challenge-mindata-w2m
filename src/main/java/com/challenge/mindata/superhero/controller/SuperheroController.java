package com.challenge.mindata.superhero.controller;

import com.challenge.mindata.superhero.controller.request.CreateSuperheroRequest;
import com.challenge.mindata.superhero.controller.request.UpdateSuperheroRequest;
import com.challenge.mindata.superhero.controller.response.SuperheroResponse;
import com.challenge.mindata.superhero.service.SuperheroService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
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

    @GetMapping("/{id}")
    public ResponseEntity<SuperheroResponse> get(@PathVariable String id) {
        return ok(superheroService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<SuperheroResponse>> getAll() {
        return ok(superheroService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperheroResponse> update(@PathVariable String id, @Valid @RequestBody UpdateSuperheroRequest updateRequest) {
        return ok(superheroService.update(updateRequest, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        superheroService.delete(id);
        return noContent().build();
    }

}
