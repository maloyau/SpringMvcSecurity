package com.serhii.springmvc.services;

import com.serhii.springmvc.entities.AbstractEntity;

public interface AbstractService<T extends AbstractEntity> {
    void save(T entity);
}
