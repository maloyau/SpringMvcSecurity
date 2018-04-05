package com.serhii.springmvc.controllers;

import com.serhii.springmvc.entities.Role;
import com.serhii.springmvc.entities.User;
import com.serhii.springmvc.repositories.UserRepository;
import com.serhii.springmvc.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashSet;
import java.util.Set;


@Controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    private UserRepository userRepository;

    private static void init() {

    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
    public String home() {
        logger.info("AppController call method home()");
        return "home";
    }
    @RequestMapping("/admin")
    public String admin() {
        logger.info("AppController call method admin()");
        return "admin";
    }
}
