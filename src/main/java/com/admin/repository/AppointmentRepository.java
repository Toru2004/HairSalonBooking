package com.admin.repository;

import com.admin.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    // Interface này sẽ tự động cung cấp các phương thức CRUD mà không cần viết mã cụ thể
    @Query(value = "SELECT SUM(total_price) FROM appointment WHERE date BETWEEN :startDate AND :endDate", nativeQuery = true)
    Double findTotalRevenueByTimePeriodNative(@Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate);

    @Query(value = "SELECT service_id, SUM(total_price) FROM appointment WHERE date BETWEEN :startDate AND :endDate GROUP BY service_id",
            nativeQuery = true)
    List<Object[]> findRevenueByServiceByTimePeriodNative(@Param("startDate") LocalDateTime startDate,
                                                          @Param("endDate") LocalDateTime endDate);
}


