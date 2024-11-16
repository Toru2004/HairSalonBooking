package com.admin.repository;

import com.admin.model.Manage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManageRepository extends JpaRepository<Manage, Integer> {
    Long countById(Integer id);
}
