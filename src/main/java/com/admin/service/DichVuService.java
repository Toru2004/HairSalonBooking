package com.admin.service;

import com.admin.exception.DichVuNotFoundException;
import com.admin.model.DichVu;
import com.admin.repository.DichVuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DichVuService {

    @Autowired
    private DichVuRepository dichVuRepository;

    // Lấy tất cả các dịch vụ
    public List<DichVu> listAll() {
        return (List<DichVu>) dichVuRepository.findAll();
    }

    // Lưu một dịch vụ mới
    public void save(DichVu dichVu) {
        dichVuRepository.save(dichVu);
    }

    // Lấy dịch vụ theo ID
    public DichVu get(Integer id) throws DichVuNotFoundException {
        return dichVuRepository.findById(id).orElseThrow(() -> new DichVuNotFoundException("Dich Vu not found"));
    }

    public void delete(Integer id) {
        // Kiểm tra xem dịch vụ có tồn tại hay không
        if (dichVuRepository.existsById(id)) {
            // Nếu có, tiến hành xóa
            dichVuRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Service not found with ID: " + id); // Nếu không tìm thấy dịch vụ
        }
    }




    // Cập nhật thông tin dịch vụ
    public void update(DichVu dichVu) {
        // Kiểm tra xem dịch vụ có tồn tại trong cơ sở dữ liệu không
        if (dichVuRepository.existsById(dichVu.getId())) {
            // Cập nhật dịch vụ nếu tồn tại
            dichVuRepository.save(dichVu);
        } else {
            // Nếu không tìm thấy dịch vụ, có thể ném ra ngoại lệ hoặc xử lý theo yêu cầu
            throw new IllegalArgumentException("Service not found with ID: " + dichVu.getId());
        }
    }



}
