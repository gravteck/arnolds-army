package com.arnolds.army.listener;

import java.util.stream.Collectors;

import javax.persistence.PostLoad;

import com.arnolds.army.model.Game;
import com.arnolds.army.model.Season;
import com.arnolds.army.model.Team;

public class SeasonEntityListener extends BaseEntityListener {

  @PostLoad
  protected void onPostLoad(Season season) {
    setUp(season);
  }

  private void setUp(Season season) {

    season.setWins(season.getGames().stream().filter(
        g -> (Team.ID_ARNOLDS == g.getAwayTeam().getId() && g.getAwayScore() > g.getHomeScore())
            || (Team.ID_ARNOLDS == g.getHomeTeam().getId() && g.getHomeScore() > g.getAwayScore()))
        .collect(Collectors.toList()).size());

    season.setLosses(season.getGames().size() - season.getWins());
  }
}
