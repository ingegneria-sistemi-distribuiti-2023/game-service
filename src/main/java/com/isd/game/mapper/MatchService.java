package com.isd.game.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isd.game.domain.Match;
import com.isd.game.dto.MatchDTO;
import com.isd.game.repository.MatchRepository;
import com.isd.game.repository.TeamRepository;


/**
 * <p>a Mapper is a class that maps one object to another (e.g. a DTO to an Entity)
 * <b>Service</b> annotation is a specialization of Component annotation. 
 * Spring Service annotation can be applied only to classes. 
 * It is used to mark the class as a service provider.
 * This annotation is used with classes that provide some business functionalities.
 * Spring context will autodetect these classes when annotation-based configuration and
 * classpath scanning is used.</p>
 * <br>
 * <p>At a high level, Spring creates proxies for all the classes annotated with
 * Transactional – either on the class or on any of the methods. The proxy
 * allows the framework to inject transactional logic before and after the
 * running method – mainly for starting and committing the transaction</p>
 */
@Service
@Transactional
public class MatchService {
    private final MatchRepository matchRepository;
    private final TeamRepository tr;

    public MatchService(MatchRepository matchRepository, TeamRepository tr) {
        this.matchRepository = matchRepository;
        this.tr = tr;
    }

    // get all the data from the database
    public List<MatchDTO> getAllData() {
        return matchRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // convert the data from the database to a DTO
    public MatchDTO convertToDto(Match match) {
        MatchDTO matchDto = new MatchDTO();
        matchDto.setId(match.getId());
        matchDto.setHomeTeamId(match.getHomeTeam().getId());
        matchDto.setAwayTeamId(match.getAwayTeam().getId());
        matchDto.setHomeTeamName(match.getHomeTeam().getName());
        matchDto.setAwayTeamName(match.getAwayTeam().getName());
        matchDto.setHomeTeamScore(match.getHomeTeamScore());
        matchDto.setAwayTeamScore(match.getAwayTeamScore());
        matchDto.setHomeWinPayout(match.getHomeWinPayout());
        matchDto.setAwayWinPayout(match.getAwayWinPayout());
        matchDto.setDrawPayout(match.getDrawPayout());
        matchDto.setInGameMinute(match.getInGameMinute());
        matchDto.setStartTime(match.getStartTime());
        matchDto.setEndTime(match.getEndTime());
        matchDto.setStatus(match.getStatus());
        return matchDto;
    }

    // create a new record in the database
    public MatchDTO createNewMatch(MatchDTO matchDto) {
        Match match = new Match();

        match.setHomeTeam(tr.findOneById(matchDto.getHomeTeamId()));
        match.setAwayTeam(tr.findOneById(matchDto.getAwayTeamId()));

        match.setHomeTeamScore(matchDto.getHomeTeamScore());
        match.setAwayTeamScore(matchDto.getAwayTeamScore());
        match.setHomeWinPayout(matchDto.getHomeWinPayout());
        match.setAwayWinPayout(matchDto.getAwayWinPayout());
        match.setDrawPayout(matchDto.getDrawPayout());
        match.setInGameMinute(matchDto.getInGameMinute());
        match.setStartTime(matchDto.getStartTime());
        match.setEndTime(matchDto.getEndTime());
        match.setStatus(matchDto.getStatus());
        matchRepository.save(match);
        return convertToDto(match);
    }

    // update a record in the database
    public MatchDTO updateMatch(MatchDTO matchDto) {
        Match match = matchRepository.findOneById(matchDto.getId());
        match.setHomeTeam(tr.findOneById(match.getHomeTeam().getId()));
        match.setAwayTeam(tr.findOneById(match.getAwayTeam().getId()));
        match.setHomeTeamScore(matchDto.getHomeTeamScore());
        match.setAwayTeamScore(matchDto.getAwayTeamScore());
        match.setHomeWinPayout(matchDto.getHomeWinPayout());
        match.setAwayWinPayout(matchDto.getAwayWinPayout());
        match.setDrawPayout(matchDto.getDrawPayout());
        match.setInGameMinute(matchDto.getInGameMinute());
        match.setStartTime(matchDto.getStartTime());
        match.setEndTime(matchDto.getEndTime());
        match.setStatus(matchDto.getStatus());
        matchRepository.save(match);
        return convertToDto(match);
    }

    // delete a record from the database
    public void deleteMatch(Integer id) {
        //check if the match exists
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match with id " + id + " does not exist");
        }
        matchRepository.deleteById(id);
    }

    // find a record in the database
    public MatchDTO findMatch(Integer id) {
        //check if the match exists
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match with id " + id + " does not exist");
        }
        return convertToDto(matchRepository.findOneById(id));
    }
}