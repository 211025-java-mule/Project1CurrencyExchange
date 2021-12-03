package com.example.project1currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Configuration
@EnableScheduling
public class Project1CurrencyApplication {

    public static void main(String[] args)  {
        SpringApplication.run(Project1CurrencyApplication.class, args);

    }

}
