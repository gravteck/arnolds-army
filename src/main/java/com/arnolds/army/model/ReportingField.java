package com.arnolds.army.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

public class ReportingField {

  private String label;

  private String value;

  private String href;

  private ReportingFieldType type;

  private List<ReportingField> group = new ArrayList<>();

  public ReportingField(String label, String value) {
    this.label = label;
    this.value = value;
    this.type = ReportingFieldType.DEFAULT;
  }

  public ReportingField(String label, Integer value) {
    this.label = label;
    this.value = value.toString();
    this.type = ReportingFieldType.DEFAULT;
  }

  public ReportingField(String label, String value, String href, ReportingFieldType type) {
    this.label = label;
    this.value = value;
    this.href = href;
    this.type = type;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  public String getHref() {
    return href;
  }

  public void setHref(String href) {
    this.href = href;
  }

  public ReportingFieldType getType() {
    return type;
  }

  public void setType(ReportingFieldType type) {
    this.type = type;
  }

  public List<ReportingField> getGroup() {
    return group;
  }

  public static ReportingField text(String key, String value) {
    return new ReportingField(key, value);
  }

  public static ReportingField text(String key, Integer value) {
    return new ReportingField(key, value);
  }

  public static ReportingField link(String key, String value, String href) {
    return new ReportingField(key, value, href, ReportingFieldType.LINK);
  }

  public static ReportingField view(String href) {
    return new ReportingField(null, null, href, ReportingFieldType.VIEW);
  }

  public static ReportingField edit(String href) {
    return new ReportingField(null, null, href, ReportingFieldType.EDIT);
  }

  public static ReportingField delete(Integer id) {
    return new ReportingField(null, null, id.toString(), ReportingFieldType.DELETE);
  }

  public static ReportingField group(ReportingField... rf) {
    ReportingField reportingField = new ReportingField("", "");
    reportingField.setType(ReportingFieldType.GROUP);
    reportingField.getGroup().addAll(Arrays.asList(rf));

    return reportingField;
  }

  public static ReportingField reverseGroup(ReportingField... rf) {
    CollectionUtils.reverseArray(rf);
    return group(rf);
  }

  public static ReportingField spacer() {
    ReportingField reportingField = new ReportingField("", "");
    reportingField.setType(ReportingFieldType.SPACER);

    return reportingField;
  }
}
