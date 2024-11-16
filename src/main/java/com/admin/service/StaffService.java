package com.admin.service;

import com.admin.model.Staff;
import com.admin.model.Manager; // Đảm bảo import lớp Manager
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

    @Autowired
    private ManagerService managerService;  // Thêm ManagerService vào để sử dụng merge

    // Lấy tất cả Staff
    public List<Staff> listAll() {
        return (List<Staff>) staffRepository.findAll();
    }

    // Lưu Staff (kể cả khi có Manager đã bị detached)
    public void save(Staff staff) {
        if (staff.getManager() != null && staff.getManager().getId() != null) {
            // Kiểm tra và đính kèm Manager nếu nó đã bị detached
            staff.setManager(managerService.merge(staff.getManager()));  // Merge Manager vào session
        }
        staffRepository.save(staff);  // Lưu Staff
    }

    // Lấy Staff theo ID
    public Staff get(Integer id) throws StaffNotFoundException {
        Optional<Staff> result = staffRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new StaffNotFoundException("Could not find any staff with ID " + id);
    }

    // Xóa Staff theo ID
    public void delete(Integer id) throws StaffNotFoundException {
        Long count = staffRepository.countById(id);
        if (count == null || count == 0) {
            throw new StaffNotFoundException("Could not find any staff with ID " + id);
        }
        staffRepository.deleteById(id);
    }
}
