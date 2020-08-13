package com.szte.recommender.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		//registry kapcsolodasi pontokat tarol endpointok es a nezetek(view) kozott
		WebMvcConfigurer.super.addViewControllers(registry);
		registry.addViewController("/login").setViewName("auth/login");
		registry.addViewController("/index").setViewName("index");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE) ;
	}

	
	
}
