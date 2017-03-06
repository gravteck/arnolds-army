package com.arnolds.army.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arnolds.army.model.Team;
import com.arnolds.army.service.ApplicationService;

@Controller
public class SeasonController {

	@Autowired
	private ApplicationService applicationService;

	@RequestMapping("calendar")
	public String loadCalendar(Model m) {

		m.addAttribute("arnoldsId", Team.ID_ARNOLDS);
		m.addAttribute("seasons", applicationService.findAllSeasons());

		return "calendar";
	}
}
