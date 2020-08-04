package com.eatza.delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.eatza.delivery.config.JwtFilter;

@SpringBootApplication
public class DeliveryserviceApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(DeliveryserviceApplication.class, args);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
	public FilterRegistrationBean jwtFilterBean() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new JwtFilter());
		registrationBean.addUrlPatterns("/delivery/*");

		return registrationBean;
	}
	
	 
	
}