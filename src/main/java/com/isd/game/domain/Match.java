package com.isd.game.domain;

import java.util.Date;

import com.isd.game.commons.MatchStatus;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "match_info")
public class Match {
    @Id
    @Column(name = "id", nullable = false, unique = true, updatable = false, insertable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increment in MySQL
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "home_team", nullable = false)
    private Team homeTeam;

    @ManyToOne
    @JoinColumn(name = "away_team", nullable = false)
    private Team awayTeam;


    @Column(name = "home_score")
    private Integer homeTeamScore;

    @Column(name = "away_score")
    private Integer awayTeamScore;

    @Column(name = "home_win_payout")
    private Double homeWinPayout;

    @Column(name = "away_win_payout")
    private Double awayWinPayout;

    @Column(name = "draw_payout")
    private Double drawPayout;

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

}
