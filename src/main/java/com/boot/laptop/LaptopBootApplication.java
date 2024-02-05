package com.boot.laptop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class LaptopBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaptopBootApplication.class, args);


    }
}
