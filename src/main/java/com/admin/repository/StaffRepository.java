package com.admin.repository;

import com.admin.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Long countById(Integer id);
    @Query("SELECT s FROM Staff s WHERE s.manager.id = ?1")
    List<Staff> findByManagerId(Integer managerId);
}