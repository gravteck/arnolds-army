package com.arnolds.army.controller;

import static com.arnolds.army.model.ReportingField.delete;
import static com.arnolds.army.model.ReportingField.edit;
import static com.arnolds.army.model.ReportingField.link;
import static com.arnolds.army.model.ReportingField.text;
import static com.arnolds.army.model.ReportingField.view;
import static com.arnolds.army.model.ReportingField.group;
import static com.arnolds.army.model.ReportingField.reverseGroup;
import static com.arnolds.army.model.ReportingField.spacer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

import com.arnolds.army.FunctionalAreaType;
import com.arnolds.army.model.Player;
import com.arnolds.army.model.ReportingField;
import com.arnolds.army.model.Team;
import com.arnolds.army.service.ApplicationService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping("{functionalArea}/list")
	public String loadAdmin(Model m, @PathVariable String functionalArea) {

		FunctionalAreaType functionalAreaType = FunctionalAreaType.find(functionalArea);
		List<String> headers = new ArrayList<>();
		List<List<ReportingField>> records = new ArrayList<>();
		StringBuilder title = new StringBuilder();
		StringBuilder itemFunctionalArea = new StringBuilder();

		switch (functionalAreaType) {
		case PLAYERS:
			loadPlayers(headers, records, title, itemFunctionalArea);
		case GAME:
			break;
		case GAMES:
			break;
		case PLAYER:
			break;
		case STATISTICAL_YEAR:
			break;
		case STATISTICAL_YEARS:
			break;
		case TEAM:
			break;
		case TEAMS:
			loadTeams(headers, records, title, itemFunctionalArea);
			break;
		default:
			break;
		}

		m.addAttribute("title", title);
		m.addAttribute("addPath", "/admin/" + itemFunctionalArea + "/add/");
		m.addAttribute("deletePath", "/admin/" + itemFunctionalArea + "/delete/");
		m.addAttribute("headers", headers);
		m.addAttribute("records", records);

		return "admin/admin";
	}

	@GetMapping("players")
	public String loadAdminPlayers(Model m) {
		List<Player> players = applicationService.findAllPlayers();

		// We don't need all the StatisticalYear instances when loading our list
		// of players. There's also a bug in Thymeleaf when it's being inspected
		// that ends in an infinite loop due to the bidirectional relationship
		// between Player and StatisticalYear.
		players.stream().forEach(p -> p.setStatisticalYears(Collections.emptyList()));

		m.addAttribute("players", players);

		List<String> headers = new ArrayList<>(Arrays.asList("First Name", "Last Name", "Phone", "Email", "", "", ""));
		// List<KeyValue> reportingFields = new
		// ArrayList<>(ImmutableMap.of("firstName", ))

		List<List<ReportingField>> records = players.stream()
				.map(p -> Arrays.asList(text("firstName", p.getFirstName()), text("lastName", p.getLastName()),
						link("phone", p.getPhone(), "tel:" + p.getPhone()),
						link("email", p.getEmail(), "mailto:" + p.getEmail()), view("/player/" + p.getId()),
						edit("/admin/player/edit/" + p.getId()), delete(p.getId())))
				.collect(Collectors.toList());

		m.addAttribute("title", "Players");
		m.addAttribute("addPath", "/admin/player/add/");
		m.addAttribute("deletePath", "/admin/player/delete/");
		m.addAttribute("headers", headers);
		m.addAttribute("records", records);

		return "admin/players";
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

		return "redirect:/admin/players/list";
	}

	@RequestMapping("player/delete/{playerId}")
	public String playerDeleteSubmit(@PathVariable Integer playerId, RedirectAttributes redirectAttributes) {

		applicationService.removePlayer(playerId);

		redirectAttributes.addFlashAttribute("deleted", Boolean.TRUE);

		return "redirect:/admin/players/list";
	}

	private void loadPlayers(List<String> headers, List<List<ReportingField>> records, StringBuilder title,
			StringBuilder itemFunctionalArea) {
		List<Player> players = applicationService.findAllPlayers();

		title(title, FunctionalAreaType.PLAYERS.title());
		itemFunctionalArea(itemFunctionalArea, FunctionalAreaType.PLAYER.value());
		headers(headers, "First Name", "Last Name", "Phone", "Email", "");

		records(records,
				players.stream().map(p -> Arrays.asList(text("firstName", p.getFirstName()),
						text("lastName", p.getLastName()), link("phone", p.getPhone(), "tel:" + p.getPhone()),
						link("email", p.getEmail(), "mailto:" + p.getEmail()),
						group(view("/" + itemFunctionalArea + "/" + p.getId()),
								edit("/admin/" + itemFunctionalArea + "/edit/" + p.getId()), delete(p.getId()))))
						.collect(Collectors.toList()));
	}

	private void loadTeams(List<String> headers, List<List<ReportingField>> records, StringBuilder title,
			StringBuilder itemFunctionalArea) {
		List<Team> teams = applicationService.findAllTeams();

		title(title, FunctionalAreaType.TEAMS.title());
		itemFunctionalArea(itemFunctionalArea, FunctionalAreaType.TEAM.value());
		headers(headers, "Name", "", "");

		records(records,
				teams.stream().map(t -> Arrays.asList(text("Name", t.getName()), spacer(),
						reverseGroup(view("/" + itemFunctionalArea + "/" + t.getId()),
								edit("/admin/" + itemFunctionalArea + "/edit/" + t.getId()), delete(t.getId()))))
						.collect(Collectors.toList()));
	}

	protected void title(StringBuilder title, String s) {
		title.setLength(0);
		title.append(s);
	}

	protected void itemFunctionalArea(StringBuilder itemFunctionalArea, String s) {
		itemFunctionalArea.setLength(0);
		itemFunctionalArea.append(s);
	}

	protected void headers(List<String> headers, String... s) {
		headers.addAll(Arrays.asList(s));
	}

	protected void records(List<List<ReportingField>> records, Collection<List<ReportingField>> collection) {
		records.addAll(collection);
	}
}
