package com.boot.laptop.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Define global headers
        Components components = new Components();
        components.addHeaders("Custom-Header", new Header().description("Custom Header Description"));

        return new OpenAPI()
                .components(components)
                .info(new Info()
                        .title("My Laptop Store")
                        .description("Methods of Laptop Controller")
                        .version("1.0")
                        .license(new License()
                                .name("ShivashisBhowmick")
                                .url("https://github.com/ShivashisBhowmick23/Laptop-Store/tree/develop")));
    }
}
