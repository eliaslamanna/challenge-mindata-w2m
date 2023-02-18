package com.challenge.mindata.superhero.repository;

import com.challenge.mindata.superhero.repository.entity.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, String> {

    List<Superhero> findBySuperheroNameContains(String superheroName);

    List<Superhero> findByIdInAndSuperheroNameContains(List<String> ids, String superheroName);

    List<Superhero> findAllByIdIn(List<String> ids);

}
