package com.arnolds.army.service;

import java.util.List;

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

	List<StatisticalYear> findAllStatisticalYears(Integer playerId);

	List<Season> findAllSeasons();

	List<Game> findAllGames();

	List<StatisticalYear> findAllStatisticalYears();
}
