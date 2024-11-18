
package com.admin.controller;

import com.admin.model.Admin;
import com.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Hiển thị form để thêm admin mới
    @GetMapping("/ViewAdmins/new")
    public String showNewAdminForm(Model model) {
        model.addAttribute("admin", new Admin()); // Tạo đối tượng Admin mới để sử dụng trong form
        model.addAttribute("pageTitle", "Add New Admin"); // Thiết lập tiêu đề trang
        return "admin/admin_form"; // Trả về view admin_form.html để thêm mới admin
    }

    // Lưu thông tin admin
    @PostMapping("/ViewAdmins/save")
    public String saveAdmin(Admin admin, RedirectAttributes ra) {
        admin.getUser().setRole("admin"); // Set role là admin
        adminService.save(admin); // Lưu thông tin admin vào cơ sở dữ liệu
        ra.addFlashAttribute("message", "The admin has been saved successfully."); // Thông báo thành công
        return "redirect:/ViewAdmins"; // Chuyển hướng về trang danh sách admin
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

