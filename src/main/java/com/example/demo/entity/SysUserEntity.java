package com.example.demo.entity;

import com.example.demo.model.SysUser;

/**
 * @author lij381
 * @date 2019/1/1 12:44
 * @description
 */
public class SysUserEntity extends SysUser {
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
