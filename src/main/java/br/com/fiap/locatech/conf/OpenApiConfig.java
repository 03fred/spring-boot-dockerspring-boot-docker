package br.com.fiap.locatech.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI localtech() {
		return new OpenAPI().info(
				new Info().title("Loca tech API")
				 		.description("Projeto desenvolvido durante o curso de spring mvc")
						.version("v0.0.1").license(new License().name("Apache 2.0")
						.url("https://github.com/03fred")));
	}

}
