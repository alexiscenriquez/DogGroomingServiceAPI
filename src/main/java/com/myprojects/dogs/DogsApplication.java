package com.myprojects.dogs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        // provides meta data on the API service
        info = @Info(
                title = "Book API", // title of the Documentation page
                version = "1.0",	// version of your API
                description = "API that allows you to get, create, update, and delete dogs.",
                contact = @Contact(name = "Cognixia", email = "jumpspartans@cognixia.com", url = "https://www.collabera.com")

        ))
        public class DogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogsApplication.class, args);
    }

}
