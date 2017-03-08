package com.arnolds.army.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.arnolds.army.model.Season;

@Repository("seasonDao")
public class SeasonDaoHibernateImpl extends BaseDaoHibernateImpl<Season, Serializable> {

}
