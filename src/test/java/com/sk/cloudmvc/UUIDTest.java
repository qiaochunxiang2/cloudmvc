package com.sk.cloudmvc;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * @author qiaochunxiang
 * @date 2020/4/1 16:46
 */
public class UUIDTest {

    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Map<String, String> map = new HashMap<>();
        map.put("aaa", "111");
        map.put("aaa","222");
        System.out.println(map);
    }
}
