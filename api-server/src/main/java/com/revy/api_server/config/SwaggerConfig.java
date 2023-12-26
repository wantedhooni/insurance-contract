package com.revy.api_server.config;


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
        info = @Info(title = "블로그 검색 API",
                description = "블로그 검색 API",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/**"};


        return GroupedOpenApi.builder()
                .group("블로그 검색 API")
                .pathsToMatch(paths)
                .build();
    }

}
