package com.arnolds.army;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arnolds.army.model.Player;

@SpringBootApplication
@Controller
public class ArnoldsArmyApplication {

	@RequestMapping("home")
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
