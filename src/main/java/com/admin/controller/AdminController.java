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
    // Lưu thông tin admin
    @PostMapping("/ViewAdmins/save")
    public String saveAdmin(Admin admin, RedirectAttributes ra) {
        try {
            // Gán vai trò mặc định cho admin
            admin.getUser().setRole("admin");
            // Lưu thông tin admin thông qua service
            adminService.save(admin);
            // Thêm thông báo thành công vào model
            ra.addFlashAttribute("message", "Admin account has been saved successfully.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Error while saving admin account: " + e.getMessage());
        }
        // Chuyển hướng về danh sách admin
        return "redirect:/ViewAdmins";
    }





}
