package com.arnolds.army.service;

import org.apache.commons.collections.CollectionUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationServiceTest {

	@Autowired
	private ApplicationService applicationService;

	@Test
	public void testFindAllTeams() {
		Assert.assertEquals(4, applicationService.findAllTeams().size());
	}

	@Test
	public void testFindAllPlayers() {
		Assert.assertEquals(4, applicationService.findAllPlayers().size());
	}

	@Test
	public void testFindAllSeasons() {
		Assert.assertTrue(CollectionUtils.isNotEmpty(applicationService.findAllSeasons()));
	}
}
