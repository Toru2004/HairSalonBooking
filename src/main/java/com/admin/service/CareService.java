package com.admin.service;

import com.admin.exception.CareNotFoundException;
import com.admin.model.Care;
import com.admin.repository.CareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CareService {

    @Autowired
    private CareRepository careRepository;

    // Lấy tất cả các care
    public List<Care> listAll() {
        return careRepository.findAll();
    }

    public List<Care> getAllCares() {
        return careRepository.findAll();
    }
    // Lưu một care mới
    public void save(Care care) {
        careRepository.save(care);
    }

    // Lấy care theo ID
    public Care get(Integer id) throws CareNotFoundException {
        return careRepository.findById(id).orElseThrow(() -> new CareNotFoundException("Service not found"));
    }

    // Cập nhật care
    public void update(Care care) {
        if (careRepository.existsById(care.getId())) {
            careRepository.save(care);
        } else {
            throw new IllegalArgumentException("Service not found with ID: " + care.getId());
        }
    }

    // Xóa care
    public void delete(Integer id) {
        if (careRepository.existsById(id)) {
            careRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Service not found with ID: " + id);
        }
    }


}
