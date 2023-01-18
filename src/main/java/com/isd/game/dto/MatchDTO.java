package com.isd.game.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import com.isd.game.commons.MatchStatus;
import com.isd.game.commons.OutcomeEnum;
import com.isd.game.commons.error.CustomHttpResponse;
import com.isd.game.commons.error.CustomServiceException;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchDTO {
    // this DTO contains the match ID, IDs of the teams, the home and away scores, the start time and the end time of the match
    private Integer id;
    private Integer homeTeamId;
    private Integer awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private Integer homeTeamScore;
    private Integer awayTeamScore;
    private Double homeWinPayout;
    private Double awayWinPayout;
    private Double drawPayout;
    private Integer inGameMinute;
    private Date startTime;
    private Date endTime;
    private MatchStatus status;

    public Double getPayout(OutcomeEnum outcome) throws CustomServiceException{
        switch (outcome) {
            case DRAW:
                return getDrawPayout();
            case AWAY:
                return getAwayWinPayout();
            case HOME:
                return getHomeWinPayout();
            default:
                throw new CustomServiceException(new CustomHttpResponse(HttpStatus.BAD_REQUEST, "Invalid bet Id"));
        }
    }

}
