package com.serhii.springmvc.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = false)
public class User extends AbstractEntity {
    private String username;
    private String password;
    private Set<Role> roles;
    private boolean enabled;
}
