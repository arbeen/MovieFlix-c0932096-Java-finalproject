package org.lambton.csd_4464.week12;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Week12Application {

    public static void main(String[] args) {
        SpringApplication.run(Week12Application.class, args);
    }

}
