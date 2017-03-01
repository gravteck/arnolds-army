package com.arnolds.army.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.arnolds.army.model.Team;

@Repository
public class TeamDaoHibernateImpl extends BaseDaoHibernateImpl<Team, Serializable> implements TeamDao {

}
