package com.admin.service;


import com.admin.model.Staff;
import com.admin.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> findAll() {
        return staffRepository.findAll();  // Lấy tất cả nhân viên
    }

    public Staff findById(Long id) {
        return staffRepository.findById(id).orElse(null);  // Lấy nhân viên theo id
    }

    public void save(Staff staff) {
        staffRepository.save(staff);  // Lưu nhân viên vào cơ sở dữ liệu
    }

    public void delete(Long id) {
        staffRepository.deleteById(id);  // Xóa nhân viên theo id
    }
}
