package com.arnolds.army.util;

import org.springframework.beans.BeanUtils;

import com.arnolds.army.model.BaseEntity;

public class ModelUtils {

  protected static String[] ignoreProperties() {
    return new String[] {"updateUser", "updateDate", "insertUser", "insertDate"};
  }

  public static void merge(BaseEntity source, BaseEntity target) {
    BeanUtils.copyProperties(source, target, ignoreProperties());
  }
}
