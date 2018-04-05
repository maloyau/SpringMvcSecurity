package com.serhii.springmvc.repositories;

import com.serhii.springmvc.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends AbstractRepository<User> {
    User findUserByUsername(String username);
}
