package com.arnolds.army.model;

import java.util.ArrayList;
import java.util.List;

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

}
