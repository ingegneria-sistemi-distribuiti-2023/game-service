package com.isd.game.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.isd.game.domain.Team;
/*
 * jpa repository is a Spring Data JPA interface that provides CRUD operations
 * for the entity class that is being managed.
 * JpaRepository is a generic interface that takes two parameters:
 * 1. the entity class
 * 2. the type of the entity’s identifier
 * 
 */
public interface TeamRepository extends JpaRepository<Team, Integer>{
    // TODO: perché non ritorna l'entity ???
    <S extends Team> S save(S entity);

    Team findOneById(Integer id);

    // TODO: perché non ritorna l'entity ???
    Optional<Team> findById(Integer id);

    boolean existsById(Integer id);

    List<Team> findAll();
}