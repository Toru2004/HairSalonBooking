package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.admin.service.AppointmentService;
import com.admin.model.Appointment;
import com.admin.service.StaffService;
import java.util.List;
import java.util.Map;


@Controller
public class ManagerController {
    @GetMapping("/RevenueOverview")
    public String RevenueOverview() {
        return "RevenueOverview";
    }

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/manager/RevenueOverview")
    public String RevenueOverview(Model model) {
        Map<String, Object> revenueData = appointmentService.getRevenueDataByFilter("monthly");  // Ví dụ, lấy doanh thu theo tháng
        model.addAttribute("revenueData", revenueData);
        return "/manager/RevenueOverview";
    }


    @GetMapping("/manager/managerDashboard")
    public String managerDashboard(Model model) {
        // Truy xuất dữ liệu doanh thu từ appointment
        List<Appointment> appointments = appointmentService.getAllAppointments();

        // Tổng hợp dữ liệu doanh thu theo tháng (hoặc ngày/năm tùy theo yêu cầu)
        Map<String, Double> revenueByMonth = appointmentService.calculateMonthlyRevenue(appointments);


        model.addAttribute("revenueByMonth", revenueByMonth);

        return "manager/managerDashboard";
    }
}
