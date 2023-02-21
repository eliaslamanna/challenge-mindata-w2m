package com.challenge.mindata.superhero.controller;

import com.challenge.mindata.superhero.controller.request.CreateSuperheroRequest;
import com.challenge.mindata.superhero.controller.request.SuperheroSearchParametersRequest;
import com.challenge.mindata.superhero.controller.request.UpdateSuperheroRequest;
import com.challenge.mindata.superhero.controller.response.SuperheroResponse;
import com.challenge.mindata.superhero.service.SuperheroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@Tag(name = "Superheroes", description = "Operations regarding Superheroes")
@RequiredArgsConstructor
@RequestMapping("/superheroes")
public class SuperheroController {

    private final ModelMapper modelMapper;

    private final SuperheroService superheroService;

    @PostMapping
    @Operation(description = "Creates a Superhero.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Superhero Created."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "409", description = "Conflict."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    public ResponseEntity<SuperheroResponse> create(@Valid @RequestBody CreateSuperheroRequest createSuperheroRequest) {
        return ok(modelMapper.map(superheroService.create(createSuperheroRequest), SuperheroResponse.class));
    }

    @GetMapping("/{id}")
    @Operation(description = "Gets a Superhero.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retrieves Superhero's information."),
            @ApiResponse(responseCode = "404", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    public ResponseEntity<SuperheroResponse> get(@PathVariable String id) {
        return ok(superheroService.getById(id));
    }

    @GetMapping
    @Operation(description = "Gets all Superheroes.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retrieves information from all Superheroes."),
            @ApiResponse(responseCode = "404", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    public ResponseEntity<List<SuperheroResponse>> getAll() {
        return ok(superheroService.getAll());
    }

    @GetMapping("/search")
    @Operation(description = "Search for Superheroes.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Retrieves information from desired Superheroes."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    public ResponseEntity<List<SuperheroResponse>> search(@RequestBody SuperheroSearchParametersRequest searchParametersRequest) {
        return ok(superheroService.search(searchParametersRequest));
    }

    @PutMapping("/{id}")
    @Operation(description = "Update a Superhero.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Superhero Updated."),
            @ApiResponse(responseCode = "400", description = "Bad request."),
            @ApiResponse(responseCode = "404", description = "Not found."),
            @ApiResponse(responseCode = "409", description = "Conflict."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    public ResponseEntity<SuperheroResponse> update(@PathVariable String id, @Valid @RequestBody UpdateSuperheroRequest updateRequest) {
        return ok(superheroService.update(updateRequest, id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Delete a Superhero.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Superhero Deleted."),
            @ApiResponse(responseCode = "404", description = "Not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")})
    public ResponseEntity<Void> delete(@PathVariable String id) {
        superheroService.delete(id);
        return noContent().build();
    }

}
