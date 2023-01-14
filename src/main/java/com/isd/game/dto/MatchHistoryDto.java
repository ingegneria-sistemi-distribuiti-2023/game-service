package com.isd.game.dto;

import java.util.Date;

import com.isd.game.commons.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchHistoryDto {
    // this DTO contains the match ID, IDs of the teams, the home and away scores, the start time and the end time of the match
    private Integer id;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Date startTime;
    private Date endTime;
    private MatchStatus status;

}
