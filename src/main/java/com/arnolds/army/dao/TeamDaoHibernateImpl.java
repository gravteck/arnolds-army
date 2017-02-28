package com.arnolds.army.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.arnolds.army.model.Team;
import com.trg.dao.jpa.GenericDAOImpl;

@Repository
public class TeamDaoHibernateImpl extends GenericDAOImpl<Team, Serializable> implements TeamDao {

}
