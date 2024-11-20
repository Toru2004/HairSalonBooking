package com.admin.repository;

import com.admin.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
    Long countById(Integer id); // Đảm bảo phương thức này được định nghĩa đúng
}
