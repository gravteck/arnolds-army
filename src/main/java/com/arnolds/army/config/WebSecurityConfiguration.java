package com.arnolds.army.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

//@Configuration
//@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	//@Override
	protected void configure(HttpSecurity http) throws Exception {

		//@formatter:off
//		http
//			.authorizeRequests()
//				.antMatchers("/admin/**").hasRole("ADMIN")
//				.antMatchers("/**").permitAll()
//				.and()
//			.formLogin()
//				.loginPage("/admin/login")
//				.permitAll()
//				.and()
//			.logout().logoutUrl("/admin/logout")
//				.permitAll();
		//@formatter:on
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN");
	}

	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

}
