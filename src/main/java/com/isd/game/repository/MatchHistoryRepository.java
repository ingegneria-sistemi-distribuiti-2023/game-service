package com.isd.game.repository;

import java.util.List;
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
    MatchHistory save(MatchHistory entity);

    MatchHistory findOneById(Integer id);

    boolean existsById(Integer id);

    List<MatchHistory> findAll();

    List<MatchHistory> findAllByHomeTeamIdOrAwayTeamId(Integer homeId, Integer awayId);
}