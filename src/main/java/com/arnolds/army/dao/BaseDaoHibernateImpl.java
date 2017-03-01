package com.arnolds.army.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.arnolds.army.model.BaseEntity;
import com.trg.dao.jpa.GenericDAOImpl;

public abstract class BaseDaoHibernateImpl<T extends BaseEntity, ID extends Serializable>
		extends GenericDAOImpl<T, Serializable> {

	@Autowired
	@Override
	public void setEntityManager(EntityManager entityManager) {
		super.setEntityManager(entityManager);
	}

}
