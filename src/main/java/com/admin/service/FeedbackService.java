package com.admin.service;

import com.admin.model.Feedback;
import com.admin.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    public void saveFeedback(Feedback feedback) {
        feedbackRepository.save(feedback); // Lưu feedback vào cơ sở dữ liệu
    }
}
