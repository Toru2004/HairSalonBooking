package com.admin.service;

import com.admin.exception.AppointmentNotFoundException;
import com.admin.model.Appointment;
import com.admin.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // Lấy tất cả các Appointment
    public List<Appointment> listAll() {
        return appointmentRepository.findAll();
    }

    // Lưu một Appointment mới
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    // Lấy Appointment theo ID
    public Appointment get(Integer id) throws AppointmentNotFoundException {
        return appointmentRepository.findById(id).orElseThrow(() -> new AppointmentNotFoundException("Appointment not found"));
    }

    // Cập nhật Appointment
    public void update(Appointment appointment) {
        if (appointmentRepository.existsById(appointment.getId())) {
            appointmentRepository.save(appointment);
        } else {
            throw new IllegalArgumentException("Appointment not found with ID: " + appointment.getId());
        }
    }

    // Xóa Appointment
    public void delete(Integer id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Appointment not found with ID: " + id);
        }
    }
}
