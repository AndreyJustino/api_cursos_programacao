package com.andrey.rocketseat.desafio.api_curso_programacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    private SecurityScheme securityScheme(){
        return new SecurityScheme()
                .name("JWT_Auth")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT");
    }


    public OpenAPI openAPI(){
        return new OpenAPI().info(new Info()
                .title("Cursos de Programação")
                .version("1")
                .description("Api de Cursos de Programação")
        )
        .schemaRequirement("JWT_Auth", securityScheme());
    }
}
