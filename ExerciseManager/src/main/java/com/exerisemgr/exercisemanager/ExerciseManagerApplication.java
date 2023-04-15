package com.exerisemgr.exercisemanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableMongoRepositories
public class ExerciseManagerApplication{

    public static void main(String[] args) {
        SpringApplication.run(ExerciseManagerApplication.class, args);
    }

    @Configuration
    public static class CrossOriginConfig {

        @Bean
        public WebMvcConfigurer corsConfigurer() {
            return new WebMvcConfigurer() {
                @Override
                public void addCorsMappings(CorsRegistry registry) {
                    registry.addMapping("/**")
//                      .allowedOrigins("http://localhost:3000")
                        .allowedOrigins("https://main--idyllic-sundae-dcf08c.netlify.app")
                        .allowedMethods("GET", "PUT", "POST", "DELETE");
                }
            };
        }

    }
}
