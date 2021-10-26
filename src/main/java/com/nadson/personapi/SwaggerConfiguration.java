package com.nadson.personapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
public class SwaggerConfiguration {

		@Bean
		public Docket productApi() {
			return new Docket(DocumentationType.SWAGGER_2)
					.select()
					.apis(RequestHandlerSelectors.basePackage("com.nadson.personapi"))
					.paths(regex("/api/v1/people.*"))
					.build()
					.apiInfo(metaInfo());
		}
		
		private ApiInfo metaInfo() {
			ApiInfo apiInfo = new ApiInfo(
					"Person REST API",
					"Person management Spring Boot REST API project",
					"1.0",
					"Terms of Service",
					new Contact("Nadson Lima","https://github.com/NadsonDev","nadsonlima10ns@gmail.com"),
					"Apache License Version 2.0",
					"https://www.apache.org/licensen.html",
					new ArrayList<VendorExtension>()
			);
			
			return apiInfo;
		}

}
