package br.com.marcosilva.gestao_de_vagas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(
	info = @Info(title = "position management", description = "API - responsible for position management", version = "1")
)
public class ManagementJobPositionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagementJobPositionApplication.class, args);
	}
}
