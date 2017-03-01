package com.arnolds.army.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.arnolds.army.model.Player;

@Repository("playerDao")
public class PlayerDaoHibernateImpl extends BaseDaoHibernateImpl<Player, Serializable> {

}
