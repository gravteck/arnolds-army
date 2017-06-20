package com.arnolds.army;

import java.io.Serializable;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.arnolds.army.model.Team;
import com.trg.dao.jpa.GenericDAO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArnoldsArmyApplicationTests {

  @Autowired
  private GenericDAO<Team, Serializable> teamDao;

  @Test
  public void testFindAllTeams() {
    Assert.assertEquals(4, teamDao.findAll().size());
  }

  @Test
  public void testFindTeam() {
    Assert.assertEquals(1, teamDao.find(1).getId().intValue());
  }
}
