package com.example.demo.mapper;

import com.example.demo.model.SysRole;

public interface SysRoleMapper {
    int insert(SysRole record);

    int insertSelective(SysRole record);
}