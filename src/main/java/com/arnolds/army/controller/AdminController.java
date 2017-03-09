package com.arnolds.army.controller;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arnolds.army.model.Player;
import com.arnolds.army.service.ApplicationService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping("players")
	public String loadAdminPlayers(Model m) {
		List<Player> players = applicationService.findAllPlayers();

		// We don't need all the StatisticalYear instances when loading our list
		// of players. There's also a bug in Thymeleaf when it's being inspected
		// that ends in an infinite loop due to the bidirectional relationship
		// between Player and StatisticalYear.
		players.stream().forEach(p -> p.setStatisticalYears(Collections.emptyList()));

		m.addAttribute("players", players);

		return "admin/players-admin";
	}

	@GetMapping("player/edit/{playerId}")
	public String loadEditPlayer(Model m, @PathVariable Integer playerId) {

		Player player = applicationService.findPlayer(playerId);

		m.addAttribute("player", player);

		return "admin/player-edit";
	}

	@GetMapping("player/add")
	public String loadAddPlayer(Model m) {

		m.addAttribute("player", new Player());

		return "admin/player-add";
	}

	@PostMapping("player/edit/submit")
	public String playerEditSubmit(@ModelAttribute Player player, RedirectAttributes redirectAttributes) {

		player.setPhone(StringUtils.replaceAll(player.getPhone(), "[^0-9]", ""));

		applicationService.savePlayer(player);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/players";
	}

	@PostMapping("player/add/submit")
	public String playerAddSubmit(@ModelAttribute Player player, RedirectAttributes redirectAttributes) {

		player.setPhone(StringUtils.replaceAll(player.getPhone(), "[^0-9]", ""));

		applicationService.savePlayer(player);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/players";
	}

	@RequestMapping("player/delete/{playerId}")
	public String playerAddSubmit(@PathVariable Integer playerId, RedirectAttributes redirectAttributes) {

		applicationService.removePlayer(playerId);

		return "redirect:/admin/players";
	}
}
