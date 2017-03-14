package com.arnolds.army.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import com.trg.search.jpa.JPAAnnotationMetadataUtil;
import com.trg.search.jpa.JPASearchProcessor;

@EnableWebMvc
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = { "com.arnolds.army" })
public class WebConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	ApplicationContext applicationContext;

	@Bean
	public JPASearchProcessor getSearchProcessor() {
		JPASearchProcessor searchProcessor = new JPASearchProcessor(getMetaDataUtil());

		return searchProcessor;
	}

	@Bean
	public JPAAnnotationMetadataUtil getMetaDataUtil() {
		return new JPAAnnotationMetadataUtil();
	}

	@Bean("viewResolver")
	public UrlBasedViewResolver getAngularViewResolver() {
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();

		resolver.setPrefix("/static/");
		resolver.setSuffix(".html");
		resolver.setViewNames("html/*", "html/**/*");
		resolver.setCache(false);
		resolver.setViewClass(InternalResourceView.class);
		resolver.setApplicationContext(applicationContext);
		resolver.setOrder(Ordered.HIGHEST_PRECEDENCE);

		return resolver;
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(getAngularViewResolver());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/html/**").addResourceLocations("classpath:/static/html/");
		registry.addResourceHandler("/static/js/**").addResourceLocations("classpath:/static/js/");
		registry.addResourceHandler("/static/css/**").addResourceLocations("classpath:/static/css/");

		super.addResourceHandlers(registry);
	}

	@Bean
	public SpringSecurityDialect securityDialect() {
		return new SpringSecurityDialect();
	}

}
