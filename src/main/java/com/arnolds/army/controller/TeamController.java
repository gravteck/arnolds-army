package com.arnolds.army.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arnolds.army.model.Team;
import com.arnolds.army.service.ApplicationService;

@Controller
@RequestMapping("team")
public class TeamController {

	@Autowired
	ApplicationService applicationService;

	@RequestMapping("{teamId}")
	public String loadTeam(Model m, @PathVariable Integer teamId) {

		Team team = applicationService.findTeam(teamId);

		m.addAttribute("team", team);

		return "team";
	}
}
