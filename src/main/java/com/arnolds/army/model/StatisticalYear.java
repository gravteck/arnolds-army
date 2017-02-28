package com.arnolds.army.model;

import java.math.BigDecimal;

public class StatisticalYear {

	private Integer id;

	private Player player;

	private Season season;

	private Integer year;

	private Integer atBats;

	private Integer runs;

	private Integer hits;

	private Integer singles;

	private Integer doubles;

	private Integer triples;

	private Integer homeRuns;

	private Integer rbi;

	private Integer walks;

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

	public Integer getSingles() {
		return singles;
	}

	public void setSingles(Integer singles) {
		this.singles = singles;
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

	public BigDecimal getOnBasePercentage() {

		Integer timesOnBase = getWalks() + getHits();

		return BigDecimal.valueOf(timesOnBase).divide(BigDecimal.valueOf(getAtBats()), 3, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getSlugging() {

		Integer singlePoints = getHits() - getDoubles() - getTriples() - getHomeRuns();
		Integer doublePoints = getDoubles() * 2;
		Integer triplePoints = getTriples() * 3;
		Integer homeRunPoints = getHomeRuns() * 4;

		Integer totalPoints = singlePoints + doublePoints + triplePoints + homeRunPoints;

		totalPoints = 50;

		return BigDecimal.valueOf(totalPoints).divide(BigDecimal.valueOf(getAtBats()), 3, BigDecimal.ROUND_DOWN);
	}

	public BigDecimal getOnBasePlusSlugging() {
		return getOnBasePercentage().add(getSlugging());
	}

}
