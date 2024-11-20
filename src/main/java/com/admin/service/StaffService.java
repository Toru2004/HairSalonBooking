package com.admin.service;

import com.admin.model.Staff;
import com.admin.model.Manager; // Đảm bảo import lớp Manager
import com.admin.repository.StaffRepository;
import com.admin.exception.StaffNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.admin.exception.ManagerNotFoundException;

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
    public List<Staff> listByManager(Integer managerId) {
        return staffRepository.findByManagerId(managerId);
    }



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

        // Kiểm tra xem Manager đã có user hay chưa, không thay đổi User ID của Manager
        if (staff.getManager() != null && staff.getManager().getId() != null) {
            try {
                Manager existingManager = managerService.get(staff.getManager().getId()); // Get existing manager
                if (existingManager.getUser() == null) {
                    existingManager.setUser(staff.getUser());  // Set user if it's null (but don't change the user ID)
                }
                staff.setManager(existingManager); // Attach the existing manager with its user (no modification on user_id)
            } catch (ManagerNotFoundException e) {
                // Handle the case when the manager is not found, e.g., logging the error or rethrowing
                throw new RuntimeException("Manager not found for ID: " + staff.getManager().getId(), e);
            }
        }

        // Lưu staff (Staff sẽ có Manager và User đã được gán đúng)
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

