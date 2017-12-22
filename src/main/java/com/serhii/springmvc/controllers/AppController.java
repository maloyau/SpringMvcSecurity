package com.serhii.springmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home() {
        return "home";
    }
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
