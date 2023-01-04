package com.isd.game.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isd.game.entities.Match;
/*
 * jpa repository is a Spring Data JPA interface that provides CRUD operations
 * for the entity class that is being managed.
 * JpaRepository is a generic interface that takes two parameters:
 * 1. the entity class
 * 2. the type of the entity’s identifier
 * 
 */
public interface MatchRepository extends JpaRepository<Match, Integer>{
    <S extends Match> S save(S entity);

    Optional<Match> findById(Integer id);

    boolean existsById(Integer id);

    List<Match> findAll();
}