package com.isd.game.repository;

import java.util.List;
import java.util.Optional;

import com.isd.game.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import com.isd.game.domain.MatchHistory;
/*
 * jpa repository is a Spring Data JPA interface that provides CRUD operations
 * for the entity class that is being managed.
 * JpaRepository is a generic interface that takes two parameters:
 * 1. the entity class
 * 2. the type of the entity’s identifier
 * 
 */
public interface MatchHistoryRepository extends JpaRepository<MatchHistory, Integer>{
    <S extends MatchHistory> S save(S entity);

    Optional<MatchHistory> findById(Integer id);

    boolean existsById(Integer id);

    List<MatchHistory> findAll();

    List<MatchHistory> findAllByHomeTeamIdOrAwayTeamId(Integer homeId, Integer awayId);

}