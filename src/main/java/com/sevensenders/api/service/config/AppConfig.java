package com.sevensenders.api.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.sevensenders.api.service.Service;
import com.sevensenders.api.service.helper.FeedHelper;
import com.sevensenders.api.service.repo.ServiceRepository;
import com.sevensenders.api.service.repo.ServiceRepositoryImpl;

@Configuration
public class AppConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public ServiceRepository serviceRepository() {
		return new ServiceRepositoryImpl();
	}
	
	@Bean
	public FeedHelper feedHelper() {
		return new FeedHelper();
	}
}
