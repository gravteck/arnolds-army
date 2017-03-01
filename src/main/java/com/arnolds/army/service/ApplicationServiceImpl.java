/**
 * 
 */
package com.arnolds.army.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arnolds.army.model.Player;
import com.arnolds.army.model.StatisticalYear;
import com.arnolds.army.model.Team;
import com.trg.dao.jpa.GenericDAO;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private GenericDAO<Team, Serializable> teamDao;

	@Autowired
	private GenericDAO<Player, Serializable> playerDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllPlayers()
	 */
	@Override
	public List<Team> findAllTeams() {
		return teamDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllPlayers()
	 */
	@Override
	public List<Player> findAllPlayers() {
		return playerDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#findPlayer(java.lang.Integer)
	 */
	@Override
	public Player findPlayer(Integer playerId) {
		return playerDao.find(playerId);
	}

	@Override
	public List<StatisticalYear> findAllStatisticalYears(Integer playerId) {
		return null;
	}

}
