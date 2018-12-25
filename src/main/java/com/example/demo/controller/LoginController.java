package com.example.demo.controller;

import com.example.demo.constants.ReturnCodeEnum;
import com.example.demo.entity.ResultEntity;
import com.example.demo.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
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

    @Autowired
    private LoginService loginService;

    @RequestMapping("/doLogin")
    public ResultEntity<Boolean> doLogin(String userName, String password, String verifyCode) {
        logger.info("登陆用户名：{}", userName);
        ResultEntity<Boolean> result = loginService.login(userName, password);
        if(!StringUtils.isEmpty(verifyCode)) {
            boolean rs = loginService.verifyAuth(Long.parseLong(verifyCode));
            if(!rs) {
                logger.error("验证OPT验证码，验证失败");
                result = new ResultEntity<>(false);
                result.setError(ReturnCodeEnum.LOGIN_VERIFY_CODE_ERROR);
            }
        }
        logger.info("登陆结果：{}", result);
        return result;
    }

    @RequestMapping("/loginout")
    public void loginout() {
        logger.info("用户登录退出");
        loginService.logout();
    }

    @RequestMapping("/genAuthQRcode")
    public ResultEntity<String> genAuthQRcode(String userName, String password) {
        ResultEntity<String> result = new ResultEntity<>(null);
        String qrcode = loginService.genAuthqrcode(userName);
        result.setData(qrcode);
        return result;
    }


    @RequestMapping("/verifyCode")
    public ResultEntity<Boolean> verifyCode(String userName, String password, Long code) {
        ResultEntity<Boolean> result = new ResultEntity<>(false);
        Boolean rs = loginService.verifyAuth(code);
        if(rs) {
            logger.info("验证OPT验证码，验证成功");
            result.setData(true);
        } else {
            logger.error("验证OPT验证码，验证失败");
            result.setError(ReturnCodeEnum.LOGIN_VERIFY_CODE_ERROR);
        }
        return result;
    }



}
