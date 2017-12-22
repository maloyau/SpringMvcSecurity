package com.serhii.springmvc.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends AbstractEntity {
    private String name;
}
