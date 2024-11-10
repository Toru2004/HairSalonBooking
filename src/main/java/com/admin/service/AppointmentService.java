package com.admin.service;

import com.admin.model.Appointment;
import com.admin.exception.AppointmentNotFoundException;
import com.admin.repository.AppointmentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    @Autowired private AppointmentRepository repo;

    public List<Appointment> listAll() {
        return (List<Appointment>) repo.findAll();
    }

    public void save(Appointment appointment) {
        repo.save(appointment);
    }

    public Appointment get(Integer id) throws AppointmentNotFoundException {
        Optional<Appointment> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new AppointmentNotFoundException("Could not find any appointments with ID " + id);
    }

    public void delete(Integer id) throws AppointmentNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new AppointmentNotFoundException("Could not find any appointments with ID " + id);
        }
        repo.deleteById(id);
    }
}
