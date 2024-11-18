package com.admin.controller;

import com.admin.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.admin.service.AppointmentService;
import com.admin.model.Appointment;
import com.admin.service.StaffService;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org. springframework. web. bind. annotation. RequestParam;

@Controller
public class ManagerController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/manager/managerStaff")
    public String viewStaff(Model model) {
        List<Staff> listStaff = staffService.findAll();  // Lấy tất cả nhân viên
        model.addAttribute("listStaff", listStaff);
        return "redirect:/manager/managerStaff";
    }

    // Hiển thị form thêm hoặc sửa nhân viên
    @GetMapping("/manager/staff_form")
    public String showStaffForm(@RequestParam(value = "id", required = false) Long id, Model model) {
        Staff staff = new Staff();
        if (id != null) {
            staff = staffService.findById(id);
        }
        model.addAttribute("staff", staff);
        model.addAttribute("pageTitle", (id == null ? "Add New Staff" : "Edit Staff"));  // Thêm tiêu đề trang
        return "redirect:/manager/staff_form";
    }

    // Lưu nhân viên sau khi thêm hoặc chỉnh sửa
    @PostMapping("/manager/managerStaff")
    public String saveStaff(@ModelAttribute Staff staff) {
        staffService.save(staff);
        return "redirect:/manager/managerStaff";
    }

    // Xóa nhân viên
    @GetMapping("/manager/managerStaff/delete")
    public String deleteStaff(@RequestParam("id") Long id) {
        staffService.delete(id);
        return "redirect:/manager/managerStaff";
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

        return "manager/managerDashboard";  // Điều hướng đến file dashboard.html trong thư mục templates/manager
    }
}
