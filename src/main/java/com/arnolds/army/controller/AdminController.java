package com.arnolds.army.controller;

import static com.arnolds.army.model.ReportingField.delete;
import static com.arnolds.army.model.ReportingField.edit;
import static com.arnolds.army.model.ReportingField.group;
import static com.arnolds.army.model.ReportingField.link;
import static com.arnolds.army.model.ReportingField.reverseGroup;
import static com.arnolds.army.model.ReportingField.spacer;
import static com.arnolds.army.model.ReportingField.text;
import static com.arnolds.army.model.ReportingField.view;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.KeyValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.arnolds.army.FunctionalAreaType;
import com.arnolds.army.dto.AdminDto;
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

		loadFunctionalArea(functionalAreaType, headers, records, title, itemFunctionalArea);

		m.addAttribute("title", title);
		m.addAttribute("addPath", "/admin/" + itemFunctionalArea + "/add/");
		m.addAttribute("deletePath", "/admin/" + itemFunctionalArea + "/delete/");
		m.addAttribute("headers", headers);
		m.addAttribute("records", records);

		return "admin/admin";
	}

	@GetMapping("{functionalArea}/get/list")
	@ResponseBody
	public AdminDto loadAdmin(@PathVariable String functionalArea) {

		FunctionalAreaType functionalAreaType = FunctionalAreaType.find(functionalArea);
		List<String> headers = new ArrayList<>();
		List<List<ReportingField>> records = new ArrayList<>();
		StringBuilder title = new StringBuilder();
		StringBuilder itemFunctionalArea = new StringBuilder();

		loadFunctionalArea(functionalAreaType, headers, records, title, itemFunctionalArea);

		return new AdminDto(title.toString(), "/admin/" + itemFunctionalArea + "/add/",
				"/admin/" + itemFunctionalArea + "/delete/", headers, records);
	}

	private void loadFunctionalArea(FunctionalAreaType functionalAreaType, List<String> headers,
			List<List<ReportingField>> records, StringBuilder title, StringBuilder itemFunctionalArea) {
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
		List<Integer> years = applicationService.findAllYears();
		List<KeyValue> months = applicationService.findAllMonths();
		List<Integer> days = applicationService.findAllDays();
		List<Integer> hours = applicationService.findAllHours();
		List<String> minuteIntervals = applicationService.findAllMinuteIntervals();
		List<String> periods = applicationService.findAllPeriods();

		m.addAttribute("game", game);
		m.addAttribute("teams", teams);
		m.addAttribute("seasons", seasons);
		m.addAttribute("years", years);
		m.addAttribute("months", months);
		m.addAttribute("days", days);
		m.addAttribute("hours", hours);
		m.addAttribute("minuteIntervals", minuteIntervals);
		m.addAttribute("periods", periods);

		return "admin/game-edit";
	}

	@GetMapping("statistical-year/edit/{statisticalYearId}")
	public String loadEditStatisticalYear(Model m, @PathVariable Integer statisticalYearId) {

		StatisticalYear statisticalYear = applicationService.findStatisticalYear(statisticalYearId);
		List<Player> players = applicationService.findAllPlayers();
		List<Season> seasons = applicationService.findAllSeasons();

		m.addAttribute("statisticalYear", statisticalYear);
		m.addAttribute("players", players);
		m.addAttribute("seasons", seasons);

		return "admin/statistical-year-edit";
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

	@GetMapping("game/add")
	public String loadAddGame(Model m) {

		List<Team> teams = applicationService.findAllTeams();
		List<Season> seasons = applicationService.findAllSeasons();
		List<Integer> years = applicationService.findAllYears();
		List<KeyValue> months = applicationService.findAllMonths();
		List<Integer> days = applicationService.findAllDays();
		List<Integer> hours = applicationService.findAllHours();
		List<String> minuteIntervals = applicationService.findAllMinuteIntervals();
		List<String> periods = applicationService.findAllPeriods();

		m.addAttribute("game", new Game());
		m.addAttribute("teams", teams);
		m.addAttribute("seasons", seasons);
		m.addAttribute("years", years);
		m.addAttribute("months", months);
		m.addAttribute("days", days);
		m.addAttribute("hours", hours);
		m.addAttribute("minuteIntervals", minuteIntervals);
		m.addAttribute("periods", periods);

		return "admin/game-add";
	}

	@GetMapping("statistical-year/add")
	public String loadAddStatisticalYear(Model m) {

		List<Player> players = applicationService.findAllPlayers();
		List<Season> seasons = applicationService.findAllSeasons();

		m.addAttribute("statisticalYear", new StatisticalYear());
		m.addAttribute("players", players);
		m.addAttribute("seasons", seasons);

		return "admin/statistical-year-add";
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

	@PostMapping("game/add/submit")
	public String gameAddSubmit(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {

		Integer hour = Game.PERIOD_PM.equals(game.getPeriod()) ? game.getHour() + 12 : game.getHour();

		LocalDateTime localDateTime = LocalDateTime.of(game.getYear(), game.getMonth(), game.getDay(), hour,
				Integer.valueOf(game.getMinuteInterval()));

		game.setLocalDateTime(localDateTime);

		applicationService.saveGame(game);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.GAMES.value() + "/list";
	}

	@PostMapping("statistical-year/add/submit")
	public String statisticalYearAddSubmit(@ModelAttribute StatisticalYear statisticalYear,
			RedirectAttributes redirectAttributes) {

		applicationService.saveStatisticalYear(statisticalYear);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.STATISTICAL_YEARS.value() + "/list";
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

	@PostMapping("game/edit/submit")
	public String gameEditSubmit(@ModelAttribute Game game, RedirectAttributes redirectAttributes) {

		Game persistedGame = applicationService.findGame(game.getId());

		Team homeTeam = applicationService.findTeam(game.getHomeTeam().getId());
		Team awayTeam = applicationService.findTeam(game.getAwayTeam().getId());
		Season season = applicationService.findSeason(game.getSeason().getId());

		persistedGame.setHomeTeam(homeTeam);
		persistedGame.setAwayTeam(awayTeam);
		persistedGame.setHomeScore(game.getHomeScore());
		persistedGame.setAwayScore(game.getAwayScore());
		persistedGame.setSeason(season);

		Integer hour = Game.PERIOD_PM.equals(game.getPeriod()) ? game.getHour() + 12 : game.getHour();

		LocalDateTime localDateTime = LocalDateTime.of(game.getYear(), game.getMonth(), game.getDay(), hour,
				Integer.valueOf(game.getMinuteInterval()));

		persistedGame.setLocalDateTime(localDateTime);

		applicationService.saveGame(persistedGame);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.GAMES.value() + "/list";
	}

	@PostMapping("statistical-year/edit/submit")
	public String statisticalYearEditSubmit(@ModelAttribute StatisticalYear statisticalYear,
			RedirectAttributes redirectAttributes) {

		StatisticalYear persistedStatisticalYear = applicationService.findStatisticalYear(statisticalYear.getId());

		persistedStatisticalYear.setPlayer(statisticalYear.getPlayer());
		persistedStatisticalYear.setSeason(statisticalYear.getSeason());
		persistedStatisticalYear.setAtBats(statisticalYear.getAtBats());
		persistedStatisticalYear.setRuns(statisticalYear.getRuns());
		persistedStatisticalYear.setHits(statisticalYear.getHits());
		persistedStatisticalYear.setDoubles(statisticalYear.getDoubles());
		persistedStatisticalYear.setTriples(statisticalYear.getTriples());
		persistedStatisticalYear.setHomeRuns(statisticalYear.getHomeRuns());
		persistedStatisticalYear.setRbi(statisticalYear.getRbi());
		persistedStatisticalYear.setWalks(statisticalYear.getWalks());
		persistedStatisticalYear.setStrikeOuts(statisticalYear.getStrikeOuts());

		applicationService.saveStatisticalYear(persistedStatisticalYear);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.STATISTICAL_YEARS.value() + "/list";
	}

	@RequestMapping("season/delete/{seasonId}")
	public String seasonDeleteSubmit(@PathVariable Integer seasonId, RedirectAttributes redirectAttributes) {

		applicationService.removeSeason(seasonId);

		redirectAttributes.addFlashAttribute("deleted", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.SEASONS.value() + "/list";
	}

	@RequestMapping("game/delete/{gameId}")
	public String gameDeleteSubmit(@PathVariable Integer gameId, RedirectAttributes redirectAttributes) {

		applicationService.removeGame(gameId);

		redirectAttributes.addFlashAttribute("deleted", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.GAMES.value() + "/list";
	}

	@RequestMapping("statistical-year/delete/{statisticalYearId}")
	public String statisticalYearDeleteSubmit(@PathVariable Integer statisticalYearId,
			RedirectAttributes redirectAttributes) {

		applicationService.removeStatisticalYear(statisticalYearId);

		redirectAttributes.addFlashAttribute("deleted", Boolean.TRUE);

		return "redirect:/admin/" + FunctionalAreaType.STATISTICAL_YEARS.value() + "/list";
	}

	@PostMapping("player/add/submit")
	public String playerAddSubmit(@ModelAttribute Player player, @RequestParam("ng") boolean angular,
			RedirectAttributes redirectAttributes) {

		player.setPhone(StringUtils.replaceAll(player.getPhone(), "[^0-9]", ""));

		applicationService.savePlayer(player);

		redirectAttributes.addFlashAttribute("saved", Boolean.TRUE);

		if (angular) {
			redirectAttributes.addFlashAttribute("ng", angular);
		}

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
		headers(headers, "Season", "Home Team", "Away Team", "Home Score", "Away Score", "Date", "Time");

		records(records,
				games.stream().map(g -> Arrays.asList(text("season", g.getSeason().getYear()),
						text("homeTeam", g.getHomeTeam().getName()), text("awayTeam", g.getAwayTeam().getName()),
						text("homeScore", g.getHomeScore()), text("awayScore", g.getAwayScore()),
						text("date", g.getMonth() + "/" + g.getDay() + "/" + g.getYear()),
						text("time", g.getHour() + ":" + g.getMinuteInterval() + " " + g.getPeriod()),
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
