package com.arnolds.army.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arnolds.army.model.Player;
import com.arnolds.army.service.ApplicationService;

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
}
