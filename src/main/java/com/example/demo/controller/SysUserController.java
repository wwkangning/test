package com.example.demo.controller;

import com.example.demo.entity.ResultEntity;
import com.example.demo.entity.SelectorEntity;
import com.example.demo.entity.SysUserEntity;
import com.example.demo.model.SysUser;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author lij381
 * @date 2019/1/1 16:35
 * @description
 */
@Controller
@RequestMapping("/sysUser")
public class SysUserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getSysUser")
    public ModelAndView getUser(Integer userId) {
        ModelAndView mv = new ModelAndView("admin-add");
        SysUserEntity user = userService.findByUserId(userId);
        List<SelectorEntity> sysRoles = userService.getSysRoleSelector();
        mv.addObject("sysRoles", sysRoles);
        mv.addObject("sysUser", user);
        return mv;
    }
}
