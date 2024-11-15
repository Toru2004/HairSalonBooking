package com.admin.repository;

import com.admin.model.Admin;
import org.springframework.data.repository.CrudRepository;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    public Long countById(Integer id);
}