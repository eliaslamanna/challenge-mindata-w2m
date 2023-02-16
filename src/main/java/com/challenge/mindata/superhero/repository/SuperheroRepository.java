package com.challenge.mindata.superhero.repository;

import com.challenge.mindata.superhero.repository.entity.Superhero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperheroRepository extends JpaRepository<Superhero, String> {
}
