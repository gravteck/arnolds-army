package com.arnolds.army.service;

import java.util.List;

import com.arnolds.army.model.Player;
import com.arnolds.army.model.StatisticalYear;
import com.arnolds.army.model.Team;

public interface ApplicationService {

	List<Team> findAllTeams();

	List<Player> findAllPlayers();

	Player findPlayer(Integer playerId);

	List<StatisticalYear> findAllStatisticalYears(Integer playerId);
}
