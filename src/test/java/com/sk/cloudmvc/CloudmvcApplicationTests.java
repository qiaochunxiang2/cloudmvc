package com.sk.cloudmvc;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.jasypt.encryption.StringEncryptor;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CloudmvcApplicationTests {

    @Autowired
    private StringEncryptor stringEncryptor;
    @Test
    void contextLoads() {
        System.out.println(stringEncryptor.encrypt("root"));
    }

}
