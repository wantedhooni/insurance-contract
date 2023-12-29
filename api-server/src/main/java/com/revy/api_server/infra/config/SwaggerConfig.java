package com.revy.api_server.infra.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Revy on 2023.11.14
 * Swagger 설정
 */
@OpenAPIDefinition(
        info = @Info(title = "보험 계약 API",
                description = "보험 계약 API",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/**"};


        return GroupedOpenApi.builder()
                .group("보험 계약 API")
                .pathsToMatch(paths)
                .build();
    }

}
