package com.arnolds.army.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnolds.army.model.Player;
import com.arnolds.army.model.Season;
import com.arnolds.army.model.Team;
import com.arnolds.army.service.ApplicationService;
import com.arnolds.army.util.ModelUtils;

@RestController
@RequestMapping("api")
public class ApiController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping("players/{id}")
	public Player getPlayer(@PathVariable Integer id) {
		return applicationService.findPlayer(id);
	}

	@GetMapping("players")
	public List<Player> findAllPlayers() {
		return applicationService.findAllPlayers();
	}

	@PostMapping("players")
	public void savePlayer(@RequestBody Player player) {
		player.setPhone(StringUtils.replaceAll(player.getPhone(), "[^0-9]", ""));

		applicationService.savePlayer(player);
	}

	@DeleteMapping("players/{id}")
	public void deletePlayer(@PathVariable Integer id) {
		applicationService.removePlayer(id);
	}

	@GetMapping("teams/{id}")
	public Team getTeam(@PathVariable Integer id) {
		return applicationService.findTeam(id);
	}

	@GetMapping("teams")
	public List<Team> findAllTeams() {
		return applicationService.findAllTeams();
	}

	@PostMapping({ "teams", "teams/{id}" })
	public void saveTeam(@RequestBody Team team) {

		if (team.getId() != null) {
			Team persistedTeam = applicationService.findTeam(team.getId());

			ModelUtils.merge(team, persistedTeam);

			applicationService.saveTeam(persistedTeam);
		} else {
			applicationService.saveTeam(team);
		}
	}

	@DeleteMapping("teams/{id}")
	public void deleteTeam(@PathVariable Integer id) {
		applicationService.removeTeam(id);
	}

	@GetMapping("seasons/{id}")
	public Season getSeason(@PathVariable Integer id) {
		return applicationService.findSeason(id);
	}

	@GetMapping("seasons")
	public List<Season> findAllSeasons() {
		return applicationService.findAllSeasons();
	}

	@PostMapping("seasons")
	public void saveSeason(@RequestBody Season season) {
		applicationService.saveSeason(season);
	}

	@DeleteMapping("seasons/{id}")
	public void deleteSeasons(@PathVariable Integer id) {
		applicationService.removeSeason(id);
	}
}
