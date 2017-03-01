package com.arnolds.army.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "game")
public class Game extends BaseEntity {

	@Id
	@Column(name = "id")
	private Integer id;

	@Column(name = "local_date_time")
	private LocalDateTime localDateTime;

	@ManyToOne(optional = false)
	@JoinColumn(name = "home_team_id")
	private Team homeTeam;

	@ManyToOne(optional = false)
	@JoinColumn(name = "away_team_id")
	private Team awayTeam;

	@Column(name = "home_score")
	private Integer homeScore;

	@Column(name = "away_score")
	private Integer awayScore;

	@ManyToOne(optional = false)
	@JoinColumn(name = "season_id")
	private Season season;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Integer getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(Integer homeScore) {
		this.homeScore = homeScore;
	}

	public Integer getAwayScore() {
		return awayScore;
	}

	public void setAwayScore(Integer awayScore) {
		this.awayScore = awayScore;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}
}
