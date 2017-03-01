package com.arnolds.army.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Season {

	private Integer id;

	private Integer year;

	private List<Game> games = new ArrayList<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Integer getWins(Integer teamId) {

		return getGames().stream()
				.filter(g -> (teamId == g.getAwayTeam().getId() && g.getAwayScore() > g.getHomeScore())
						|| (teamId == g.getHomeTeam().getId() && g.getHomeScore() > g.getAwayScore()))
				.collect(Collectors.toList()).size();
	}

	public Integer getLosses(Integer teamId) {
		return getGames().size() - getWins(teamId);
	}

}
