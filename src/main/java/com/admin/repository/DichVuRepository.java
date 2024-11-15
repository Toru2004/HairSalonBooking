package com.admin.repository;

import com.admin.model.DichVu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DichVuRepository extends JpaRepository<DichVu, Integer> {
    // Interface này sẽ tự động cung cấp các phương thức CRUD mà không cần viết mã cụ thể
}
