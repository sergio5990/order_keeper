package com.sashaq.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.sashaq")
public class OrderKeeperApplication {

    public static void main(String... args) {
        SpringApplication.run(OrderKeeperApplication.class, args);
    }
}
