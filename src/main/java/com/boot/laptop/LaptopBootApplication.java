package com.boot.laptop;

import com.boot.laptop.model.Laptop;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan
public class LaptopBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaptopBootApplication.class, args);



    }
}
