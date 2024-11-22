package com.admin.controller;

import com.admin.exception.AdminNotFoundException;
import com.admin.exception.CustomerNotFoundException;
import com.admin.model.Admin;
import com.admin.model.Customer;
import com.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.admin.exception.AdminNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Hiển thị danh sách admin
    @GetMapping("/ViewAdmins")
    public String showAdminList(Model model, HttpServletRequest request) {
        List<Admin> listAdmins = adminService.listAll();
        model.addAttribute("listAdmins", listAdmins);

        // Lấy username từ session (sau khi login)
        String currentUsername = (String) request.getSession().getAttribute("username");
        model.addAttribute("currentUsername", currentUsername);
        // Lấy vai trò từ session
        String role = (String) request.getSession().getAttribute("role");

        // Kiểm tra nếu không phải staff thì chuyển hướng
//        if (role == null || !role.equals("admin")) {
//            return "redirect:/page/login"; // Chuyển hướng đến trang Access Denied
//        }
        return "admin/ViewAdmins";
    }

    // Hiển thị form thêm admin mới
    @GetMapping("/ViewAdmins/new")
    public String showNewAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("pageTitle", "Add New Admin");
        return "admin/admin_form";
    }

    // Lưu thông tin admin
    @PostMapping("/ViewAdmins/save")
    public String saveAdmin(Admin admin, RedirectAttributes ra) {
        try {
            if (admin.getId() != null) { // Nếu có ID thì thực hiện cập nhật
                // Lấy thông tin admin hiện tại từ cơ sở dữ liệu
                Admin existingAdmin = adminService.get(admin.getId());

                // Cập nhật thông tin user
                existingAdmin.getUser().setUsername(admin.getUser().getUsername());
                existingAdmin.getUser().setEmail(admin.getUser().getEmail());
                existingAdmin.getUser().setPhoneNumber(admin.getUser().getPhoneNumber());
                existingAdmin.getUser().setPassword(admin.getUser().getPassword());
                existingAdmin.getUser().setEnabled(admin.getUser().isEnabled());
                adminService.save(existingAdmin); // Lưu admin đã chỉnh sửa
            } else {
                // Nếu không có ID, thêm mới admin
                admin.getUser().setRole("admin"); // Gán vai trò
                adminService.save(admin); // Lưu admin mới
            }

            ra.addFlashAttribute("message", "The admin has been saved successfully.");
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", "Failed to save the admin: " + e.getMessage());
        }
        return "redirect:/ViewAdmins";
    }

    @GetMapping("/ViewAdmins/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        String currentUsername = (String) request.getSession().getAttribute("loggedInUsername");

        try {
            Admin admin = adminService.get(id);

            // Kiểm tra quyền
            if (!admin.getUser().getUsername().equals(currentUsername)) {
                throw new SecurityException("You can only edit your own account!");
            }

            model.addAttribute("admin", admin);
        } catch (AdminNotFoundException e) {
            throw new RuntimeException("Admin not found with ID: " + id, e); // Hoặc trả về một trang lỗi tùy chỉnh
        }

        return "admin/admin_form";
    }

    @GetMapping("/ViewAdmins/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Integer id, HttpServletRequest request) {
        String currentUsername = (String) request.getSession().getAttribute("loggedInUsername");

        try {
            Admin admin = adminService.get(id);

            // Kiểm tra quyền
            if (!admin.getUser().getUsername().equals(currentUsername)) {
                throw new SecurityException("You can only delete your own account!");
            }

            adminService.delete(id);
        } catch (AdminNotFoundException e) {
            throw new RuntimeException("Admin not found with ID: " + id, e); // Hoặc chuyển hướng tới trang lỗi
        }

        return "redirect:/ViewAdmins";
    }

}
