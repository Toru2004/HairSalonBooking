package com.admin.controller;

import com.admin.service.FeedbackService;
import com.admin.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService; // Inject FeedbackService

    @PostMapping("/manageFeedbacks/save")
    public String submitFeedback(@ModelAttribute Feedback feedback, Model model) {
        feedbackService.save(feedback); // Lưu feedback
        model.addAttribute("success", "Feedback submitted successfully!");
        return "redirect:/page/thankyou"; // Chuyển hướng đến trang thank you
    }

    @GetMapping("/manageFeedbacks")
    public String listFeedbacks(Model model) {
        List<Feedback> feedbackList = feedbackService.getAllFeedbacks(); // Cần thêm phương thức trong service
        model.addAttribute("listFeedbacks", feedbackList);
        return "admin/manageFeedbacks";
    }


}
