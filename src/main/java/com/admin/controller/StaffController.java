package com.admin.controller;

import com.admin.model.Appointment;
import com.admin.model.Staff;
import com.admin.model.Manager;
import com.admin.model.User;  // Import the User model
import com.admin.exception.StaffNotFoundException;
import com.admin.service.StaffService;
import com.admin.service.ManagerService;
import com.admin.service.UserService;  // Import the UserService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
public class StaffController {


    @Autowired
    private StaffService staffService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private UserService userService;  // Inject the UserService

    @GetMapping("/Staff/staffDashboard")
    public String staffDashboard(Model model) {

        return "Staff/staffDashboard";
    }



    @GetMapping("/manageStaffs")
    public String showStaffList(Model model) {
        List<Staff> listStaffs = staffService.listAll();
        model.addAttribute("listStaffs", listStaffs);
        return "admin/manageStaffs";
    }

    @GetMapping("/manageStaffs/new")
    public String showNewForm(Model model) {
        List<User> listUsers = userService.listAll();  // Lấy danh sách users từ service
        List<Manager> listManagers = managerService.listAll();  // Lấy danh sách managers
        model.addAttribute("staff", new Staff());
        model.addAttribute("listUsers", listUsers);  // Gửi danh sách users vào form
        model.addAttribute("listManagers", listManagers);
        model.addAttribute("pageTitle", "Add New Staff");
        return "admin/staff_form";
    }

    @PostMapping("/manageStaffs/save")
    public String saveStaff(Staff staff, RedirectAttributes ra) {
        staff.getUser().setRole("staff");
        // Kiểm tra và lưu User nếu chưa có ID
        if (staff.getUser() != null && staff.getUser().getId() == null) {
            userService.save(staff.getUser());  // Lưu user nếu chưa có ID
        }

        // Lưu staff sau khi lưu user (User đã có ID hoặc mới được lưu)
        staffService.save(staff);  // Lưu staff
        ra.addFlashAttribute("message", "The staff has been saved successfully.");
        return "redirect:/manageStaffs";
    }




    // Hiển thị form chỉnh sửa thông tin staff
    @GetMapping("/manageStaffs/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Staff staff = staffService.get(id);
            List<Manager> listManagers = managerService.listAll();
            model.addAttribute("staff", staff);
            model.addAttribute("listManagers", listManagers);
            model.addAttribute("pageTitle", "Edit Staff (ID: " + id + ")");
            return "admin/staff_form";
        } catch (StaffNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageStaffs";
        }
    }

    // Xóa staff theo ID
    @GetMapping("/manageStaffs/delete/{id}")
    public String deleteStaff(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            staffService.delete(id);
            ra.addFlashAttribute("message", "The staff ID " + id + " has been deleted.");
        } catch (StaffNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/manageStaffs";
    }
}
