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

    @Autowired
    private UserService userService;  // Inject UserService

    // Lấy tất cả Staff
    public List<Staff> listAll() {
        return (List<Staff>) staffRepository.findAll();
    }

    // Lưu Staff (kể cả khi có Manager đã bị detached)
    public void save(Staff staff) {
        // Kiểm tra và lưu User nếu chưa có ID
        if (staff.getUser() != null && staff.getUser().getId() == null) {
            userService.save(staff.getUser());  // Lưu User nếu chưa có ID
        }

        // Kiểm tra và gán User cho Manager nếu Manager có User null
        if (staff.getManager() != null && staff.getManager().getUser() == null) {
            // Gán User cho Manager nếu chưa có
            staff.getManager().setUser(staff.getUser());
        }

        // Nếu Manager là thực thể đã có, cần chắc chắn rằng Manager không bị detached
        if (staff.getManager() != null && staff.getManager().getId() != null) {
            staff.setManager(managerService.merge(staff.getManager()));  // Merge Manager vào session
        }

        // Lưu staff
        staffRepository.save(staff);
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

