package com.arnolds.army.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "statistical_year")
public class StatisticalYear extends BaseEntity {

	@Id()
	@Column(name = "id")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "player_id", insertable = false, updatable = false, nullable = false)
	private Player player;

	@ManyToOne(optional = false)
	@JoinColumn(name = "season_id", insertable = false, updatable = false, nullable = false)
	private Season season;

	@Column(name = "at_bats")
	private Integer atBats;

	@Column(name = "runs")
	private Integer runs;

	@Column(name = "hits")
	private Integer hits;

	@Column(name = "doubles")
	private Integer doubles;

	@Column(name = "triples")
	private Integer triples;

	@Column(name = "home_runs")
	private Integer homeRuns;

	@Column(name = "rbi")
	private Integer rbi;

	@Column(name = "bb")
	private Integer walks;

	@Column(name = "k")
	private Integer strikeOuts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public Integer getYear() {
		return getSeason().getYear();
	}

	public Integer getAtBats() {
		return atBats;
	}

	public void setAtBats(Integer atBats) {
		this.atBats = atBats;
	}

	public Integer getRuns() {
		return runs;
	}

	public void setRuns(Integer runs) {
		this.runs = runs;
	}

	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	public Integer getDoubles() {
		return doubles;
	}

	public void setDoubles(Integer doubles) {
		this.doubles = doubles;
	}

	public Integer getTriples() {
		return triples;
	}

	public void setTriples(Integer triples) {
		this.triples = triples;
	}

	public Integer getHomeRuns() {
		return homeRuns;
	}

	public void setHomeRuns(Integer homeRuns) {
		this.homeRuns = homeRuns;
	}

	public Integer getRbi() {
		return rbi;
	}

	public void setRbi(Integer rbi) {
		this.rbi = rbi;
	}

	public Integer getWalks() {
		return walks;
	}

	public void setWalks(Integer walks) {
		this.walks = walks;
	}

	public Integer getStrikeOuts() {
		return strikeOuts;
	}

	public void setStrikeOuts(Integer strikeOuts) {
		this.strikeOuts = strikeOuts;
	}

	public BigDecimal getBattingAverage() {
		return BigDecimal.valueOf(getHits()).divide(BigDecimal.valueOf(getAtBats()), 3, BigDecimal.ROUND_DOWN);
	}

	public String getOnBasePercentage() {

		Integer timesOnBase = getWalks() + getHits();

		return BigDecimal.valueOf(timesOnBase).divide(BigDecimal.valueOf(getAtBats()), 3, BigDecimal.ROUND_DOWN)
				.toPlainString();
	}

	public String getSlugging() {

		Integer singlePoints = getHits() - getDoubles() - getTriples() - getHomeRuns();
		Integer doublePoints = getDoubles() * 2;
		Integer triplePoints = getTriples() * 3;
		Integer homeRunPoints = getHomeRuns() * 4;

		Integer totalPoints = singlePoints + doublePoints + triplePoints + homeRunPoints;

		totalPoints = 50;

		return BigDecimal.valueOf(totalPoints).divide(BigDecimal.valueOf(getAtBats()), 3, BigDecimal.ROUND_DOWN)
				.toPlainString();
	}

	public BigDecimal getOnBasePlusSlugging() {
		return BigDecimal.valueOf(Double.parseDouble(getOnBasePercentage()))
				.add(BigDecimal.valueOf(Double.parseDouble(getSlugging())));
	}

}
