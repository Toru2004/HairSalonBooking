package com.admin.controller;

import com.admin.model.Customer;
import com.admin.service.FeedbackService;
import com.admin.model.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService; // Inject FeedbackService


    @GetMapping("/manageFeedbacks")
    public String showCustomerList(HttpServletRequest request, Model model) {
        List<Feedback> listFeedbacks = feedbackService.listAll(); // Lấy danh sách tất cả khách hàng
        model.addAttribute("listFeedbacks", listFeedbacks); // Đưa danh sách khách hàng vào model để hiển thị trong view

        // Lấy vai trò từ session
        String role = (String) request.getSession().getAttribute("role");

        // Kiểm tra nếu không phải staff thì chuyển hướng
        if (role == null || !role.equals("admin")) {
            return "redirect:/page/login"; // Chuyển hướng đến trang Access Denied
        }

        return "admin/manageFeedbacks"; // Trả về view manageCustomers.html
    }

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
