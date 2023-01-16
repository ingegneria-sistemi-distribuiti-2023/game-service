package com.isd.game.converter;

import com.isd.game.domain.Match;
import com.isd.game.dto.MatchDTO;
import com.isd.game.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchConverter {

    @Autowired
    TeamRepository teamRepository;

    // convert the data from the database to a DTO
    public MatchDTO toDto(Match match) {
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

    public Match toEntity(MatchDTO dto) {
        Match match = new Match();
        match.setId(dto.getId());
        match.setHomeTeam(teamRepository.findById(dto.getHomeTeamId()).orElse(null));
        match.setAwayTeam(teamRepository.findById(dto.getAwayTeamId()).orElse(null));
        match.setHomeTeamScore(dto.getHomeTeamScore());
        match.setAwayTeamScore(dto.getAwayTeamScore());
        match.setInGameMinute(dto.getInGameMinute());
        match.setHomeWinPayout(dto.getHomeWinPayout());
        match.setAwayWinPayout(dto.getAwayWinPayout());
        match.setDrawPayout(dto.getDrawPayout());
        match.setStartTime(dto.getStartTime());
        match.setEndTime(dto.getEndTime());
        match.setStatus(dto.getStatus());
        return match;
    }
}
