package com.admin.service;

import com.admin.exception.AdminNotFoundException;
import com.admin.exception.CustomerNotFoundException;
import com.admin.model.Admin;
import com.admin.model.Customer;
import com.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Lấy tất cả các admin
    public List<Admin> listAll() {
        return (List<Admin>) adminRepository.findAll();
    }

    // Lưu admin mới
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    // Lấy admin theo ID
    public Admin get(Integer id) throws AdminNotFoundException {
        Optional<Admin> result = adminRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new AdminNotFoundException("Could not find any customers with ID " + id);
    }

    // Xóa admin theo ID
    public void delete(Integer id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Admin not found with ID: " + id);
        }
    }
}