/**
 * 
 */
package com.arnolds.army.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.KeyValue;
import org.apache.commons.collections.keyvalue.DefaultMapEntry;
import org.springframework.beans.factory.annotation.Autowired;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#findSeason(java.lang.Integer)
	 */
	@Override
	public Season findSeason(Integer seasonId) {
		return seasonDao.find(seasonId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#saveSeason(com.arnolds.army.
	 * model.Season)
	 */
	@Override
	@Transactional
	public void saveSeason(Season season) {
		seasonDao.save(season);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#removeSeason(java.lang.
	 * Integer)
	 */
	@Override
	@Transactional
	public void removeSeason(Integer seasonId) {
		seasonDao.removeById(seasonId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#findGame(java.lang.Integer)
	 */
	@Override
	public Game findGame(Integer gameId) {
		return gameDao.find(gameId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#saveGame(com.arnolds.army.
	 * model.Game)
	 */
	@Override
	@Transactional
	public void saveGame(Game game) {
		gameDao.save(game);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.arnolds.army.service.ApplicationService#removeGame(java.lang.Integer)
	 */
	@Override
	@Transactional
	public void removeGame(Integer gameId) {
		gameDao.removeById(gameId);
	}

	@Override
	public List<StatisticalYear> findAllStatisticalYears(Integer playerId) {
		return null;
	}

	@Override
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllYears()
	 */
	@Override
	public List<Integer> findAllYears() {

		List<Integer> years = new ArrayList<>();

		for (int i = 2012; i < 2017; i++) {
			years.add(i + 1);
		}

		return years;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllMonths()
	 */
	@Override
	public List<KeyValue> findAllMonths() {
		List<KeyValue> month = new ArrayList<>();

		month.add(new DefaultMapEntry("Jan", 1));
		month.add(new DefaultMapEntry("Feb", 2));
		month.add(new DefaultMapEntry("Mar", 3));
		month.add(new DefaultMapEntry("Apr", 4));
		month.add(new DefaultMapEntry("May", 5));
		month.add(new DefaultMapEntry("Jun", 6));
		month.add(new DefaultMapEntry("Jul", 7));
		month.add(new DefaultMapEntry("Aug", 8));
		month.add(new DefaultMapEntry("Sep", 9));
		month.add(new DefaultMapEntry("Oct", 10));
		month.add(new DefaultMapEntry("Nov", 11));
		month.add(new DefaultMapEntry("Dec", 12));

		return month;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllDays()
	 */
	@Override
	public List<Integer> findAllDays() {
		List<Integer> days = new ArrayList<>();

		for (int i = 0; i < 31; i++) {
			days.add(i + 1);
		}

		return days;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllHours()
	 */
	@Override
	public List<Integer> findAllHours() {
		List<Integer> hours = new ArrayList<>();

		for (int i = 0; i < 12; i++) {
			hours.add(i + 1);
		}

		return hours;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllMinuteIntervals()
	 */
	@Override
	public List<String> findAllMinuteIntervals() {
		return Arrays.asList("00", "15", "30", "45");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arnolds.army.service.ApplicationService#findAllPeriods()
	 */
	@Override
	public List<String> findAllPeriods() {
		return Arrays.asList("pm", "am");
	}

}
