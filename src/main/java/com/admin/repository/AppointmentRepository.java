package com.admin.repository;

import com.admin.model.Appointment;
import org.springframework.data.repository.CrudRepository;

public interface AppointmentRepository extends CrudRepository<Appointment, Integer> {
    Long countById(Integer id);
}
