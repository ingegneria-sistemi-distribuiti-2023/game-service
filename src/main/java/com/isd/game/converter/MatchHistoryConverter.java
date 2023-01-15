package com.isd.game.converter;

import com.isd.game.domain.MatchHistory;
import com.isd.game.dto.MatchDTO;
import com.isd.game.dto.MatchHistoryDTO;
import com.isd.game.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchHistoryConverter {

    @Autowired
    private TeamRepository teamRepository;

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

//    public MatchHistoryDTO createNewMatch(MatchDTO matchDto) {
    public MatchHistoryDTO fromMatchDtoToMatchHistory(MatchDTO matchDto) {
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
        return matchHistoryDto;
    }


    public MatchHistory toEntity(MatchHistoryDTO dto) {
        MatchHistory match = new MatchHistory();
        match.setId(dto.getId());
        match.setHomeTeamId(dto.getHomeTeamId());
        match.setAwayTeamId(dto.getAwayTeamId());
        match.setHomeTeamScore(dto.getHomeTeamScore());
        match.setAwayTeamScore(dto.getAwayTeamScore());
        match.setStartTime(dto.getStartTime());
        match.setEndTime(dto.getEndTime());
        match.setStatus(dto.getStatus());
        return match;
    }

}
