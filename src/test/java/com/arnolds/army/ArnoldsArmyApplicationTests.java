package com.arnolds.army;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arnolds.army.dao.TeamDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArnoldsArmyApplicationTests {

	@Autowired
	TeamDao teamDao;

	@Test
	public void testFindAllTeams() {
		Assert.assertEquals(4, teamDao.findAll().size());
	}

	@Test
	public void testFindTeam() {
		Assert.assertEquals(1, teamDao.find(1).getId().intValue());
	}
}
