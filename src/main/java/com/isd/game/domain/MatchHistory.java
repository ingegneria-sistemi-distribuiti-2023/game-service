package com.isd.game.domain;

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
@Table(name = "match_history")
public class MatchHistory {
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

    public MatchHistory() {
    }

    public MatchHistory(Integer homeTeamId, Integer awayTeamId, Integer homeTeamScore, Integer awayTeamScore, Date startTime, Date endTime, MatchStatus status) {
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
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

    public void setAwayTeamId(Integer awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public Integer getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(Integer homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public Integer getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(Integer awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
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
        return "Match [awayTeamId=" + awayTeamId + ", awayTeamScore=" + awayTeamScore + ", endTime=" + endTime
                + ", homeTeamId=" + homeTeamId + ", homeTeamScore=" + homeTeamScore + ", id=" + id + ", startTime="
                + startTime + ", status=" + status +"]";
    }
}
