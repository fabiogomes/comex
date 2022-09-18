package br.com.alura.comex.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

	@Bean
	public Docket comexApi() {
		return new Docket(DocumentationType.SWAGGER_2).enable(true).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.alura.comex")).paths(PathSelectors.ant("/**"))
				.build();
	}
}