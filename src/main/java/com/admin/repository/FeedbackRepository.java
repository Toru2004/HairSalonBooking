package com.admin.repository;

import com.admin.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    // Các phương thức tìm kiếm có thể thêm vào nếu cần
}
