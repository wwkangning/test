package com.example.demo.controller;

import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lij381
 * @date 2018/12/14 15:41
 * @description
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping("/{page}")
    public String load(@PathVariable String page) {
        if(page.indexOf(".html") != -1) {
            page = page.substring(0, page.indexOf(".html"));
        }
        if(page.equals("login")) {
            loginService.logout();
        }
        return page;
    }

}
