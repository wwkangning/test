package com.example.demo.controller;

import com.example.demo.constants.Constants;
import com.example.demo.constants.ReturnCodeEnum;
import com.example.demo.entity.LoginEntity;
import com.example.demo.entity.ResultEntity;
import com.example.demo.model.SysUser;
import com.example.demo.service.LoginService;
import com.example.demo.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

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

    @Autowired
    private UserService userService;

    /**
     * 用户登陆验证
     * @param loginUser
     * @return
     */
    @RequestMapping("/doLogin")
    public ResultEntity<Boolean> doLogin(@Valid LoginEntity loginUser) {
        logger.info("登陆用户名：{}", loginUser.getUserName());
        ResultEntity<Boolean> result = loginService.login(loginUser.getUserName(), loginUser.getPassword());
        if(!StringUtils.isEmpty(loginUser.getVerifyCode())) {
            boolean rs = loginService.verifyAuth(Long.parseLong(loginUser.getVerifyCode()));
            if(!rs) {
                logger.error("验证OTP验证码，验证失败");
                result = new ResultEntity<>(false);
                result.setError(ReturnCodeEnum.LOGIN_VERIFY_CODE_ERROR);
            } else {
                userService.bindOtp(loginUser.getUserName());
            }
        } else {
            logger.error("OTP验证码为空");
            result = new ResultEntity<>(false);
            result.setError(ReturnCodeEnum.LOGIN_VERIFY_CODE_EMPTY);
        }
        logger.info("登陆结果：{}", result);
        return result;
    }

    /**
     * 用户登出
     * @return
     */
    @RequestMapping("/loginout")
    public String loginout() {
        logger.info("用户登录退出");
        loginService.logout();
        return "loginout";
    }

    /**
     * 获取OTP绑定二维码
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/genAuthQRcode")
    public ResultEntity<String> genAuthQRcode(String userName, String password) {
        ResultEntity<String> result = new ResultEntity<>(null);
        ResultEntity<Boolean> rs = loginService.login(userName, password);
        SysUser user = (SysUser)SecurityUtils.getSubject().getPrincipal();
        if(rs.getData() && user.getOtp() == Constants.OTP_UNBIND) {
            String qrcode = loginService.genAuthqrcode(userName);
            result.setData(qrcode);
        } else {
            result.setCode(rs.getCode());
            result.setDesc(rs.getDesc());
        }
        return result;
    }

}
