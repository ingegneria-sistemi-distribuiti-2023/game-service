package com.example.demo.dto;

import java.util.Date;

public class MatchDto {
    // this DTO contains the match ID, IDs of the teams, the home and away scores, the start time and the end time of the match
    private int id;
    private int homeTeamId;
    private int awayTeamId;
    private int homeTeamScore;
    private int awayTeamScore;
    private Date startTime;
    private Date endTime;

    public MatchDto() {
    }

    public MatchDto( int id, int homeTeamId, int awayTeamId, int homeTeamScore, int awayTeamScore, Date startTime, Date endTime) {
        this.id = id;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public int getAwayTeamId() {
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

    @Override
    public String toString() {
        return "MatchDto [awayTeamId=" + awayTeamId + ", awayTeamScore=" + awayTeamScore + ", endTime=" + endTime
                + ", homeTeamId=" + homeTeamId + ", homeTeamScore=" + homeTeamScore + ", startTime=" + startTime
                + "]";
    }
}
