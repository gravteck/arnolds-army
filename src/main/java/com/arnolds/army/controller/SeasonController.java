package com.arnolds.army.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arnolds.army.model.Season;
import com.arnolds.army.model.Team;
import com.arnolds.army.service.ApplicationService;

@Controller
@CrossOrigin
public class SeasonController {

  @Autowired
  private ApplicationService applicationService;

  @RequestMapping("calendar")
  public String loadCalendar(Model m) {

    m.addAttribute("arnoldsId", Team.ID_ARNOLDS);
    m.addAttribute("seasons", applicationService.findAllSeasons());

    return "calendar";
  }

  @RequestMapping("season/{seasonId}")
  public String loadSeason(Model m, @PathVariable Integer seasonId) {

    Season season = applicationService.findSeason(seasonId);

    m.addAttribute("arnoldsId", Team.ID_ARNOLDS);
    m.addAttribute("season", season);

    return "season";
  }

  @RequestMapping("seasons/list")
  @ResponseBody
  public List<Season> listSeasons(Model m) {
    return applicationService.findAllSeasons();
  }

}
