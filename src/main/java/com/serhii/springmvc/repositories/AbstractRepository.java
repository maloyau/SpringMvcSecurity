package com.serhii.springmvc.repositories;

import com.serhii.springmvc.entities.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbstractRepository<T extends AbstractEntity> extends JpaRepository<T, Long> {
}
