package com.arnolds.army.model;

import java.util.ArrayList;
import java.util.List;

public class EditableItem {

  private Integer id;

  private List<EditableField> editableFields = new ArrayList<>();

  public EditableItem() {}

  public EditableItem(Integer id, List<EditableField> editableFields) {
    this.id = id;
    this.editableFields = editableFields;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public List<EditableField> getEditableFields() {
    return editableFields;
  }

  public void setEditableFields(List<EditableField> editableFields) {
    this.editableFields = editableFields;
  }

  public static EditableItem item(Integer id, List<EditableField> editableFields) {
    return new EditableItem(id, editableFields);
  }
}
