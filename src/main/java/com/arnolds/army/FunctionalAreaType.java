package com.arnolds.army;

import java.util.Arrays;

public enum FunctionalAreaType {
	PLAYER("Player", "player"), PLAYERS("Players", "players"), TEAM("Team", "team"), TEAMS("Teams", "teams"), SEASON(
			"Season", "season"), SEASONS("Seasons", "seasons"), GAME("Game", "game"), GAMES("Games",
					"games"), STATISTICAL_YEAR("Statistical Year",
							"statistical-year"), STATISTICAL_YEARS("Statistical Years", "statistical-years");

	private String title;
	private String value;

	FunctionalAreaType(String title, String value) {
		this.title = title;
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public String title() {
		return this.title;
	}

	public static FunctionalAreaType find(String s) {
		return Arrays.asList(FunctionalAreaType.values()).stream().filter(t -> t.value().equals(s)).findFirst().get();
	}
}
