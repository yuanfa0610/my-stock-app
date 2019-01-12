package com.rf.privjoy.myStock.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.rf.privjoy.myStock") 
public class MyStockConfiguration {

	@Bean
	public ViewResolver internalResourceViewResolver() {
	    InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
	    internalResourceViewResolver.setPrefix("/WEB-INF/view/");
	    internalResourceViewResolver.setSuffix(".jsp");
	    return internalResourceViewResolver;
	}

}
