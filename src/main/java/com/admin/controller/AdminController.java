package com.admin.controller;

import com.admin.model.Admin;
import com.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/ViewAdmins")
    public String showAdminList(Model model) {
        List<Admin> listAdmins = adminService.listAll();
        model.addAttribute("listAdmins", listAdmins);
        return "admin/ViewAdmins";
    }

    @GetMapping("/ViewAdmins/new")
    public String showNewAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("pageTitle", "Add New Admin");
        return "admin/admin_form"; // Returns the admin form
    }


    @PostMapping("/ViewAdmins/new")
    public String saveAdmin(@ModelAttribute Admin admin) {
        // Logic lưu admin mới vào cơ sở dữ liệu
        adminService.save(admin);
        return "redirect:/ViewAdmins"; // Sau khi lưu, quay lại danh sách admin
    }

    @GetMapping("/ViewAdmins/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Admin admin = adminService.get(id);
        model.addAttribute("admin", admin);
        return "admin/admin_form";
    }

    @GetMapping("/ViewAdmins/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Integer id) {
        adminService.delete(id);
        return "redirect:/ViewAdmins";
    }
}