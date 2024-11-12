package com.admin.service;
/*

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service // Đánh dấu lớp này là một lớp service trong Spring.
public class ServiceService {

    private final ServiceRepository serviceRepository;

    @Autowired // Tự động (inject) repository để sử dụng trong lớp này.
    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    // Lấy danh sách tất cả các dịch vụ
    public List<Service> getAllServices() {
        return serviceRepository.findAll(); // Tìm tất cả dịch vụ trong CSDL.
    }

    // Tìm dịch vụ theo ID
    public Service getServiceById(Long id) {
        return serviceRepository.findById(id).orElse(null); // Trả về dịch vụ hoặc null nếu không tìm thấy.
    }

    // Tạo mới một dịch vụ
    public Service createService(Service service) {
        return serviceRepository.save(service); // Lưu dịch vụ mới vào CSDL.
    }

    // Cập nhật một dịch vụ dựa trên ID
    public Service updateService(Long id, Service updatedService) {
        Service service = getServiceById(id); // Tìm dịch vụ theo ID.
        if (service != null) {
            service.setName(updatedService.getName()); // Cập nhật tên dịch vụ.
            service.setDescription(updatedService.getDescription()); // Cập nhật mô tả dịch vụ.
            service.setPrice(updatedService.getPrice()); // Cập nhật giá dịch vụ.
            return serviceRepository.save(service); // Lưu thay đổi vào CSDL.
        }
        return null; // Trả về null nếu dịch vụ không tồn tại.
    }

    // Xóa một dịch vụ theo ID
    public void deleteService(Long id) {
        serviceRepository.deleteById(id); // Xóa dịch vụ khỏi CSDL.
    }
}

*/
