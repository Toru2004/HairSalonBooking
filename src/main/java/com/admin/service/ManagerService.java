package com.admin.service;

import com.admin.model.Manager;
import com.admin.model.User;  // Đảm bảo import lớp User
import com.admin.repository.ManagerRepository;
import com.admin.exception.ManagerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    public List<Manager> listAll() {
        return (List<Manager>) managerRepository.findAll();
    }

    // Phương thức save được sử dụng để lưu Manager
    public void save(Manager manager) {
        if (manager.getId() != null) {
            // Nếu Manager đã có ID, gọi merge để đính kèm vào session
            merge(manager);
        } else {
            // Nếu Manager là thực thể mới, gọi persist để lưu
            managerRepository.save(manager);
        }
    }

    // Phương thức để tìm Manager theo ID
    public Manager get(Integer id) throws ManagerNotFoundException {
        Optional<Manager> result = managerRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new ManagerNotFoundException("Could not find any manager with ID " + id);
    }

    // Phương thức delete để xóa Manager
    public void delete(Integer id) throws ManagerNotFoundException {
        Long count = managerRepository.countById(id);
        if (count == null || count == 0) {
            throw new ManagerNotFoundException("Could not find any manager with ID " + id);
        }
        managerRepository.deleteById(id);
    }

    // Cập nhật thông tin Manager và User liên quan
    public Manager updateManager(Integer managerId, Manager updatedManager) {
        Optional<Manager> existingManager = managerRepository.findById(managerId);
        if (existingManager.isPresent()) {
            Manager manager = existingManager.get();

            // Cập nhật thông tin của đối tượng User liên kết với Manager
            User user = manager.getUser();
            user.setUsername(updatedManager.getUser().getUsername());
            user.setEmail(updatedManager.getUser().getEmail());
            user.setPhoneNumber(updatedManager.getUser().getPhoneNumber());
            user.setEnabled(updatedManager.getUser().isEnabled());

            // Lưu lại đối tượng User đã được cập nhật và lưu Manager
            manager.setUser(user);
            return managerRepository.save(manager);  // Lưu Manager với User đã cập nhật
        }
        return null; // Trường hợp không tìm thấy Manager
    }

    // Phương thức merge giúp đính kèm Manager vào session nếu Manager bị detached
    public Manager merge(Manager manager) {
        return managerRepository.save(manager);  // Hibernate sẽ merge thực thể đã detached
    }
}
