package com.arnolds.army.dto;

import java.util.List;

import org.apache.commons.collections.KeyValue;

import com.arnolds.army.model.Game;
import com.arnolds.army.model.Season;
import com.arnolds.army.model.Team;

public class GameDto {

  private Game game;
  
  private List<Team> teams;
  
  private List<Season> seasons;
  
  private List<Integer> years;
  
  private List<KeyValue> months;
  
  private List<Integer> days;
  
  private List<Integer> hours;

  private List<String> minuteIntervals;

  private List<String> periods;

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public List<Team> getTeams() {
    return teams;
  }

  public void setTeams(List<Team> teams) {
    this.teams = teams;
  }

  public List<Season> getSeasons() {
    return seasons;
  }

  public void setSeasons(List<Season> seasons) {
    this.seasons = seasons;
  }

  public List<Integer> getYears() {
    return years;
  }

  public void setYears(List<Integer> years) {
    this.years = years;
  }

  public List<KeyValue> getMonths() {
    return months;
  }

  public void setMonths(List<KeyValue> months) {
    this.months = months;
  }

  public List<Integer> getDays() {
    return days;
  }

  public void setDays(List<Integer> days) {
    this.days = days;
  }

  public List<Integer> getHours() {
    return hours;
  }

  public void setHours(List<Integer> hours) {
    this.hours = hours;
  }

  public List<String> getMinuteIntervals() {
    return minuteIntervals;
  }

  public void setMinuteIntervals(List<String> minuteIntervals) {
    this.minuteIntervals = minuteIntervals;
  }

  public List<String> getPeriods() {
    return periods;
  }

  public void setPeriods(List<String> periods) {
    this.periods = periods;
  }
}