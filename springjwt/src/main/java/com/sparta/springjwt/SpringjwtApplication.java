package com.sparta.springjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class SpringjwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringjwtApplication.class, args);
    }

}
