package com.arnolds.army;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@Controller
@EnableCaching
public class ArnoldsArmyApplication extends SpringBootServletInitializer {

  @RequestMapping(value = {"/", "home"})
  public String loadHomePage(Model m) {

    return "home";
  }

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(ArnoldsArmyApplication.class);
  }

  public static void main(String[] args) {
    SpringApplication.run(ArnoldsArmyApplication.class, args);
  }
}
