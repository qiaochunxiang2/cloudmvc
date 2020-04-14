package com.sk.cloudmvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@MapperScan("com.sk.**.dao")
public class CloudmvcApplication {

    public static void main(String[] args) {
        System.setProperty("jasypt.encryptor.password", "qiaochunxiang");
        SpringApplication.run(CloudmvcApplication.class, args);
        List list = new ArrayList();
    }

}
