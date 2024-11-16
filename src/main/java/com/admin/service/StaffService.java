package com.admin.service;

import com.admin.model.Staff;
import com.admin.repository.StaffRepository;
import com.admin.exception.StaffNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public List<Staff> listAll() {
        return (List<Staff>) staffRepository.findAll(); // Lấy tất cả bản ghi Staff
    }

    public void save(Staff staff) {
        staffRepository.save(staff); // Lưu bản ghi Staff
    }

    public Staff get(Integer id) throws StaffNotFoundException {
        Optional<Staff> result = staffRepository.findById(id);
        if (result.isPresent()) {
            return result.get(); // Trả về đối tượng Staff nếu tìm thấy
        }
        throw new StaffNotFoundException("Could not find any staff with ID " + id); // Lỗi nếu không tìm thấy
    }

    public void delete(Integer id) throws StaffNotFoundException {
        Long count = staffRepository.countById(id);
        if (count == null || count == 0) {
            throw new StaffNotFoundException("Could not find any staff with ID " + id); // Lỗi nếu không tìm thấy
        }
        staffRepository.deleteById(id); // Xóa bản ghi Staff theo ID
    }
}
