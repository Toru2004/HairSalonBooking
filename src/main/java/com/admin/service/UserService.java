package com.admin.service;

import com.admin.model.User;
import com.admin.model.Manager;
import com.admin.model.Staff;
import com.admin.repository.UserRepository;
import com.admin.repository.ManagerRepository;
import com.admin.repository.StaffRepository;
import com.admin.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private StaffRepository staffRepository;

    // Các phương thức hiện tại
    public Long countById(Integer id) {
        return userRepository.countById(id);
    }

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundException("Could not find any users with ID " + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = userRepository.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundException("Could not find any users with ID " + id);
        }
        userRepository.deleteById(id);
    }

    // Thêm phương thức để tạo Manager
    public Manager createManager(User user) {
        Manager manager = new Manager();
        manager.setUser(user);  // Set User cho Manager
        return managerRepository.save(manager);
    }

    // Thêm phương thức để tạo Staff
    public Staff createStaff(User user, Manager manager) {
        Staff staff = new Staff();
        staff.setUser(user);  // Set User cho Staff
        staff.setManager(manager);  // Set Manager cho Staff
        return staffRepository.save(staff);
    }

    // Lấy thông tin Manager của Staff
    public Manager getManagerForStaff(Integer staffId) throws UserNotFoundException {
        Optional<Staff> staff = staffRepository.findById(staffId);
        if (staff.isPresent()) {
            return staff.get().getManager();
        }
        throw new UserNotFoundException("Could not find any staff with ID " + staffId);
    }

    // Lấy thông tin User cho Manager hoặc Staff
    public User getUserForManagerOrStaff(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        throw new UserNotFoundException("Could not find any user with ID " + id);
    }
}
