package com.isd.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication // @Configuration + @EnableAutoConfiguration + @ComponentScan (scan all the components in the same package or below)
@EnableWebMvc
public class GameServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(GameServiceApplication.class, args);
	}
}
