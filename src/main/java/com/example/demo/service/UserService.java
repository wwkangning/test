package com.example.demo.service;

import com.example.demo.constants.Constants;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 通过用户名查询用户信息
     * @param userName
     * @return
     */
    public SysUser findByUserName(String userName) {
        SysUser sysUser = new SysUser();
        sysUser.setUserName(userName);
        SysUser user = sysUserMapper.selectByParams(sysUser);
        return user;
    }

    /**
     * 用户绑定OTP
     * @param userName
     */
    public void bindOtp(String userName) {
        SysUser record = new SysUser();
        record.setUserName(userName);
        record.setOtp(Constants.OTP_BINDED);
        sysUserMapper.updateByUserNameSelective(record);
    }

}
