package com.arnolds.army.controller;

import static com.arnolds.army.model.ReportingField.delete;
import static com.arnolds.army.model.ReportingField.edit;
import static com.arnolds.army.model.ReportingField.group;
import static com.arnolds.army.model.ReportingField.link;
import static com.arnolds.army.model.ReportingField.reverseGroup;
import static com.arnolds.army.model.ReportingField.spacer;
import static com.arnolds.army.model.ReportingField.text;
import static com.arnolds.army.model.ReportingField.view;

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
import com.arnolds.army.model.Game;
import com.arnolds.army.model.Player;
import com.arnolds.army.model.ReportingField;
import com.arnolds.army.model.Season;
import com.arnolds.army.model.StatisticalYear;
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
			break;
		case SEASONS:
			loadSeasons(headers, records, title, itemFunctionalArea);
			break;
		case GAMES:
			loadGames(headers, records, title, itemFunctionalArea);
			break;
		case STATISTICAL_YEARS:
			loadStatisticalYears(headers, records, title, itemFunctionalArea);
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

	@GetMapping("team/edit/{teamId}")
	public String loadEditTeam(Model m, @PathVariable Integer teamId) {

		Team team = applicationService.findTeam(teamId);

		m.addAttribute("team", team);

		return "admin/team-edit";
	}

	@GetMapping("season/edit/{seasonId}")
	public String loadEditSeason(Model m, @PathVariable Integer seasonId) {

		Season season = applicationService.findSeason(seasonId);

		m.addAttribute("season", season);

		return "admin/season-edit";
	}

	@GetMapping("game/edit/{gameId}")
	public String loadEditGame(Model m, @PathVariable Integer gameId) {

		Game game = applicationService.findGame(gameId);
		List<Team> teams = applicationService.findAllTeams();
		List<Season> seasons = applicationService.findAllSeasons();

		m.addAttribute("game", game);
		m.addAttribute("teams", teams);
		m.addAttribute("seasons", seasons);

		return "admin/game-edit";
	}

	@GetMapping("team/add")
	public String loadAddTeam(Model m) {

		m.addAttribute("team", new Team());

		return "admin/team-add";
	}

	@GetMapping("player/add")
	public String loadAddPlayer(Model m) {

		m.addAttribute("player", new Player());

		return "admin/player-add";
	}

	@GetMapping("season/add")
	public String loadAddSeason(Model m) {

		m.addAttribute("season", new Season());

		return "admin/season-add";
	}

	@PostMapping("player/edit/submit")
	public String playerEditSubmit(@ModelAttribute Player player, RedirectAttributes redirectAttributes) {

		player.setPhone(StringUtils.replaceAll(player.getPhone(), "[^0-9]", ""));

		Player persistedPlayer = applicationService.findPlayer(player.getId());

		persistedPlayer.setFirstName(player.getFirstName());
		persistedPlayer.setLastName(player.getLastName());
		persistedPlayer.setEmail(player.getEmail());
		persistedPlayer.setPhone(player.getPhone());

		applicationService.savePlayer(persistedPlayer);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.PLAYERS.value() + "/list";
	}

	@PostMapping("team/add/submit")
	public String teamAddSubmit(@ModelAttribute Team team, RedirectAttributes redirectAttributes) {

		applicationService.saveTeam(team);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.TEAMS.value() + "/list";
	}

	@PostMapping("season/add/submit")
	public String teamSeasonSubmit(@ModelAttribute Season season, RedirectAttributes redirectAttributes) {

		applicationService.saveSeason(season);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.SEASONS.value() + "/list";
	}

	@PostMapping("season/edit/submit")
	public String seasonEditSubmit(@ModelAttribute Season season, RedirectAttributes redirectAttributes) {

		Season persistedSeason = applicationService.findSeason(season.getId());

		persistedSeason.setYear(season.getYear());

		applicationService.saveSeason(persistedSeason);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.SEASONS.value() + "/list";
	}

	@PostMapping("team/edit/submit")
	public String teamEditSubmit(@ModelAttribute Team team, RedirectAttributes redirectAttributes) {

		Team persistedTeam = applicationService.findTeam(team.getId());

		persistedTeam.setName(team.getName());

		applicationService.saveTeam(persistedTeam);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.TEAMS.value() + "/list";
	}

	@RequestMapping("season/delete/{seasonId}")
	public String seasonDeleteSubmit(@PathVariable Integer seasonId, RedirectAttributes redirectAttributes) {

		applicationService.removeSeason(seasonId);

		redirectAttributes.addFlashAttribute("deleted", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.SEASONS.value() + "/list";
	}

	@PostMapping("player/add/submit")
	public String playerAddSubmit(@ModelAttribute Player player, RedirectAttributes redirectAttributes) {

		player.setPhone(StringUtils.replaceAll(player.getPhone(), "[^0-9]", ""));

		applicationService.savePlayer(player);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.PLAYERS.value() + "/list";
	}

	@RequestMapping("player/delete/{playerId}")
	public String playerDeleteSubmit(@PathVariable Integer playerId, RedirectAttributes redirectAttributes) {

		applicationService.removePlayer(playerId);

		redirectAttributes.addFlashAttribute("deleted", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.PLAYERS.value() + "/list";
	}

	@RequestMapping("team/delete/{teamId}")
	public String teamDeleteSubmit(@PathVariable Integer teamId, RedirectAttributes redirectAttributes) {

		applicationService.removeTeam(teamId);

		redirectAttributes.addFlashAttribute("deleted", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.TEAMS.value() + "/list";
	}

	@RequestMapping("login")
	public String login() {
		return "admin/login";
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
						reverseGroup(view("/" + itemFunctionalArea + "/" + p.getId()),
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

	private void loadSeasons(List<String> headers, List<List<ReportingField>> records, StringBuilder title,
			StringBuilder itemFunctionalArea) {
		List<Season> seasons = applicationService.findAllSeasons();

		title(title, FunctionalAreaType.SEASONS.title());
		itemFunctionalArea(itemFunctionalArea, FunctionalAreaType.SEASON.value());
		headers(headers, "Name", "", "");

		records(records,
				seasons.stream().map(t -> Arrays.asList(text("Name", t.getYear()), spacer(),
						reverseGroup(view("/" + itemFunctionalArea + "/" + t.getId()),
								edit("/admin/" + itemFunctionalArea + "/edit/" + t.getId()), delete(t.getId()))))
						.collect(Collectors.toList()));
	}

	private void loadGames(List<String> headers, List<List<ReportingField>> records, StringBuilder title,
			StringBuilder itemFunctionalArea) {
		List<Game> games = applicationService.findAllGames();

		title(title, FunctionalAreaType.GAMES.title());
		itemFunctionalArea(itemFunctionalArea, FunctionalAreaType.GAME.value());
		headers(headers, "#", "Home Team", "Away Team", "Home Score", "Away Score", "");

		records(records,
				games.stream().map(g -> Arrays.asList(text("id", g.getId()),
						text("homeTeam", g.getHomeTeam().getName()), text("awayTeam", g.getAwayTeam().getName()),
						text("homeScore", g.getHomeScore()), text("homeScore", g.getHomeScore()), spacer(),
						reverseGroup(view("/" + itemFunctionalArea + "/" + g.getId()),
								edit("/admin/" + itemFunctionalArea + "/edit/" + g.getId()), delete(g.getId()))))
						.collect(Collectors.toList()));
	}

	private void loadStatisticalYears(List<String> headers, List<List<ReportingField>> records, StringBuilder title,
			StringBuilder itemFunctionalArea) {
		List<StatisticalYear> statisticalYears = applicationService.findAllStatisticalYears();

		title(title, FunctionalAreaType.STATISTICAL_YEARS.title());
		itemFunctionalArea(itemFunctionalArea, FunctionalAreaType.STATISTICAL_YEAR.value());
		headers(headers, "#", "Player", "Season", "AB", "R", "H", "2B", "3B", "HR", "RBI", "BB", "K", "AVG", "OBP",
				"SLG", "OPS", "");

		records(records, statisticalYears.stream()
				.map(sy -> Arrays.asList(text("id", sy.getId()), text("player", sy.getPlayer().getFullName()),
						text("season", sy.getSeason().getYear()), text("atBats", sy.getAtBats()),
						text("runs", sy.getRuns()), text("hits", sy.getHits()), text("doubles", sy.getDoubles()),
						text("triples", sy.getTriples()), text("homeRuns", sy.getHomeRuns()), text("rbi", sy.getRbi()),
						text("bb", sy.getWalks()), text("k", sy.getStrikeOuts()),
						text("avg", sy.getBattingAverage().toPlainString()), text("obp", sy.getOnBasePercentage()),
						text("slg", sy.getSlugging()), text("ops", sy.getOnBasePlusSlugging().toPlainString()),
						reverseGroup(view("/" + itemFunctionalArea + "/" + sy.getId()),
								edit("/admin/" + itemFunctionalArea + "/edit/" + sy.getId()), delete(sy.getId()))))
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
