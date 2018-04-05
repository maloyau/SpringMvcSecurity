package com.serhii.springmvc.services.impl;

import com.serhii.springmvc.controllers.AppController;
import com.serhii.springmvc.entities.User;
import com.serhii.springmvc.repositories.UserRepository;
import com.serhii.springmvc.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService{

    private static final Logger logger = LoggerFactory.getLogger(DefaultUserService.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        logger.info("DefaultUserService call method findByUsername(String username)");
        return userRepository.findUserByUsername(username);
    }

    @Override
    public void save(User entity) {
        logger.info("DefaultUserService call method save(User entity)");
        userRepository.save(entity);
    }
}
