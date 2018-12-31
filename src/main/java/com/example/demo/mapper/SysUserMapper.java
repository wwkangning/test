package com.example.demo.mapper;

import com.example.demo.entity.SysUserQueryEntity;
import com.example.demo.model.SysUser;

import java.util.List;

public interface SysUserMapper {
    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByParams(SysUser sysUser);

    int updateByUserNameSelective(SysUser record);

    List<SysUserQueryEntity> querySysUserList(SysUserQueryEntity query);
}