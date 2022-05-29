package org.ennea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("org.ennea")
public class EnneaSolutionsAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnneaSolutionsAssignmentApplication.class, args);
	}

}
