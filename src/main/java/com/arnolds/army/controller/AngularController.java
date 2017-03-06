package com.arnolds.army.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("s")
public class AngularController {

	@GetMapping("")
	public String loadHome(Model m) {
		return "html/index";
	}

	@GetMapping("top")
	public String loadHeader(Model m) {
		return "ng/topNav";
	}
}
