package com.work.university;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableCaching
@EnableScheduling
@SpringBootApplication
public class PaperGenerateApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaperGenerateApplication.class, args);
    }

}
