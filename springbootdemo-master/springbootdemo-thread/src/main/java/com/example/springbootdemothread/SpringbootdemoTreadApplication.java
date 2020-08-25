package com.example.springbootdemothread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableEurekaClient



@ComponentScan(value = "com.example.*")
public class SpringbootdemoTreadApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootdemoTreadApplication.class, args);
    }

}
