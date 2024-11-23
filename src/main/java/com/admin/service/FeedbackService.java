package com.admin.service;

import com.admin.model.Customer;
import com.admin.model.Feedback;
import com.admin.repository.FeedbackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<Feedback> listAll() {
        return (List<Feedback>) feedbackRepository.findAll();
    }

    public void save(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
}
