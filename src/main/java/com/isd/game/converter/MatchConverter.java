package com.isd.game.converter;

import com.isd.game.domain.Match;
import com.isd.game.dto.MatchDTO;
import com.isd.game.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchConverter {

    @Autowired
    TeamRepository teamRepository;

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
