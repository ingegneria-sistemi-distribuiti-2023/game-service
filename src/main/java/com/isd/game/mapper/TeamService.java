package com.isd.game.mapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.isd.game.converter.MatchConverter;
import com.isd.game.converter.MatchHistoryConverter;
import com.isd.game.converter.TeamConverter;
import com.isd.game.domain.Match;
import com.isd.game.domain.MatchHistory;
import com.isd.game.dto.MatchDTO;
import com.isd.game.dto.MatchHistoryDTO;
import com.isd.game.dto.TeamHistoryDTO;
import com.isd.game.repository.MatchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isd.game.domain.Team;
import com.isd.game.dto.TeamDTO;
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
public class TeamService {
    private final TeamRepository teamRepository;
    private final MatchHistoryRepository matchHistoryRepository;

    public TeamService(TeamRepository teamRepository, MatchHistoryRepository matchHistoryRepository) {
        this.teamRepository = teamRepository;
        this.matchHistoryRepository = matchHistoryRepository;
    }

    // get all the data from the database
    public List<TeamDTO> getAllData() {
        List<TeamDTO> list = new LinkedList<>();
        List<Team> listEntity = teamRepository.findAll();

        for (Team t: listEntity){
            list.add(new TeamConverter().toDto(t));
        }

        return list;
    }


    // create a new record in the database
    public TeamDTO createNewRecord(String newTeam) {
        Team team = new Team();
        team.setName(newTeam);
        teamRepository.save(team);
        return new TeamConverter().toDto(team);
    }

    // find a record in the database
    public TeamHistoryDTO findHistoryOfTeam(Integer id) {
        //check if the team exists

        TeamHistoryDTO toRet = new TeamHistoryDTO();

        Team team = teamRepository.findOneById(id);

        if (team == null) {
            throw new RuntimeException("Team with id " + id + " does not exist");
        }

        Integer teamId = team.getId();

        toRet.setId(teamId);
        toRet.setName(team.getName());

        List<MatchHistory> list = matchHistoryRepository.findAllByHomeTeamIdOrAwayTeamId(teamId, teamId);
        List<MatchHistoryDTO> listDto = new ArrayList<>();

        MatchHistoryConverter cnv = new MatchHistoryConverter();

        for (MatchHistory m : list){
            listDto.add(cnv.convertToDto(m));
        }

        toRet.setPlayedGames(listDto);

        return toRet;

    }
}