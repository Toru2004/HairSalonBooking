package com.admin.service;

import com.admin.model.Manager; // Changed from Stylist
import com.admin.repository.ManagerRepository; // Changed from StylistRepository
import com.admin.exception.ManagerNotFoundException; // Changed from StylistNotFoundException
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService { // Changed from StylistService

    @Autowired
    private ManagerRepository managerRepository; // Changed from StylistRepository

    public List<Manager> listAll() {
        return (List<Manager>) managerRepository.findAll(); // Changed from StylistRepository
    }

    public void save(Manager manager) {
        managerRepository.save(manager); // Changed from StylistRepository.save
    }

    public Manager get(Integer id) throws ManagerNotFoundException { // Changed from StylistNotFoundException
        Optional<Manager> result = managerRepository.findById(id); // Changed from StylistRepository.findById
        if (result.isPresent()) {
            return result.get();
        }
        throw new ManagerNotFoundException("Could not find any manager with ID " + id); // Changed from StylistNotFoundException
    }

    public void delete(Integer id) throws ManagerNotFoundException { // Changed from StylistNotFoundException
        Long count = managerRepository.countById(id); // Changed from StylistRepository.countById
        if (count == null || count == 0) {
            throw new ManagerNotFoundException("Could not find any manager with ID " + id); // Changed from StylistNotFoundException
        }
        managerRepository.deleteById(id); // Changed from StylistRepository.deleteById
    }
}
