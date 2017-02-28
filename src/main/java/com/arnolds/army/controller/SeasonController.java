package com.arnolds.army.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SeasonController {

	@RequestMapping("calendar")
	public String loadCalendar(Model m) {

		return "calendar";
	}
}
