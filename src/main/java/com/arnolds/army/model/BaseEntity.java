package com.arnolds.army.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class BaseEntity {

	private List<ReportingField> reportingFields = new ArrayList<>();

	public List<ReportingField> getReportingFields() {
		return reportingFields;
	}
}
