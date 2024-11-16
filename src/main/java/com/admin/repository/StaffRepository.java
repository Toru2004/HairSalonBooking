package com.admin.repository;

import com.admin.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Long countById(Integer id);
}
