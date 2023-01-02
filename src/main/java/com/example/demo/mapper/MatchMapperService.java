package com.example.demo.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.MatchDto;
import com.example.demo.entities.Match;
import com.example.demo.repository.MatchRepository;


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
public class MatchMapperService {
    // @Autowired annotation is used to inject the object dependency implicitly.
    @Autowired
    private MatchRepository matchRepository;

    // get all the data from the database
    public List<MatchDto> getAllData() {
        return matchRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // convert the data from the database to a DTO
    public MatchDto convertToDto(Match match) {
        System.out.println(match.toString());
        MatchDto matchDto = new MatchDto();
        matchDto.setId(match.getId());
        matchDto.setHomeTeamId(match.getHomeTeamId());
        matchDto.setAwayTeamId(match.getAwayTeamId());
        matchDto.setHomeTeamScore(match.getHomeTeamScore());
        matchDto.setAwayTeamScore(match.getAwayTeamScore());
        matchDto.setStartTime(match.getStartTime());
        matchDto.setEndTime(match.getEndTime());
        return matchDto;
    }

    // create a new record in the database
    public MatchDto createNewMatch(MatchDto matchDto) {
        Match match = new Match();
        match.setHomeTeamId(matchDto.getHomeTeamId());
        match.setAwayTeamId(matchDto.getAwayTeamId());
        match.setHomeTeamScore(matchDto.getHomeTeamScore());
        match.setAwayTeamScore(matchDto.getAwayTeamScore());
        match.setStartTime(matchDto.getStartTime());
        match.setEndTime(matchDto.getEndTime());
        matchRepository.save(match);
        return convertToDto(match);
    }

    // update a record in the database
    public MatchDto updateMatch(MatchDto matchDto) {
        Match match = matchRepository.findById(matchDto.getId()).get();
        match.setHomeTeamId(matchDto.getHomeTeamId());
        match.setAwayTeamId(matchDto.getAwayTeamId());
        match.setHomeTeamScore(matchDto.getHomeTeamScore());
        match.setAwayTeamScore(matchDto.getAwayTeamScore());
        match.setStartTime(matchDto.getStartTime());
        match.setEndTime(matchDto.getEndTime());
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
    public MatchDto findMatch(Integer id) {
        //check if the match exists
        if (!matchRepository.existsById(id)) {
            throw new RuntimeException("Match with id " + id + " does not exist");
        }
        return convertToDto(matchRepository.findById(id).get());
    }
}