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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
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

}
