package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author lij381
 * @date 2018/12/14 15:41
 * @description
 */
@Controller
@RequestMapping("/error")
public class ErrorController {

    @RequestMapping("/errorMsg")
    public ModelAndView load(String errorMsg) {
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("message", errorMsg);
        return mv;
    }

}
