package com.example.demo.service;

import com.example.demo.auth.GoogleAuthenticator;
import com.example.demo.constants.ReturnCodeEnum;
import com.example.demo.entity.ResultEntity;
import com.example.demo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String AUTH_CODE_SECRET = "IBWLAQROY5XQJBJT";

    /**
     * 登陆验证
     * @param userName
     * @param password
     * @return
     */
    public ResultEntity<Boolean> login(String userName, String password) {
        ResultEntity<Boolean> loginResult = new ResultEntity(false);
        if(userName==null || userName.isEmpty()) {
            loginResult.setError(ReturnCodeEnum.LOGIN_USER_NAME_EMPTY);
            return loginResult;
        }
        String msg="";
        // 1、获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();
        // 3、将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        // 4、认证
        try {
            currentUser.login(token);// 传到MyAuthorizingRealm类中的方法进行认证
            Session session = currentUser.getSession();
            session.setAttribute("userName", userName);
            loginResult.setData(true);
            return loginResult;
        } catch (UnknownAccountException e) {
            logger.error("用户登录异失败，账号不存在");
            loginResult.setError(ReturnCodeEnum.LOGIN_USER_NOT_EXIST);
        } catch (IncorrectCredentialsException e) {
            logger.error("用户登录失败，密码不正确");
            loginResult.setError(ReturnCodeEnum.LOGIN_USER_PWD_ERROR);
        } catch (AuthenticationException e) {
            logger.error("用户登录失败,用户验证失败", e);
            loginResult.setError(ReturnCodeEnum.LOGIN_USER_VALIDATE_FAIL);
        }
        return loginResult;
    }

    /**
     * 退出登录
     */
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        if(user != null) {
            logger.info("用户：{}，退出登录", user.getName());
        }
        subject.logout();
    }

    public String genAuthqrcode(String name) {
        String qrBarcode = GoogleAuthenticator.getQRBarcode(name, AUTH_CODE_SECRET);
        return qrBarcode;
    }

    public boolean verifyAuth(long code) {
        long t = System.currentTimeMillis();
        GoogleAuthenticator ga = new GoogleAuthenticator();
        ga.setWindowSize(1);
        boolean r = ga.check_code(AUTH_CODE_SECRET, code, t);
        return r;
    }

}
