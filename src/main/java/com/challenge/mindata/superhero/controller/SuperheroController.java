package com.challenge.mindata.superhero.controller;

import com.challenge.mindata.superhero.controller.request.CreateSuperheroRequest;
import com.challenge.mindata.superhero.controller.request.SuperheroSearchParametersRequest;
import com.challenge.mindata.superhero.controller.request.UpdateSuperheroRequest;
import com.challenge.mindata.superhero.controller.response.SuperheroResponse;
import com.challenge.mindata.superhero.service.SuperheroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@Api(tags = "Superhero API", value = "Operations regarding Superheroes")
@RestController
@RequiredArgsConstructor
@RequestMapping("/superheroes")
public class SuperheroController {

    private final ModelMapper modelMapper;

    private final SuperheroService superheroService;

    @PostMapping
    @ApiOperation(value = "Creates a Superhero.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Superhero Created."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 409, message = "Conflict."),
            @ApiResponse(code = 500, message = "Internal server error.")})
    public ResponseEntity<SuperheroResponse> create(@Valid @RequestBody CreateSuperheroRequest createSuperheroRequest) {
        return ok(modelMapper.map(superheroService.create(createSuperheroRequest), SuperheroResponse.class));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Gets a Superhero.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieves Superhero's information."),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 500, message = "Internal server error.")})
    public ResponseEntity<SuperheroResponse> get(@PathVariable String id) {
        return ok(superheroService.getById(id));
    }

    @GetMapping
    @ApiOperation(value = "Gets all Superheroes.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieves information from all Superheroes."),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 500, message = "Internal server error.")})
    public ResponseEntity<List<SuperheroResponse>> getAll() {
        return ok(superheroService.getAll());
    }

    @GetMapping("/search")
    @ApiOperation(value = "Searches for Superheroes.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Retrieves information from desired Superheroes."),
            @ApiResponse(code = 500, message = "Internal server error.")})
    public ResponseEntity<List<SuperheroResponse>> search(@RequestBody SuperheroSearchParametersRequest searchParametersRequest) {
        return ok(superheroService.search(searchParametersRequest));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updates a Superhero.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Superhero Updated."),
            @ApiResponse(code = 400, message = "Bad request."),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 409, message = "Conflict."),
            @ApiResponse(code = 500, message = "Internal server error.")})
    public ResponseEntity<SuperheroResponse> update(@PathVariable String id, @Valid @RequestBody UpdateSuperheroRequest updateRequest) {
        return ok(superheroService.update(updateRequest, id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletes a Superhero.")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Superhero Deleted."),
            @ApiResponse(code = 404, message = "Not found."),
            @ApiResponse(code = 500, message = "Internal server error.")})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        superheroService.delete(id);
        return noContent().build();
    }

}
