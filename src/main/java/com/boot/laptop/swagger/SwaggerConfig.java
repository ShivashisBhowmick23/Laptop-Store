package com.boot.laptop.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("My Laptop Store").description(" Methods of Laptop Controller").version("1.0").license(new License().name("ShivashisBhowmick").url("https://github.com/ShivashisBhowmick23/Laptop-Store/tree/develop")));
    }
}
