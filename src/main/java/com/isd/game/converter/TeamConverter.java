package com.isd.game.converter;

import com.isd.game.domain.Team;
import com.isd.game.dto.TeamDTO;

public class TeamConverter {

    // convert the data from the database to a DTO
    public TeamDTO toDto(Team team) {
        TeamDTO teamDto = new TeamDTO();
        teamDto.setId(team.getId());
        teamDto.setName(team.getName());
        return teamDto;
    }

}
