package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lij381
 * @date 2018/12/14 15:41
 * @description
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/{page}")
    public String load(@PathVariable String page) {
        if(page.indexOf(".html") != -1) {
            page = page.substring(0, page.indexOf(".html"));
        }
        return page;
    }

}
