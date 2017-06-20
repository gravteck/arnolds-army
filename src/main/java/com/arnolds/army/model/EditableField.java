package com.arnolds.army.model;

public class EditableField {

  private String label;

  private String field;

  private String data;

  private String placeholder;

  private boolean password;

  private boolean email;

  public EditableField() {}

  public EditableField(String label, String field, String data, String placeholder) {
    this.label = label;
    this.field = field;
    this.data = data;
    this.placeholder = placeholder;
  }

  public EditableField(String label, String field, String data, String placeholder,
      boolean password, boolean email) {
    this.label = label;
    this.field = field;
    this.data = data;
    this.placeholder = placeholder;
    this.password = password;
    this.email = email;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  public String getField() {
    return field;
  }

  public void setField(String field) {
    this.field = field;
  }

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getPlaceholder() {
    return placeholder;
  }

  public void setPlaceholder(String placeholder) {
    this.placeholder = placeholder;
  }

  public boolean isPassword() {
    return password;
  }

  public void setPassword(boolean password) {
    this.password = password;
  }

  public boolean isEmail() {
    return email;
  }

  public void setEmail(boolean email) {
    this.email = email;
  }

  public static EditableField field(String label, String field, String data) {
    return new EditableField(label, field, data, null);
  }

  public static EditableField field(String label, String field, String data, String placeholder) {
    return new EditableField(label, field, data, placeholder);
  }

  public static EditableField field(String label, String field, String data, String placeholder,
      boolean password, boolean email) {
    return new EditableField(label, field, data, placeholder, password, email);
  }

  public static EditableField password(String label, String field, String data) {
    return new EditableField(label, field, data, "********", true, false);
  }

  public static EditableField email(String label, String field, String data) {
    return new EditableField(label, field, data, "your.username@email.com", false, true);
  }

}
