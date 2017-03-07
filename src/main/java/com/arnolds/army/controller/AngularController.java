package com.arnolds.army.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arnolds.army.model.Player;
import com.arnolds.army.service.ApplicationService;

@Controller
@RequestMapping("s")
public class AngularController {

	@Autowired
	private ApplicationService applicationService;

	@GetMapping("")
	public String loadHome() {
		return "html/index";
	}

	@GetMapping("top")
	public String loadHeader() {
		return "ng/topNav";
	}

	@GetMapping("players")
	public String loadPlayers() {
		return "html/players";
	}

	@GetMapping("player/{playerId}")
	public String navPlayer(Model m) {

		return "html/player";
	}

	@GetMapping("player/get/{playerId}")
	@ResponseBody
	public Player getPlayer(Model m, @PathVariable Integer playerId) {
		return applicationService.findPlayer(playerId);
	}
}
