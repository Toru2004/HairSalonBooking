package com.admin.repository;

import com.admin.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {


    // Tổng doanh thu trong khoảng thời gian (Native Query)
    @Query(value = "SELECT SUM(total_price) FROM appointments WHERE appointment_date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    Double findTotalRevenueByTimePeriodNative(@Param("startDate") LocalDateTime startDate,
                                              @Param("endDate") LocalDateTime endDate);

    // Doanh thu theo từng dịch vụ trong khoảng thời gian (Native Query)
    @Query(value = "SELECT c.service_id, SUM(a.total_price) FROM appointment_care ac " +
            "JOIN appointments a ON ac.appointment_id = a.id " +
            "JOIN cares c ON ac.care_id = c.id " +
            "WHERE a.appointment_date BETWEEN :startDate AND :endDate " +
            "GROUP BY c.service_id",
            nativeQuery = true)
    List<Object[]> findRevenueByServiceByTimePeriodNative(@Param("startDate") LocalDateTime startDate,
                                                          @Param("endDate") LocalDateTime endDate);

    // Danh sách cuộc hẹn trong khoảng thời gian (Derived Query)
    List<Appointment> findAppointmentsByAppointmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}
