package com.example.demo.controller;


import com.example.demo.entity.*;
import com.example.demo.model.SysUser;
import com.example.demo.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sysUser")
public class SysUserRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public PageInfo<SysUserQueryEntity> list(SysUserPageEntity userQuery) {
        PageHelper.startPage(userQuery.getStart(), userQuery.getLength());
        List<SysUserQueryEntity> userList = userService.listByParams(userQuery.getSysUser());
        PageInfo<SysUserQueryEntity> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }

    @RequestMapping(value = "/roleSelector")
    public ResultEntity<List<SelectorEntity>> roleSelector() {
        List<SelectorEntity> allAvailRoles = userService.getSysRoleSelector();
        return new ResultEntity<>(allAvailRoles);
    }

    @RequestMapping(value = "/addSysUser")
    public ResultEntity<Boolean> addSysUser(SysUserAddEntity sysUser) {
        ResultEntity<Boolean> result = new ResultEntity<>(true);
        userService.insertSysUser(sysUser);
        return result;
    }

    @RequestMapping(value = "/updateSysUser")
    public ResultEntity<Boolean> updateSysUser(SysUserAddEntity sysUser) {
        ResultEntity<Boolean> result = new ResultEntity<>(true);
        userService.updateSysUser(sysUser);
        return result;
    }


}
