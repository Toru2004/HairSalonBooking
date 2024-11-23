package com.admin.repository;

import com.admin.model.Care;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareRepository extends JpaRepository<Care, Integer> {
    // Interface này sẽ tự động cung cấp các phương thức CRUD mà không cần viết mã cụ thể
    List<Care> findByNameContainingIgnoreCase(String name);
}
