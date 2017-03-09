package com.arnolds.army.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "team")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Team extends BaseEntity {

	@Id
	@SequenceGenerator(name = "team_id_seq", sequenceName = "team_id_seq", allocationSize = 1, initialValue = 10)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_id_seq")
	@Column(name = "id")
	private Integer id;

	@Column(name = "name")
	private String name;

	public static final Integer ID_ARNOLDS = 1;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
