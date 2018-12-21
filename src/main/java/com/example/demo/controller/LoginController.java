package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lij381
 * @date 2018/12/18 16:31
 * @description
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping("/doLogin")
    public ModelAndView doLogin(String username, String password) {
        ModelAndView mv = new ModelAndView("index");
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            mv.setViewName("error");
            mv.addObject("message", "登录失败，用户名或密码错误");
            logger.error("登录失败，用户名或密码错误");
            return mv;
        }
        logger.info("登录成功");
        return mv;
    }
}
