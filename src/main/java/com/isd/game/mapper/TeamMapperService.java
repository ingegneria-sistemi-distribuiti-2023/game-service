package com.isd.game.mapper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import com.isd.game.domain.Match;
import com.isd.game.domain.MatchHistory;
import com.isd.game.dto.MatchDto;
import com.isd.game.dto.MatchHistoryDto;
import com.isd.game.dto.TeamHistoryDTO;
import com.isd.game.repository.MatchHistoryRepository;
import com.isd.game.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.isd.game.domain.Team;
import com.isd.game.dto.TeamDto;
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

    @Autowired
    private MatchHistoryRepository matchHistoryRepository;

    @Autowired
    private MatchHistoryMapperService mhps;

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
    public TeamDto createNewRecord(String newTeam) {
        Team team = new Team();
        team.setName(newTeam);
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

    // find a record in the database
    public TeamHistoryDTO findHistoryOfTeam(Integer id) {
        //check if the team exists

        TeamHistoryDTO toRet = new TeamHistoryDTO();

        Team team = teamRepository.findOneById(id);

//      NOTA PER MARCO: conviene istanziare un oggetto e non fare in questo modo perché mi avevano spiegato che dovrebbe cambiare chiamate a livello di DB
//        .. in questo esempio tu prima chiami l'exist e fai la query e successivamente ne fai un'altra query per trovare il team
//        if (!teamRepository.existsById(id)) {
        if (team == null) {
            throw new RuntimeException("Team with id " + id + " does not exist");
        }

        // TODO: In questo caso credo pure dovrebbe essere più performante usare teamId invece che chiamare team.getId() su toRet.setId() e mr.findAllBy..(), verificare
        Integer teamId = team.getId();

        toRet.setId(teamId);
        toRet.setName(team.getName());

        List<MatchHistory> list = matchHistoryRepository.findAllByHomeTeamIdOrAwayTeamId(teamId, teamId);
        List<MatchHistoryDto> listDto = new ArrayList<>();

        for (MatchHistory m : list){
            listDto.add(mhps.convertToDto(m));
        }

        toRet.setPlayedGames(listDto);

//        return convertToDto(teamRepository.findById(id).get());
        return toRet;

    }
}