package com.example.demo.dto;

import java.util.Date;

import com.example.demo.commons.MatchStatus;

public class MatchHistoryDto {
    // this DTO contains the match ID, IDs of the teams, the home and away scores, the start time and the end time of the match
    private int id;
    private int homeTeamId;
    private int awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private int homeTeamScore;
    private int awayTeamScore;
    private Date startTime;
    private Date endTime;
    private MatchStatus status;

    public MatchHistoryDto() {
    }

    public MatchHistoryDto( int id, int homeTeamId, int awayTeamId, String homeTeamName, String awayTeamName,int homeTeamScore, int awayTeamScore, Date startTime, Date endTime, MatchStatus status) {
        this.id = id;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
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

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
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

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MatchHistoryDto [id=" + id + ", homeTeamId=" + homeTeamId + ", awayTeamId=" + awayTeamId + ", homeTeamName=" + homeTeamName + ", awayTeamName=" + awayTeamName + ", homeTeamScore=" + homeTeamScore + ", awayTeamScore=" + awayTeamScore + ", startTime=" + startTime + ", endTime=" + endTime + ", status=" + status + "]";
    }
}
