package com.arnolds.army.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.arnolds.army.model.Game;

@Repository("gameDao")
public class GameDaoHibernateImpl extends BaseDaoHibernateImpl<Game, Serializable> {

}
