package com.isd.game.entities;

import java.util.Date;

import com.isd.game.commons.MatchStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/*
 * this class represents an Entity (or domain object) in the application
 * it is a Java representation of a database table
 */
@Entity
@Table(name = "match_info")
public class Match {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment in MySQL
    private Integer id;

    @Column(name = "home_team")
    private Integer homeTeamId;

    @Column(name = "away_team")
    private Integer awayTeamId;

    @Column(name = "home_score")
    private Integer homeTeamScore;

    @Column(name = "away_score")
    private Integer awayTeamScore;

    @Column(name = "in_game_minute")
    private Integer inGameMinute;

    // start time of the match
    @Column(name = "start_time")
    private Date startTime;

    // end time of the match
    @Column(name = "end_time")
    private Date endTime;

    // status of the match
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MatchStatus status;

    public Match() {
    }

    public Match(Integer homeTeamId, Integer awayTeamId, Integer homeTeamScore, Integer awayTeamScore, Integer inGameMinute, Date startTime, Date endTime, MatchStatus status) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.inGameMinute = inGameMinute;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Integer homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Integer getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public Integer getInGameMinute() {
        return inGameMinute;
    }

    public void setInGameMinute(Integer inGameMinute) {
        this.inGameMinute = inGameMinute;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Match [awayTeamId=" + awayTeamId + ", awayTeamScore=" + awayTeamScore + ", endTime=" + endTime +
         ", homeTeamId=" + homeTeamId + ", homeTeamScore=" + homeTeamScore + ", id=" + id + ", inGameMinute=" +
          inGameMinute + ", startTime=" + startTime + ", status=" + status + "]";
    }
}
