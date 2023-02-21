package com.challenge.mindata.unitTest;

import com.challenge.mindata.exception.exception.ItemNotFoundException;
import com.challenge.mindata.superhero.controller.request.CreateSuperheroRequest;
import com.challenge.mindata.superhero.controller.request.SuperheroSearchParametersRequest;
import com.challenge.mindata.superhero.controller.request.UpdateSuperheroRequest;
import com.challenge.mindata.superhero.controller.response.SuperheroResponse;
import com.challenge.mindata.superhero.service.SuperheroService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SuperheroUnitTests {

	@Test
	@Order(1)
	public void getAllSuperheroesRetrievesEverySuperhero(@Autowired SuperheroService superheroService) {
		var expectedSuperheroes = List.of(
				SuperheroResponse.builder().id(1L).name("Bruce Wayne").superheroName("Batman").build(),
				SuperheroResponse.builder().id(2L).name("Peter Parker").superheroName("Spider-Man").build(),
				SuperheroResponse.builder().id(3L).name("Tony Stark").superheroName("Iron Man").build());
		var retrievedSuperheroes = superheroService.getAll();
		Assertions.assertEquals(expectedSuperheroes,retrievedSuperheroes);
	}

	@Test
	public void createNewSuperheroCorrectly(@Autowired SuperheroService superheroService) {
		var expectedSuperhero = SuperheroResponse.builder().id(4L).name("New Name").superheroName("New Superhero").build();
		var createdSuperhero = superheroService.create(CreateSuperheroRequest.builder().name("New Name").superheroName("New Superhero").build());
		Assertions.assertEquals(expectedSuperhero,createdSuperhero);
	}

	@Test
	public void getSuperHeroById(@Autowired SuperheroService superheroService) {
		var expectedSuperhero = SuperheroResponse.builder().id(1L).name("Bruce Wayne").superheroName("Batman").build();
		var createdSuperhero = superheroService.getById(1L);
		Assertions.assertEquals(expectedSuperhero,createdSuperhero);
	}

	@Test
	public void updateSuperHero(@Autowired SuperheroService superheroService) {
		var expectedSuperhero = SuperheroResponse.builder().id(2L).name("Bruce Wayne Updated").superheroName("Batman Updated").build();
		var createdSuperhero = superheroService.update(UpdateSuperheroRequest.builder().name("Bruce Wayne Updated").superheroName("Batman Updated").build(), 2L);
		Assertions.assertEquals(expectedSuperhero,createdSuperhero);
	}

	@Test
	public void deleteSuperHero(@Autowired SuperheroService superheroService) {
		superheroService.delete(1L);
		Assertions.assertThrows(ItemNotFoundException.class, () -> superheroService.getById(1L));
	}

	@Test
	public void searchForSuperHero(@Autowired SuperheroService superheroService) {
		var expectedSuperheroes = List.of(SuperheroResponse.builder().id(1L).name("Bruce Wayne").superheroName("Batman").build());
		var wantedSuperheroes = superheroService.search(SuperheroSearchParametersRequest.builder().superheroName("Bat").build());
		Assertions.assertEquals(expectedSuperheroes, wantedSuperheroes);
	}

}
