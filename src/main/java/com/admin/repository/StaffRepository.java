package com.admin.repository;

import com.admin.model.Staff;
import org.springframework.data.repository.CrudRepository;

public interface StaffRepository extends CrudRepository<Staff, Integer> {
    Long countById(Integer id); // Đếm số lượng bản ghi theo ID
}
