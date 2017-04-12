package com.arnolds.army.listener;

import java.time.LocalDateTime;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.context.SecurityContextHolder;

import com.arnolds.army.model.BaseEntity;

public class BaseEntityListener {

	@PrePersist
	protected void onCreate(BaseEntity be) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		be.setInsertUser(name);
		be.setInsertDate(LocalDateTime.now());
	}

	@PreUpdate
	protected void onUpdate(BaseEntity be) {
		String name = SecurityContextHolder.getContext().getAuthentication().getName();

		be.setUpdateUser(name);
		be.setUpdateDate(LocalDateTime.now());
	}
}
