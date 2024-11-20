package com.admin.controller;

import com.admin.model.Admin;
import com.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Lưu admin mới hoặc chỉnh sửa admin hiện tại
    @PostMapping("/ViewAdmins/save")
    public String saveAdmin(Admin admin, RedirectAttributes ra) {
        admin.getUser().setRole("admin");
        adminService.save(admin);
        ra.addFlashAttribute("message", "The admin has been saved successfully.");
        return "redirect:/ViewAdmins";
    }

    // Hiển thị form chỉnh sửa (chỉ chính mình)
    @GetMapping("/ViewAdmins/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, HttpServletRequest request) {
        String currentUsername = (String) request.getSession().getAttribute("loggedInUsername");
        Admin admin = adminService.get(id);

        // Kiểm tra quyền
        if (!admin.getUser().getUsername().equals(currentUsername)) {
            throw new SecurityException("You can only edit your own account!");
        }

        model.addAttribute("admin", admin);
        return "admin/admin_form";
    }

    // Xóa admin (chỉ chính mình)
    @GetMapping("/ViewAdmins/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Integer id, HttpServletRequest request) {
        String currentUsername = (String) request.getSession().getAttribute("loggedInUsername");
        Admin admin = adminService.get(id);

        // Kiểm tra quyền
        if (!admin.getUser().getUsername().equals(currentUsername)) {
            throw new SecurityException("You can only delete your own account!");
        }

        adminService.delete(id);
        return "redirect:/ViewAdmins";
    }
}
