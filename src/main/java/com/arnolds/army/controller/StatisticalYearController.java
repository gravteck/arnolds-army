package com.arnolds.army.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.arnolds.army.model.StatisticalYear;
import com.arnolds.army.service.ApplicationService;

@Controller
@RequestMapping("statistical-year")
public class StatisticalYearController {

	@Autowired
	ApplicationService applicationService;

	@RequestMapping("{statisticalYearId}")
	public String loadStatisticalYear(Model m, @PathVariable Integer statisticalYearId) {

		StatisticalYear statisticalYear = applicationService.findStatisticalYear(statisticalYearId);

		m.addAttribute("statisticalYear", statisticalYear);

		return "statistical-year";
	}
}
