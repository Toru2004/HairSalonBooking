package com.admin.repository;

import com.admin.model.User;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
