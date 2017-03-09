package com.arnolds.army.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "season")
public class Season extends BaseEntity {

	@Id
	@SequenceGenerator(name = "season_id_seq", sequenceName = "season_id_seq", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "season_id_seq")
	@Column(name = "id")
	private Integer id;

	@Column(name = "year")
	private Integer year;

	@OneToMany(mappedBy = "season", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
