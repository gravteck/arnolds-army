package com.arnolds.army.controller;

import static org.assertj.core.api.Assertions.setLenientDateParsing;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arnolds.army.model.Player;
import com.arnolds.army.service.ApplicationService;

@Controller
@CrossOrigin
public class PlayerController {

  @Autowired
  private ApplicationService applicationService;

  @GetMapping(value = {"players"})
  public String loadPlayers(Model m) {

    List<Player> players = applicationService.findAllPlayers();

    // We don't need all the StatisticalYear instances when loading our list
    // of players. There's also a bug in Thymeleaf when it's being inspected
    // that ends in an infinite loop due to the bidirectional relationship
    // between Player and StatisticalYear.
    players.stream().forEach(p -> p.setStatisticalYears(Collections.emptyList()));

    m.addAttribute("players", players);

    return "players";
  }

  @GetMapping("player/{playerId}")
  public String loadPlayer(Model m, @PathVariable Integer playerId) {

    m.addAttribute("player", applicationService.findPlayer(playerId));

    return "player";
  }

  @GetMapping("player/get/{playerId}")
  @ResponseBody
  public Player getPlayer(@PathVariable Integer playerId) {
    return applicationService.findPlayer(playerId);
  }

  @GetMapping("players/list")
  @ResponseBody
  public List<Player> listPlayers() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return applicationService.findAllPlayers();
  }

  @GetMapping("players/get/{playerId}")
  @ResponseBody
  public Player get(@PathVariable Integer playerId) {
    return applicationService.findPlayer(playerId);
  }
}
