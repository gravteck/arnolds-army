package com.arnolds.army.service;

import java.util.List;

import org.apache.commons.collections.KeyValue;

import com.arnolds.army.model.Game;
import com.arnolds.army.model.Player;
import com.arnolds.army.model.Season;
import com.arnolds.army.model.StatisticalYear;
import com.arnolds.army.model.Team;

public interface ApplicationService {

	List<Team> findAllTeams();

	Team findTeam(Integer teamId);

	void saveTeam(Team team);

	void removeTeam(Integer teamId);

	List<Player> findAllPlayers();

	Player findPlayer(Integer playerId);

	void savePlayer(Player player);

	void removePlayer(Integer playerId);

	Season findSeason(Integer seasonId);

	void saveSeason(Season season);

	void removeSeason(Integer seasonId);

	Game findGame(Integer gameId);

	void saveGame(Game game);

	void removeGame(Integer gameId);

	List<StatisticalYear> findAllStatisticalYears(Integer playerId);

	List<Season> findAllSeasons();

	List<Game> findAllGames();

	List<StatisticalYear> findAllStatisticalYears();

	List<Integer> findAllYears();

	List<KeyValue> findAllMonths();

	List<Integer> findAllDays();

	List<Integer> findAllHours();

	List<String> findAllMinuteIntervals();

	List<String> findAllPeriods();
}
