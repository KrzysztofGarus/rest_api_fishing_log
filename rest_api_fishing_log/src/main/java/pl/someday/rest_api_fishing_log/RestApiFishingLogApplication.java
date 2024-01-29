package pl.someday.rest_api_fishing_log;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition (info = @Info(title="Fishing Log API", description="API for fishing log application", version="1.0"))
public class RestApiFishingLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiFishingLogApplication.class, args);
	}

}
