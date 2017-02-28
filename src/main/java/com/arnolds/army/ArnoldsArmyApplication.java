package com.arnolds.army;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.arnolds.army.model.Player;
import com.arnolds.army.model.StatisticalYear;

@SpringBootApplication
@Controller
public class ArnoldsArmyApplication {

	@RequestMapping(value = { "/", "home" })
	public String loadHomePage(Model m) {

		m.addAttribute("players", getPlayers());

		return "home";
	}

	public static void main(String[] args) {
		SpringApplication.run(ArnoldsArmyApplication.class, args);
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

}
