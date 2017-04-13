package com.arnolds.army.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.arnolds.army.listener.GameEntityListener;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "game")
@EntityListeners(GameEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Game extends BaseEntity {

	@Id
	@SequenceGenerator(name = "game_id_seq", sequenceName = "game_id_seq", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_seq")
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

	@Transient
	private Integer month;

	@Transient
	private Integer day;

	@Transient
	private Integer year;

	@Transient
	private Integer hour;

	@Transient
	private String minuteInterval;

	@Transient
	private String period;

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

	/**
	 * @return the month
	 */
	public Integer getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}

	/**
	 * @return the day
	 */
	public Integer getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(Integer day) {
		this.day = day;
	}

	/**
	 * @return the year
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * @return the hour
	 */
	public Integer getHour() {
		return hour;
	}

	/**
	 * @param hour
	 *            the hour to set
	 */
	public void setHour(Integer hour) {
		this.hour = hour;
	}

	/**
	 * @return the minuteInterval
	 */
	public String getMinuteInterval() {
		return minuteInterval;
	}

	/**
	 * @param minuteInterval
	 *            the minuteInterval to set
	 */
	public void setMinuteInterval(String minuteInterval) {
		this.minuteInterval = minuteInterval;
	}

	/**
	 * @return the period
	 */
	public String getPeriod() {
		return period;
	}

	/**
	 * @param period
	 *            the period to set
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
}
