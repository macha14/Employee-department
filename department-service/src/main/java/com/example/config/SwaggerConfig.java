package com.example.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* This file will be moved to config library that will be created later */

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("department-v1").pathsToMatch("/**")
				.packagesToScan("com.example.controller").build();
	}
}
