package com.admin.repository;
import com.admin.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh tại đây nếu cần.
}
