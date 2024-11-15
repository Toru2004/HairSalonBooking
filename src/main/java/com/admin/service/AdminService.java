package com.admin.service;

import com.admin.model.Admin;
import com.admin.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Admin get(Integer id) {
        return adminRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Admin not found"));
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