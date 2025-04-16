package com.bys.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class LoginRegisterServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoginRegisterServerApplication.class, args);
    }

}

