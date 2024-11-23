package com.admin.controller;


import com.admin.exception.CustomerNotFoundException;
import com.admin.exception.ManagerNotFoundException;
import com.admin.exception.UserNotFoundException;
import com.admin.model.*;
import com.admin.service.AppointmentService;
import com.admin.service.ManagerService;
import com.admin.service.StaffService;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;




@Controller

public class ManagerController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private StaffService staffService;

    @GetMapping("/manager/managerDashboard")
    public String managerDashboard(HttpServletRequest request, Model model) {
        // Truy xuất dữ liệu doanh thu từ appointment
        List<Appointment> appointments = appointmentService.getAllAppointments();

        // Tổng hợp dữ liệu doanh thu theo tháng (hoặc ngày/năm tùy theo yêu cầu)
        Map<String, Double> revenueByMonth = appointmentService.calculateMonthlyRevenue(appointments);

        // Lấy vai trò từ session
        String role = (String) request.getSession().getAttribute("role");

        // Kiểm tra nếu không phải staff thì chuyển hướng
        if (role == null || !role.equals("manager")) {
            return "redirect:/page/login"; // Chuyển hướng đến trang Access Denied
        }

        model.addAttribute("revenueByMonth", revenueByMonth);


        return "manager/managerDashboard";
    }

    @GetMapping("/manager/showStaffs")
    public String showStaffs(HttpServletRequest request, Model model) {
        // Lấy vai trò từ session
        String role = (String) request.getSession().getAttribute("role");

        // Kiểm tra nếu không phải staff thì chuyển hướng
        if (role == null || !role.equals("manager")) {
            return "redirect:/page/login"; // Chuyển hướng đến trang Access Denied
        }

        return "manager/showStaffs";
    }


    @GetMapping("/manager/showStaffs/{id}")
    public String showStaffById(@PathVariable("id") Integer id, Model model) {
        try {
            // Lấy thông tin user theo ID
            User existingUser = userService.get(id);

            // Lấy ID của manager
            Integer managerId = existingUser.getManager().getId();

            List<Staff> listStaffs = staffService.listByManager(managerId);
            model.addAttribute("listStaffs", listStaffs);
            // Chuyển hướng tới đường dẫn động
            return "manager/showStaffs";
        } catch (UserNotFoundException e) {
            // Xử lý khi user không tồn tại
            model.addAttribute("message", "Manager not found with ID: " + id);
            return "manager/managerDashboard";
        }
    }


    @Autowired
    private ManagerService managerService;

    @GetMapping("/manageManagers")
    public String showManagerList(Model model) {
        List<Manager> listManagers = managerService.listAll();
        LocalDate now = LocalDate.now();
        model.addAttribute("currentYear", now.getYear());
        model.addAttribute("currentMonth", now.getMonthValue());
        model.addAttribute("listManagers", listManagers);
        System.out.println("Managers List: " + listManagers);  // Debugging
        return "admin/manageManagers";
    }



    // Hiển thị form thêm mới manager
    @GetMapping("/manageManagers/new")
    public String showNewForm(Model model) {
        model.addAttribute("manager", new Manager());
        model.addAttribute("pageTitle", "Add New Manager");
        return "admin/manager_form";
    }

    // Lưu thông tin manager
    @PostMapping("/manageManagers/save")
    public String saveManager(Manager manager, RedirectAttributes ra) {
        try {
            if (manager.getId() != null) { // Nếu có ID thì thực hiện cập nhật
                // Lấy thông tin manager hiện tại từ cơ sở dữ liệu
                Manager existingManager = managerService.get(manager.getId());

                // Cập nhật thông tin user
                existingManager.getUser().setUsername(manager.getUser().getUsername());
                existingManager.getUser().setEmail(manager.getUser().getEmail());
                existingManager.getUser().setPhoneNumber(manager.getUser().getPhoneNumber());
                existingManager.getUser().setPassword(manager.getUser().getPassword());
                existingManager.getUser().setEnabled(manager.getUser().isEnabled());
                managerService.save(existingManager); // Lưu manager đã chỉnh sửa
            } else {
                // Nếu không có ID, thêm mới manager
                manager.getUser().setRole("manager"); // Gán vai trò
                managerService.save(manager); // Lưu manager mới
            }

            ra.addFlashAttribute("message", "The manager has been saved successfully.");
        } catch (ManagerNotFoundException e) {
            ra.addFlashAttribute("message", "Failed to save the manager: " + e.getMessage());
        }
        return "redirect:/manageManagers";
    }

    // Hiển thị form chỉnh sửa thông tin manager
    @GetMapping("/manageManagers/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Manager manager = managerService.get(id);
            model.addAttribute("manager", manager);
            model.addAttribute("pageTitle", "Edit Manager (ID: " + id + ")");
            return "admin/manager_form"; // C
        } catch (ManagerNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageManagers";
        }
    }

    // Xóa manager theo ID
    @GetMapping("/manageManagers/delete/{id}")
    public String deleteManager(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            managerService.delete(id); // Changed from stylistService.delete
            ra.addFlashAttribute("message", "The manager ID " + id + " has been deleted."); // Changed from stylist
        } catch (ManagerNotFoundException e) { // Changed from StylistNotFoundException
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/manageManagers"; // Changed from manageStylists
    }
}
