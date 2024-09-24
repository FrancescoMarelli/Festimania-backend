package org.nevent.festimania.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("api-nevent")
                .packagesToScan("org.nevent.festimania")
                .build();
    }

    @Bean
    public OpenAPI apiInfo() {

        return new OpenAPI()
                .info(new Info().title("FESTIMANIA API - Aplicacion de Festivales")
                        .contact(new Contact()
                                .name("Nevent")
                                .url("nevent.com")
                                .email("soporte.nevent@valencia.org")));

    }
}