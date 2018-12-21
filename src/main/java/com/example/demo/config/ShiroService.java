package com.example.demo.config;



import org.springframework.stereotype.Service;

/**
 * @author lij381
 * @date 2018/12/18 16:28
 * @description
 */
@Service
public class ShiroService {
    public String getPasswordByUsername(String username) {
        switch (username) {
            case "liming":
                return "123";
            case "hanli":
                return "456";
            default:
                return null;
        }
    }
}