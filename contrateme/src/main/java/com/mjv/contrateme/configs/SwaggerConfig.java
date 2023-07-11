package com.mjv.contrateme.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(info()
                        .contact(contact()));
    }

    private Info info() {
        return new Info()
                .title("Contrate-me")
                .description("API de Armazenamento de Dados Pessoais e Profissionais")
                .version("1.0.0");
    }

    private Contact contact() {
        return new Contact()
                .name("Contrate-me")
                .email("contato@contrateme.com")
                .url("https://github.com/JonathanZapotosczny/mjv-java-school-grupo09-projeto-final");
    }
}