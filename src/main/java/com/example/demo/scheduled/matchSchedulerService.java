package com.example.demo.scheduled;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.commons.MatchStatus;
import com.example.demo.dto.MatchDto;
import com.example.demo.dto.TeamDto;
import com.example.demo.mapper.MatchMapperService;
import com.example.demo.mapper.TeamMapperService;


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
public class matchSchedulerService {
    @Autowired
    private MatchMapperService matchMapperService;

    @Autowired
    private TeamMapperService teamMapperService;

    /**
     * every 10 seconds, fetch two teams that are not in a match that ended and create a new match between them.
     */
    @Scheduled(fixedRate = 10000)
    public void createMatch() {
        // fetch all teams from database
        List<TeamDto> teams = teamMapperService.getAllData();
        // fetch all matches from database
        List<MatchDto> matches = matchMapperService.getAllData();
        // find two teams that are not in a match that ended
        TeamDto team1 = null;
        TeamDto team2 = null;
        // shuffle the teams and matches
        java.util.Collections.shuffle(teams);
        java.util.Collections.shuffle(matches);
        for (TeamDto team : teams) {
            boolean found = false;
            for (MatchDto match : matches) {
                if (match.getHomeTeamId() == team.getId() || match.getAwayTeamId() == team.getId()) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (team1 == null) {
                    team1 = team;
                } else {
                    team2 = team;
                    break;
                }
            }
        }
        // if we found two teams, create a new match between them that will start in 2 minutes
        if (team1 != null && team2 != null) {
            MatchDto match = new MatchDto();
            match.setHomeTeamId(team1.getId());
            match.setAwayTeamId(team2.getId());
            match.setStartTime(new Date(new Date().getTime() + TimeUnit.MINUTES.toMillis(2)));
            match.setEndTime(null);
            match.setHomeTeamScore(0);
            match.setAwayTeamScore(0);
            match.setStatus(MatchStatus.TO_BE_PLAYED);
            matchMapperService.createNewMatch(match);
        }
    }

    /**
     * fetch all metch from database every 5 seconds and update the score of each one of them 
     * by randomly adding a gol to one of the teams if the match started not more than 10 minutes ago
     */
    @Scheduled(fixedRate = 20000)
    public void updateMatchScore() {
        // fetch all matches from database
        List<MatchDto> matches = matchMapperService.getAllData();
        // update the score of each match
        matches.forEach(match -> {
            // if the match started more than 10 minutes ago, do not update the score
            long matchStartTimeStamp = match.getStartTime().getTime();
            long currentTimestamp = new Date().getTime();
            if ((Math.abs(currentTimestamp - matchStartTimeStamp) >= TimeUnit.MINUTES.toMillis(10)) && !(match.getStatus().equals(MatchStatus.FINISHED))) {
                // update the end time of the match
                match.setEndTime(new Date(matchStartTimeStamp + TimeUnit.MINUTES.toMillis(10)));
                // update the status of the match
                match.setStatus(MatchStatus.FINISHED);
            } else if (((Math.abs(matchStartTimeStamp - currentTimestamp) >= TimeUnit.SECONDS.toMillis(0)))) { // TODO: check this condition
                // generate a random number between 0 and 1
                int random = (int) (Math.random() * 3);
                // if the random number is 0, add a goal to the home team
                switch (random) {
                    case 0:
                        match.setHomeTeamScore(match.getHomeTeamScore() + 1);
                        break;
                    case 1:
                        match.setAwayTeamScore(match.getAwayTeamScore() + 1);
                        break;
                    case 2:
                        // no goal added
                        break;
                    default:
                        break;
                }
               match.setStatus(MatchStatus.PLAYING);
            }
            // TODO: delete this
            System.out.println("match updated");
            System.out.println(match);
            // save the match to the database
            matchMapperService.updateMatch(match);
        });
    }
    
    /** 
     *  Check every 10 minutes: delete a match from the database if it ended more than 50 minutes ago
     */
    @Scheduled(fixedRate = 600000)
    public void deleteMatch() {
        // fetch all matches from database
        List<MatchDto> matches = matchMapperService.getAllData();
        matches.forEach(match -> {
            long matchStartTimeStamp = match.getStartTime().getTime();
            long matchEndTimeStamp = new Date().getTime();
            if ((Math.abs(matchStartTimeStamp - matchEndTimeStamp) > TimeUnit.MINUTES.toMillis(50))) {
                matchMapperService.deleteMatch(match.getId());
            }
        });
    }
}
