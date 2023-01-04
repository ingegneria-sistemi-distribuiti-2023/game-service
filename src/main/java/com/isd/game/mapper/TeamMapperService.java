package com.isd.game.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isd.game.dto.TeamDto;
import com.isd.game.entities.Team;
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
public class TeamMapperService {
    // @Autowired annotation is used to inject the object dependency implicitly.
    @Autowired
    private TeamRepository teamRepository;

    // get all the data from the database
    public List<TeamDto> getAllData() {
        return teamRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    // convert the data from the database to a DTO
    public TeamDto convertToDto(Team team) {
        TeamDto teamDto = new TeamDto();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        return teamDto;
    }

    // create a new record in the database
    public TeamDto createNewRecord(TeamDto teamDto) {
        Team team = new Team();
        team.setName(teamDto.getName());
        teamRepository.save(team);
        return convertToDto(team);
    }

    // update a record in the database
    public TeamDto updateTeam(TeamDto teamDto) {
        Team team = teamRepository.findById(teamDto.getId()).get();
        team.setName(teamDto.getName());
        teamRepository.save(team);
        return convertToDto(team);
    }

    // delete a record from the database
    public void deleteTeam(Integer id) {
        //check if the team exists
        if (!teamRepository.existsById(id)) {
            throw new RuntimeException("Team with id " + id + " does not exist");
        }
        teamRepository.deleteById(id);
    }

    // find a record in the database
    public TeamDto findTeam(Integer id) {
        //check if the team exists
        if (!teamRepository.existsById(id)) {
            throw new RuntimeException("Team with id " + id + " does not exist");
        }
        return convertToDto(teamRepository.findById(id).get());
    }
}