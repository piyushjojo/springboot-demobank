package com.demo.cards;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info= @Info(
				title = "Cards microservice rest api documentation",
				description = "Demo Bank Cards microservice REST Api documentation",
				version = "v1",
				contact = @Contact(
						name = "Piyush Joshi",
						email = "impiyushjoshi@gmail.com",
						url = "https://www.linkedin.com/in/piyush-joshi-"
				),
				license = @License(
						name = "Apache2.0",
						url = "https://www.linkedin.com/in/piyush-joshi-"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "Demo Bank Cards microservice REST Api documentation",
				url = "https://www.linkedin.com/in/piyush-joshi-"
		)
)
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
