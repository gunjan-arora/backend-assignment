package com.springboot.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan; 

@SpringBootApplication 
@ComponentScan(basePackages= {"com.springboot.rest"})
public class SpringBootCSVXMLApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCSVXMLApplication.class, args);
    }
}
