package com.serhii.springmvc.services;

import com.serhii.springmvc.entities.User;

public interface UserService extends AbstractService<User>{
    User findByUsername(String username);
}
