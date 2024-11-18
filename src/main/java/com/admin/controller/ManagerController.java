package com.admin.controller;

<<<<<<< HEAD
=======
import com.admin.model.Manager; // Changed from Stylist to Manager
import com.admin.exception.ManagerNotFoundException; // Changed from StylistNotFoundException
import com.admin.service.ManagerService; // Changed from StylistService
>>>>>>> 32e447fcc978edb38f93a4f083615b0194cc3b4e
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
<<<<<<< HEAD
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
=======
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @GetMapping("/manageManagers")
    public String showManagerList(Model model) {
        List<Manager> listManagers = managerService.listAll();
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
        manager.getUser().setRole("manager");
        managerService.save(manager);
        ra.addFlashAttribute("message", "The manager has been saved successfully.");
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
>>>>>>> 32e447fcc978edb38f93a4f083615b0194cc3b4e
