/**
 * 
 */
package com.arnolds.army.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arnolds.army.model.Game;
import com.arnolds.army.model.Player;
import com.arnolds.army.model.Season;
import com.arnolds.army.model.StatisticalYear;
import com.arnolds.army.model.Team;
import com.trg.dao.jpa.GenericDAO;

@Service
public class ApplicationServiceImpl implements ApplicationService {

	@Autowired
	private GenericDAO<Team, Serializable> teamDao;

	@Autowired
	private GenericDAO<Player, Serializable> playerDao;

	@Autowired
	private GenericDAO<Season, Serializable> seasonDao;

	@Autowired
	private GenericDAO<Game, Serializable> gameDao;

	@Autowired
	private GenericDAO<StatisticalYear, Serializable> statisticalYearDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#findTeam(java.lang.Integer)
	 */
	@Override
	public Team findTeam(Integer teamId) {
		return teamDao.find(teamId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllPlayers()
	 */
	@Override
	// @Cacheable("teams")
	public List<Team> findAllTeams() {
		return teamDao.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#saveTeam(com.arnolds.army.
	 * model.Team)
	 */
	@Override
	@Transactional
	public void saveTeam(Team team) {
		teamDao.save(team);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#removeTeam(java.lang.Integer)
	 */
	@Override
	@Transactional
	public void removeTeam(Integer teamId) {
		teamDao.removeById(teamId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllPlayers()
	 */
	@Override
	// @Cacheable("players")
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
	// @Cacheable("player")
	public Player findPlayer(Integer playerId) {
		return playerDao.find(playerId);
	}

	@Override
	@Transactional
	// @Caching(put = { @CachePut(cacheNames = "player") }, evict = {
	// @CacheEvict(cacheNames = "players") })
	public void savePlayer(Player player) {
		playerDao.save(player);
	}

	@Override
	@Transactional
	public void removePlayer(Integer playerId) {
		playerDao.removeById(playerId);
	}

	@Override
	public List<StatisticalYear> findAllStatisticalYears(Integer playerId) {
		return null;
	}

	@Override
	@Cacheable("seasons")
	public List<Season> findAllSeasons() {
		return seasonDao.findAll();
	}

	@Override
	public List<Game> findAllGames() {
		return gameDao.findAll();
	}

	@Override
	public List<StatisticalYear> findAllStatisticalYears() {
		return statisticalYearDao.findAll();
	}

}
