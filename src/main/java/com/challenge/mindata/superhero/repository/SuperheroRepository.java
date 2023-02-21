package com.challenge.mindata.superhero.repository;

import com.challenge.mindata.superhero.repository.entity.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, Long> {

    List<Superhero> findBySuperheroNameContains(String superheroName);

    List<Superhero> findByIdInAndSuperheroNameContains(List<String> ids, String superheroName);

    List<Superhero> findAllByIdIn(List<String> ids);

}
