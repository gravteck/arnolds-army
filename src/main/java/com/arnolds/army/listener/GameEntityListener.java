package com.arnolds.army.listener;

import java.time.LocalDateTime;

import javax.persistence.PostLoad;

import com.arnolds.army.model.Game;

public class GameEntityListener extends BaseEntityListener {

  @PostLoad
  protected void onPostLoad(Game game) {
    setUpTransientTimes(game);
  }

  private void setUpTransientTimes(Game game) {
    game.setMonth(game.getLocalDateTime().getMonthValue());
    game.setDay(game.getLocalDateTime().getDayOfMonth());
    game.setYear(game.getLocalDateTime().getYear());

    Integer hour = game.getLocalDateTime().getHour();

    game.setHour(hour > 12 ? hour - 12 : hour);
    game.setMinuteInterval(String.valueOf(game.getLocalDateTime().getMinute()));
    game.setPeriod(hour > 12 ? Game.PERIOD_PM : Game.PERIOD_AM);
  }
}
