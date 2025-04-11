package br.com.codenoir.management_jobs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Management Jobs")
                                .description("API responsible for management to jobs")
                                .version("1")
                )
                .schemaRequirement("jwt_auth", createSecurityScheme());
    }

    private SecurityScheme createSecurityScheme() {
        return new SecurityScheme()
                .name("jwt_auth")
                .description("O token deve ser passado no campo abaixo para que a requisição passa ser feita")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER);

    }

}
