package com.jamongdan.daban;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DabanApplication {

    public static void main(String[] args) {
        SpringApplication.run(DabanApplication.class, args);
    }

}
