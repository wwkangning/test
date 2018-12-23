package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    public User findByUserName(String userName) {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("d3c59d25033dbf980d29554025c23a75");
        user.setSalt("8d78869f470951332959580424d4bf4f");
        return user;
    }

}
