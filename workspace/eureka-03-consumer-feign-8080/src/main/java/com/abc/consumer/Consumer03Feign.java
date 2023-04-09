package com.abc.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class Consumer03Feign {

    public static void main(String[] args) {
        SpringApplication.run(Consumer03Feign.class, args);
    }

}











