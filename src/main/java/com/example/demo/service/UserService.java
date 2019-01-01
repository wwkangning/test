package com.example.demo.service;

import com.example.demo.constants.Constants;
import com.example.demo.entity.SelectorEntity;
import com.example.demo.entity.SysUserAddEntity;
import com.example.demo.entity.SysUserEntity;
import com.example.demo.entity.SysUserQueryEntity;
import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.mapper.SysUserRoleMapper;
import com.example.demo.model.SysRole;
import com.example.demo.model.SysUser;
import com.example.demo.model.SysUserRole;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class UserService {

    @Autowired
    SysUserMapper sysUserMapper;

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

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

    /**
     * 生成用户OTP动态验证码KEY
     * @param userName
     * @param secretKey
     */
    public void genOtpSecretKey(String userName, String secretKey) {
        SysUser record = new SysUser();
        record.setUserName(userName);
        record.setSecretKey(secretKey);
        sysUserMapper.updateByUserNameSelective(record);
    }

    /**
     * 查询用户列表
     * @param query
     * @return
     */
    public List<SysUserQueryEntity> listByParams(SysUserQueryEntity query) {
        return sysUserMapper.querySysUserList(query);
    }

    /**
     * 获取角色下拉列表
     * @return
     */
    public List<SelectorEntity> getSysRoleSelector() {
        SysRole record = new SysRole();
        record.setAvailable(Constants.VALID);
        List<SysRole> sysRoles = sysRoleMapper.selectByParams(record);
        if(!CollectionUtils.isEmpty(sysRoles)) {
            List<SelectorEntity> list = new ArrayList<>(sysRoles.size());
            for (SysRole sysRole : sysRoles) {
                list.add(new SelectorEntity(sysRole.getRoleId() + "", sysRole.getDescription()));
            }
            return list;
        }
        return null;
    }

    /**
     * 用户信息入库
     */
    public void insertSysUser(SysUserAddEntity sysUser) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(sysUser, user);

        //生成盐（部分，需要存入数据库中）
        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        user.setSalt(random);
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String result = new Md5Hash(user.getPassword(), user.getUserName() + random,2).toString();
        user.setPassword(result);
        user.setState(Constants.USER_STATE_1);
        user.setCreateTime(new Date());

        sysUserMapper.insertSelective(user);
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(sysUser.getRoleId());
        sysUserRoleMapper.insertSelective(userRole);
    }

    /**
     * 通过主键查询用户信息
     * @param userId
     * @return
     */
    public SysUserEntity findByUserId(Integer userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        SysUserEntity user = sysUserMapper.selectByUserId(userId);
        return user;
    }

    /**
     * 用户信息修改
     */
    public void updateSysUser(SysUserAddEntity sysUser) {
        SysUser user = new SysUser();
        BeanUtils.copyProperties(sysUser, user);

        //生成盐（部分，需要存入数据库中）
        String random=new SecureRandomNumberGenerator().nextBytes().toHex();
        user.setSalt(random);
        //将原始密码加盐（上面生成的盐），并且用md5算法加密三次，将最后结果存入数据库中
        String result = new Md5Hash(user.getPassword(), user.getUserName() + random,2).toString();
        user.setPassword(result);
        user.setState(Constants.USER_STATE_1);

        sysUserMapper.updateSelective(user);
        sysUserRoleMapper.deleteByUserId(user.getUserId());
        SysUserRole userRole = new SysUserRole();
        userRole.setUserId(user.getUserId());
        userRole.setRoleId(sysUser.getRoleId());
        sysUserRoleMapper.insertSelective(userRole);
    }

}
