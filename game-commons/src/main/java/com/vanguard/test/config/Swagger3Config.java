package com.vanguard.test.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config
{
    @Bean
    public GroupedOpenApi GameApi()
    {
        return GroupedOpenApi.builder().group("Game APIs").pathsToMatch("/**").build();
    }

    @Bean
    public OpenAPI docsOpenApi()
    {
        return new OpenAPI()
                .info(new Info().title("Vanguard Game API")
                        .description("RESTFUL API for Vanguard Game")
                        .version("v1.0"));
    }
}
