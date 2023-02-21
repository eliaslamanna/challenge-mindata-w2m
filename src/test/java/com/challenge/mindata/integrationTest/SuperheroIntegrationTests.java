package com.challenge.mindata.integrationTest;

import com.challenge.mindata.exception.exception.ItemNotFoundException;
import com.challenge.mindata.superhero.controller.request.CreateSuperheroRequest;
import com.challenge.mindata.superhero.controller.request.SuperheroSearchParametersRequest;
import com.challenge.mindata.superhero.controller.request.UpdateSuperheroRequest;
import com.challenge.mindata.superhero.controller.response.SuperheroResponse;
import com.challenge.mindata.superhero.service.SuperheroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SuperheroIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("Create a superhero should create a new superhero with a given name and superHero name")
	public void createNewSuperheroCorrectly(@Autowired SuperheroService superheroService) throws Exception {
		var request = CreateSuperheroRequest.builder().name("New Name").superheroName("New Superhero").build();

		var response = mockMvc.perform(post("/superheroes")
						.content(objectMapper.writeValueAsBytes(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		var expectedSuperhero = SuperheroResponse.builder().id(4L).name("New Name").superheroName("New Superhero").build();
		var createdSuperhero = objectMapper.readValue(response.getResponse().getContentAsString(), SuperheroResponse.class);
		Assertions.assertEquals(expectedSuperhero,createdSuperhero);
	}

	@Test
	@DisplayName("Get superhero by id should retrive the wanted superhero")
	public void getSuperHeroById(@Autowired SuperheroService superheroService) throws Exception {
		var response = mockMvc.perform(get(String.format("/superheroes/ %d", 1L)))
				.andExpect(status().isOk())
				.andReturn();

		var expectedSuperhero = SuperheroResponse.builder().id(1L).name("Bruce Wayne").superheroName("Batman").build();
		var retrievedSuperhero = objectMapper.readValue(response.getResponse().getContentAsString(), SuperheroResponse.class);
		Assertions.assertEquals(expectedSuperhero, retrievedSuperhero);
	}

	@Test
	@DisplayName("Update a superhero should update it's name and/or superheroName")
	public void updateSuperHero(@Autowired SuperheroService superheroService) throws Exception {
		var request = CreateSuperheroRequest.builder().name("Updated Name").superheroName("Updated Superhero").build();

		var response = mockMvc.perform(put(String.format("/superheroes/ %d", 2L))
						.content(objectMapper.writeValueAsBytes(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		var expectedSuperhero = SuperheroResponse.builder().id(2L).name("Updated Name").superheroName("Updated Superhero").build();
		var updatedSuperhero = objectMapper.readValue(response.getResponse().getContentAsString(), SuperheroResponse.class);
		Assertions.assertEquals(expectedSuperhero,updatedSuperhero);
	}

	@Test
	@DisplayName("Delete a superhero should take it out of the database")
	public void deleteSuperHero(@Autowired SuperheroService superheroService) throws Exception {
		mockMvc.perform(delete(String.format("/superheroes/ %d", 1L)))
				.andExpect(status().isNoContent());

		Assertions.assertThrows(ItemNotFoundException.class, () -> superheroService.getById(1L));
	}

	@Test
	@DisplayName("Search a superhero by differentIds or an approximate name should retrieve all possibilities")
	public void searchForSuperHero(@Autowired SuperheroService superheroService) throws Exception {
		var request = SuperheroSearchParametersRequest.builder().superheroName("Bat").build();

		var response = mockMvc.perform(get("/superheroes/search")
						.content(objectMapper.writeValueAsBytes(request))
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		var expectedSuperheroes = List.of(SuperheroResponse.builder().id(1L).name("Bruce Wayne").superheroName("Batman").build());

		var responseData = response.getResponse().getContentAsString();
		var wantedSuperheroes = List.of(objectMapper.readValue(responseData.substring(1, responseData.length() - 1), SuperheroResponse.class));
		Assertions.assertEquals(expectedSuperheroes, wantedSuperheroes);
	}

}
