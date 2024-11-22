package com.admin.controller;

import com.admin.model.Feedback;
import com.admin.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Thêm một phương thức để xử lý form gửi feedback
    @PostMapping("/page/feedbacks")
    public String submitFeedback(@RequestParam("name") String name,
                                 @RequestParam("email") String email,
                                 @RequestParam("message") String message,
                                 @RequestParam("customerId") Long customerId, // Lấy customerId từ form
                                 Model model) {
        // Tạo đối tượng Feedback từ dữ liệu form
        Feedback feedback = new Feedback();
        feedback.setName(name);
        feedback.setEmail(email);
        feedback.setMessage(message);
        feedback.setCustomerId(customerId); // Lưu thông tin customerId

        // Gọi service để lưu feedback vào cơ sở dữ liệu
        feedbackService.saveFeedback(feedback);

        model.addAttribute("success", "Feedback submitted successfully!");
        return "redirect:/page/thankyou"; // Chuyển hướng đến trang thankyou
    }
}
