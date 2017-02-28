package com.arnolds.army.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arnolds.army.model.Game;
import com.arnolds.army.model.Season;
import com.arnolds.army.model.Team;

@Controller
public class SeasonController {

	@RequestMapping("calendar")
	public String loadCalendar(Model m) {

		m.addAttribute("arnoldsId", Team.ID_ARNOLDS);
		m.addAttribute("seasons", getSeasons());

		return "calendar";
	}

	public List<Season> getSeasons() {

		List<Season> seasons = new ArrayList<>();

		Team arnoldsArmy = new Team();
		arnoldsArmy.setId(Team.ID_ARNOLDS);
		arnoldsArmy.setName("Arnold's Army");

		Team team2 = new Team();
		team2.setId(2);
		team2.setName("Team 2");

		Team team3 = new Team();
		team3.setId(3);
		team3.setName("Team 3");

		Team team4 = new Team();
		team4.setId(4);
		team4.setName("Team 4");

		Team team5 = new Team();
		team5.setId(5);
		team5.setName("Team 5");

		Season s1 = new Season();
		s1.setId(1);
		s1.setYear(2015);

		Game g1 = new Game();
		g1.setId(1);
		g1.setLocalDateTime(LocalDateTime.now().minusWeeks(3));
		g1.setHomeTeam(arnoldsArmy);
		g1.setAwayTeam(team2);
		g1.setHomeScore(5);
		g1.setAwayScore(3);
		g1.setSeason(s1);

		Game g2 = new Game();
		g2.setId(2);
		g2.setLocalDateTime(LocalDateTime.now().minusWeeks(2));
		g2.setHomeTeam(arnoldsArmy);
		g2.setAwayTeam(team3);
		g2.setHomeScore(2);
		g2.setAwayScore(8);
		g2.setSeason(s1);

		Game g3 = new Game();
		g3.setId(3);
		g3.setLocalDateTime(LocalDateTime.now().minusWeeks(2));
		g3.setHomeTeam(team4);
		g3.setAwayTeam(arnoldsArmy);
		g3.setHomeScore(7);
		g3.setAwayScore(10);
		g3.setSeason(s1);

		Game g4 = new Game();
		g4.setId(4);
		g4.setLocalDateTime(LocalDateTime.now());
		g4.setHomeTeam(team5);
		g4.setAwayTeam(arnoldsArmy);
		g4.setHomeScore(5);
		g4.setAwayScore(4);
		g4.setSeason(s1);

		Game g5 = new Game();
		g5.setId(5);
		g5.setLocalDateTime(LocalDateTime.now().plusWeeks(1));
		g5.setHomeTeam(team5);
		g5.setAwayTeam(arnoldsArmy);
		g5.setHomeScore(2);
		g5.setAwayScore(8);
		g5.setSeason(s1);

		Season s2 = new Season();
		s2.setId(2);
		s2.setYear(2016);

		s1.getGames().addAll(Arrays.asList(g1, g2, g3, g4, g5));
		s2.getGames().addAll(Arrays.asList(g1, g2, g3, g4, g5));

		seasons.add(s1);
		seasons.add(s2);

		return seasons;
	}
}
