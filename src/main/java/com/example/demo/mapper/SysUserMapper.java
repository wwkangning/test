package com.example.demo.mapper;

import com.example.demo.model.SysUser;

public interface SysUserMapper {
    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByParams(SysUser sysUser);

    int updateByUserNameSelective(SysUser record);
}