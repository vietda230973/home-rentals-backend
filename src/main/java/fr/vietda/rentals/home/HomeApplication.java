package fr.vietda.rentals.home;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@OpenAPIDefinition(
		info = @Info(
				title = "Student API",
				version = "1.0",
				description = "API documentation for managing students"
		)
)
@SpringBootApplication
public class HomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeApplication.class, args);
	}
	
//	@Bean
//	public Docket productApi() {
//		return new Docket(DocumentationType.SWAGGER_2).select()
//				.apis(RequestHandlerSelectors.basePackage("com.tutorialspoint.swaggerdemo")).build();
//	}

}
