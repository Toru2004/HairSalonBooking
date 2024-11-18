package com.admin.repository;
import com.admin.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
    // Các phương thức tìm kiếm nhân viên có thể được định nghĩa ở đây
}
