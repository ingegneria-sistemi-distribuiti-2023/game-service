package com.isd.game.scheduled;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.isd.game.converter.MatchHistoryConverter;
import com.isd.game.domain.MatchHistory;
import com.isd.game.dto.MatchHistoryDTO;
import com.isd.game.repository.MatchHistoryRepository;
import com.isd.game.repository.MatchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.isd.game.commons.MatchStatus;
import com.isd.game.commons.error.CustomServiceException;
import com.isd.game.dto.MatchDTO;
import com.isd.game.dto.TeamDTO;
import com.isd.game.mapper.MatchService;
import com.isd.game.mapper.TeamService;
import org.springframework.transaction.annotation.Transactional;


/*
 * Here we have specified the property name as scheduler.enabled. 
 * We want to enable it by default. 
 * For this, we have also set the value of matchIfMissing to true 
 * which means we do not have to set this property to enable scheduling 
 * but have to set this property to explicitly disable the scheduler
 */
@EnableScheduling
@Service
@ConditionalOnProperty(name = "scheduler.enabled", matchIfMissing = true)
@Transactional
public class MatchSchedulerService {
    private final MatchService matchService;
    private final TeamService teamService;
    private final MatchHistoryRepository mhr;
    private final MatchRepository mr;

    private static final Logger LOGGER = LoggerFactory.getLogger(MatchSchedulerService.class);

    public MatchSchedulerService(MatchService matchService, TeamService teamService, MatchHistoryRepository mhr, MatchRepository mr) {
        this.matchService = matchService;
        this.teamService = teamService;
        this.mhr = mhr;
        this.mr = mr;
    }

    /**
     * every 10 seconds, fetch two teams that are not in a match that ended and create a new match between them.
     */
    @Scheduled(fixedRate = 10000)
    public void createMatch() {
        // fetch all teams from database
        List<TeamDTO> teams = teamService.getAllData();
        // fetch all matches from database
        List<MatchDTO> matches = matchService.getAllData();
        // find two teams that are not in a match that ended
        TeamDTO homeTeam = null;
        TeamDTO awayTeam = null;
        // shuffle the teams and matches
        java.util.Collections.shuffle(teams);
        java.util.Collections.shuffle(matches);
        for (TeamDTO team : teams) {
            boolean teamInMatch = false;
            for (MatchDTO match : matches) {
                if ((match.getHomeTeamId() == team.getId() || match.getAwayTeamId() == team.getId()) && !match.getStatus().equals(MatchStatus.FINISHED)) {
                    teamInMatch = true;
                    break;
                }
            }
            if (!teamInMatch) {
                if (homeTeam == null) {
                    homeTeam = team;
                } else {
                    awayTeam = team;
                    break;
                }
            }
        }
        // if we found two teams, create a new match between them that will start in 2 to 5 minutes
        if (homeTeam != null && awayTeam != null) {
            MatchDTO match = new MatchDTO();
            match.setHomeTeamId(homeTeam.getId());
            match.setAwayTeamId(awayTeam.getId());
            match.setStartTime(new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(2 + (int) (Math.random() * 3)) ));
            match.setEndTime(null);
            match.setHomeTeamScore(0);
            match.setAwayTeamScore(0);
            // set initial payouts of the match  // TODO: define a better logic for this
            match.setHomeWinPayout(1 + (Math.random() * 2));
            match.setDrawPayout(1 + (Math.random() * 2));
            match.setAwayWinPayout(1 + (Math.random() * 2));
            match.setStatus(MatchStatus.TO_BE_PLAYED);
            match.setInGameMinute(0);
            matchService.createNewMatch(match);
        }
    }

    /**
     * fetch all metch from database every 5 seconds and update the score of each one of them 
     * by randomly adding a gol to one of the teams if the match started not more than 10 minutes ago
     */
    @Scheduled(fixedRate = 20000)
    public void updateMatchScore() throws CustomServiceException {
        // fetch all matches from database
        List<MatchDTO> matches = matchService.getAllData();
        // update the score of each match
        matches.forEach(match -> {
            // if the match started more than 10 minutes ago, do not update the score
            Date matchStartTime = match.getStartTime();
            Date currentTime = new Date();
            if ((Math.abs(currentTime.getTime() - matchStartTime.getTime()) >= TimeUnit.MINUTES.toMillis(10)) && !(match.getStatus().equals(MatchStatus.FINISHED))) {
                // update the end time of the match
                match.setEndTime(new Date(matchStartTime.getTime() + TimeUnit.MINUTES.toMillis(10)));
                // update the status of the match
                match.setStatus(MatchStatus.FINISHED);

                MatchHistoryConverter cnv = new MatchHistoryConverter();

                MatchHistoryDTO matchHistoryDto = cnv.fromMatchDtoToMatchHistory(match);

                MatchHistory matchHistoryEntity = cnv.toEntity(matchHistoryDto);

                mhr.save(matchHistoryEntity);

                // delete the match from the current matches
                try {
                    matchService.deleteMatch(match.getId());
                } catch (CustomServiceException e) {
                    e.printStackTrace();
                }

            } else if (((matchStartTime.compareTo(currentTime) <= 0)) && !(match.getStatus().equals(MatchStatus.FINISHED))) {
                // generate a random number between 0 and 1
                Integer random = (int) (Math.random() * 10);
                // if the random number is 0, add a goal to the home team
                switch (random) {
                    case 0:
                        match.setHomeTeamScore(match.getHomeTeamScore() + 1);
                        break;
                    case 1:
                        match.setAwayTeamScore(match.getAwayTeamScore() + 1);
                        break;
                    default:
                        // no goal added
                        break;
                }
                match.setStatus(MatchStatus.PLAYING);

                // update homeWinPayout, drawPayout // TODO: define a better logic for this
                match.setHomeWinPayout(1 + (Math.random() * 2));
                match.setDrawPayout(1 + (Math.random() * 2));
                match.setAwayWinPayout(1 + (Math.random() * 2));

                // update inGameMinute, map the real time elapsed to the start of the match in a range of 0 to 90 minutes in game where 0 is the start of the match and 90 is the start time plus 10 minutes
                long matchStartTimeStamp = match.getStartTime().getTime();
                long matchEndTimeStamp = new Date().getTime();
                long matchDuration = Math.abs(matchStartTimeStamp - matchEndTimeStamp);
                long matchDurationInSeconds = TimeUnit.MILLISECONDS.toSeconds(matchDuration);
                match.setInGameMinute((int) (matchDurationInSeconds * (90 ) / (10 * 60)));
                LOGGER.info("Updated: " + match);
                // save the match to the database
                matchService.updateMatch(match);
            }
        });
    }
    
    /** 
     *  Check every 10 minutes: delete a match from the database if it ended more than 50 minutes ago
     */
    @Scheduled(fixedRate = 600000)
    public void deleteMatch() throws CustomServiceException {
        // fetch all matches from database
        List<MatchDTO> matches = matchService.getAllData();
        matches.forEach(match -> {
            long matchStartTimeStamp = match.getStartTime().getTime();
            long matchEndTimeStamp = new Date().getTime();
            if ((Math.abs(matchStartTimeStamp - matchEndTimeStamp) > TimeUnit.MINUTES.toMillis(50))) {
                try {
                    matchService.deleteMatch(match.getId());
                } catch (CustomServiceException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
