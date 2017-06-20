package com.arnolds.army.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArnoldsErrorController implements ErrorController {

  @Override
  public String getErrorPath() {
    return "/error";
  }

  @RequestMapping("error")
  public String handleError(Model m, Exception e) {

    m.addAttribute("stackTrace", e.getStackTrace());
    return "error";
  }
}
