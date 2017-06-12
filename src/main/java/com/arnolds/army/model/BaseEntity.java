package com.arnolds.army.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

import com.arnolds.army.listener.BaseEntityListener;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@MappedSuperclass
@EntityListeners(BaseEntityListener.class)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@JsonIgnoreProperties(value = { "insertDate", "updateDate", "insertUser", "updateUser" })
@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
public class BaseEntity {

	@XmlTransient
	@Column(name = "insert_date")
	private LocalDateTime insertDate;

	@XmlTransient
	@Column(name = "update_date")
	private LocalDateTime updateDate;

	@XmlTransient
	@Column(name = "insert_user")
	private String insertUser;

	@XmlTransient
	@Column(name = "update_user")
	private String updateUser;

	public LocalDateTime getInsertDate() {
		return this.getInsertDate();
	}

	public void setInsertDate(LocalDateTime insertDate) {
		this.insertDate = insertDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public String getInsertUser() {
		return insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	boolean hasDependents() {
		return false;
	}
}
