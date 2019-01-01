package com.example.demo.mapper;

import com.example.demo.model.SysRole;

import java.util.List;

public interface SysRoleMapper {
    int insert(SysRole record);

    int insertSelective(SysRole record);

    List<SysRole> selectByParams(SysRole record);
}