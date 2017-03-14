package com.arnolds.army.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.arnolds.army.model.Game;
import com.arnolds.army.model.StatisticalYear;

@Repository("statisticalYearDao")
public class StatisticalYearDaoHibernateImpl extends BaseDaoHibernateImpl<StatisticalYear, Serializable> {

}
