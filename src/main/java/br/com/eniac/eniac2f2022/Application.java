package br.com.eniac.eniac2f2022;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.github.javafaker.Faker;

@SpringBootApplication
public class Application {
    
    @Bean
    Faker faker() {
    	return new Faker();
    }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
