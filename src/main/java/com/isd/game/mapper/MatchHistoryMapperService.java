package com.isd.game.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isd.game.domain.MatchHistory;
import com.isd.game.dto.MatchDTO;
import com.isd.game.dto.MatchHistoryDTO;
import com.isd.game.repository.MatchHistoryRepository;
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
public class MatchHistoryMapperService {
    // @Autowired annotation is used to inject the object dependency implicitly.
    @Autowired
    private MatchHistoryRepository matchHistoryRepository;

    @Autowired
    private TeamRepository teamRepository;

    // get all the data from the database
    public List<MatchHistoryDTO> getAllData() {
        return matchHistoryRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // convert the data from the database to a DTO
    public MatchHistoryDTO convertToDto(MatchHistory match) {
        MatchHistoryDTO matchDto = new MatchHistoryDTO();
        matchDto.setId(match.getId());
        matchDto.setHomeTeamId(match.getHomeTeamId());
        matchDto.setAwayTeamId(match.getAwayTeamId());
        matchDto.setHomeTeamName(teamRepository.findById(match.getHomeTeamId()).get().getName());
        matchDto.setAwayTeamName(teamRepository.findById(match.getAwayTeamId()).get().getName());
        matchDto.setHomeTeamScore(match.getHomeTeamScore());
        matchDto.setAwayTeamScore(match.getAwayTeamScore());
        matchDto.setStartTime(match.getStartTime());
        matchDto.setEndTime(match.getEndTime());
        matchDto.setStatus(match.getStatus());
        return matchDto;
    }

    public MatchHistoryDTO createNewMatch(MatchDTO matchDto) {
        // create a MatchHistoryDto object from the MatchDto one
        MatchHistoryDTO matchHistoryDto = new MatchHistoryDTO();
        matchHistoryDto.setHomeTeamId(matchDto.getHomeTeamId());
        matchHistoryDto.setAwayTeamId(matchDto.getAwayTeamId());
        matchHistoryDto.setHomeTeamScore(matchDto.getHomeTeamScore());
        matchHistoryDto.setAwayTeamScore(matchDto.getAwayTeamScore());
        matchHistoryDto.setStartTime(matchDto.getStartTime());
        matchHistoryDto.setEndTime(matchDto.getEndTime());
        matchHistoryDto.setStatus(matchDto.getStatus());
        // create a new record in the database
        return createNewMatch(matchHistoryDto);
    }


    // create a new record in the database
    public MatchHistoryDTO createNewMatch(MatchHistoryDTO matchDto) {
        MatchHistory match = new MatchHistory();
        match.setHomeTeamId(matchDto.getHomeTeamId());
        match.setAwayTeamId(matchDto.getAwayTeamId());
        match.setHomeTeamScore(matchDto.getHomeTeamScore());
        match.setAwayTeamScore(matchDto.getAwayTeamScore());
        match.setStartTime(matchDto.getStartTime());
        match.setEndTime(matchDto.getEndTime());
        match.setStatus(matchDto.getStatus());
        matchHistoryRepository.save(match);
        return convertToDto(match);
    }

    // update a record in the database
    public MatchHistoryDTO updateMatch(MatchHistoryDTO matchDto) {
        MatchHistory match = matchHistoryRepository.findById(matchDto.getId()).get();
        match.setHomeTeamId(matchDto.getHomeTeamId());
        match.setAwayTeamId(matchDto.getAwayTeamId());
        match.setHomeTeamScore(matchDto.getHomeTeamScore());
        match.setAwayTeamScore(matchDto.getAwayTeamScore());
        match.setStartTime(matchDto.getStartTime());
        match.setEndTime(matchDto.getEndTime());
        match.setStatus(matchDto.getStatus());
        matchHistoryRepository.save(match);
        return convertToDto(match);
    }

    // delete a record from the database
    public void deleteMatch(Integer id) {
        //check if the match exists
        if (!matchHistoryRepository.existsById(id)) {
            throw new RuntimeException("Match with id " + id + " does not exist");
        }
        matchHistoryRepository.deleteById(id);
    }

    // find a record in the database
    public MatchHistoryDTO findMatch(Integer id) {
        //check if the match exists
        if (!matchHistoryRepository.existsById(id)) {
            throw new RuntimeException("Match with id " + id + " does not exist");
        }
        return convertToDto(matchHistoryRepository.findById(id).get());
    }
}