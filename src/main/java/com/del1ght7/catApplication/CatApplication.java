package com.del1ght7.catApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableJpaAuditing
@EnableWebMvc
public class CatApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatApplication.class, args);
	}

}
