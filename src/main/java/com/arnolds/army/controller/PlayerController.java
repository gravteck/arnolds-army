package com.arnolds.army.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arnolds.army.model.Player;
import com.arnolds.army.model.StatisticalYear;

@Controller
public class PlayerController {

	@GetMapping("players")
	public String loadPlayers(Model m) {

		m.addAttribute("players", getPlayers());

		return "players";
	}

	@GetMapping("player/{playerId}")
	public String loadPlayer(Model m, @PathVariable Integer playerId) {

		m.addAttribute("player", getPlayer(playerId));

		return "player";
	}

	@GetMapping("players/list")
	@ResponseBody
	public List<Player> listPlayers() {
		List<Player> players = getPlayers();

		return players;
	}

	private List<Player> getPlayers() {
		List<Player> players = new ArrayList<>();

		Player matt = new Player();
		matt.setId(1);
		matt.setFirstName("Matt");
		matt.setLastName("Heineke");
		matt.setPhone("5134047250");
		matt.setEmail("matthew.heineke@gmail.com");

		StatisticalYear year2015 = new StatisticalYear();
		year2015.setId(1);
		year2015.setYear(2015);
		year2015.setAtBats(84);
		year2015.setRuns(11);
		year2015.setHits(27);
		year2015.setDoubles(7);
		year2015.setTriples(0);
		year2015.setHomeRuns(4);
		year2015.setRbi(17);
		year2015.setWalks(5);
		year2015.setStrikeOuts(15);

		StatisticalYear year2016 = new StatisticalYear();
		year2016.setId(2);
		year2016.setYear(2016);
		year2016.setAtBats(526);
		year2016.setRuns(69);
		year2016.setHits(156);
		year2016.setDoubles(32);
		year2016.setTriples(3);
		year2016.setHomeRuns(24);
		year2016.setRbi(84);
		year2016.setWalks(59);
		year2016.setStrikeOuts(102);

		matt.getStatisticalYears().add(year2016);

		StatisticalYear year2017 = new StatisticalYear();
		year2017.setId(3);
		year2017.setYear(2017);
		year2017.setAtBats(469);
		year2017.setRuns(82);
		year2017.setHits(151);
		year2017.setDoubles(38);
		year2017.setTriples(1);
		year2017.setHomeRuns(25);
		year2017.setRbi(84);
		year2017.setWalks(70);
		year2017.setStrikeOuts(106);

		matt.getStatisticalYears().add(year2017);

		Player ray = new Player();
		ray.setId(2);
		ray.setFirstName("Ray");
		ray.setLastName("Piening");
		ray.setPhone("2939181938");
		ray.setEmail("ray.piening@gmail.com");

		Player eric = new Player();
		eric.setId(3);
		eric.setFirstName("Eric");
		eric.setLastName("Ihlendorf");
		eric.setPhone("8392718395");
		eric.setEmail("eric.ihlendorf@gmail.com");

		Player brian = new Player();
		brian.setId(4);
		brian.setFirstName("Brian");
		brian.setLastName("Something");
		brian.setPhone("8173028192");
		brian.setEmail("Brian.something@gmail.com");

		players.addAll(Arrays.asList(matt, ray, eric, brian));
		return players;
	}	

	private Player getPlayer(Integer id) {
		return getPlayers().stream().findFirst().filter(p -> id.equals(p.getId())).get();
	}

}
