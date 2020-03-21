package com.sk.cloudmvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sk.**.dao")
public class CloudmvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudmvcApplication.class, args);
    }

}
