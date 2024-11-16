package com.admin.repository;

import com.admin.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // Interface này sẽ tự động cung cấp các phương thức CRUD mà không cần viết mã cụ thể
}
