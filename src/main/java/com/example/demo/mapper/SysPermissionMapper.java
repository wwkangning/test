package com.example.demo.mapper;

import com.example.demo.model.SysPermission;

public interface SysPermissionMapper {
    int insert(SysPermission record);

    int insertSelective(SysPermission record);
}