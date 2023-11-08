package com.devgym.gymmanager.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@OpenAPIDefinition(
        info = @Info(title = "Gym-Manager API 명세서",
                description = "헬스장 관리 어플 서비스 API 명세서",
                version = "v1"))
@Configuration
public class SwaggerConfig {
//    http://localhost:8080/swagger-ui/index.html#/
    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/order/**", "/member/**", "/trainer/**", "/review/**", "/item/**"};

        return GroupedOpenApi.builder()
                .group("COUPLE API v1")
                .pathsToMatch(paths)
                .build();
    }
}