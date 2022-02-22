package com.example.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/* This file will be moved to config library that will be created later */

@Configuration
public class SwaggerConfig {

	@Bean
	public GroupedOpenApi api() {
		return GroupedOpenApi.builder().group("employee-v1").pathsToMatch("/**")
				.packagesToScan("com.example.controller").build();
	}

	@Bean
	public OpenAPI springEmployeeOpenAPI() {
		return new OpenAPI()
				.info(new Info().title("springEmployee API").description("Spring employee sample application")
						.version("v0.0.1").license(new License().name("Apache 2.0").url("http://springdoc.org")))
				.externalDocs(new ExternalDocumentation().description("springEmployee Wiki Documentation")
						.url("https://springEmployee.wiki.github.org/docs"));
	}
}
