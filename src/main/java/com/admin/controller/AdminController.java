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
    public String saveAdmin(Admin admin, RedirectAttributes ra, HttpServletRequest request) {
        String currentUsername = (String) request.getSession().getAttribute("loggedInUsername");
        String role = (String) request.getSession().getAttribute("role");

        try {
            if (admin.getId() != null) { // Nếu có ID thì thực hiện cập nhật
                Admin existingAdmin = adminService.get(admin.getId());

                // Nếu không phải tài khoản của chính mình và không phải admin
                if (!existingAdmin.getUser().getUsername().equals(currentUsername) &&
                        (role == null || !role.equals("admin"))) {
                    ra.addFlashAttribute("message", "You do not have permission to edit this account.");
                    return "redirect:/page/login";
                }

                // Cập nhật thông tin user
                existingAdmin.getUser().setUsername(admin.getUser().getUsername());
                existingAdmin.getUser().setEmail(admin.getUser().getEmail());
                existingAdmin.getUser().setPhoneNumber(admin.getUser().getPhoneNumber());
                existingAdmin.getUser().setPassword(admin.getUser().getPassword());
                existingAdmin.getUser().setEnabled(admin.getUser().isEnabled());
                adminService.save(existingAdmin); // Lưu admin đã chỉnh sửa
            } else {
                // Thêm mới admin
                admin.getUser().setRole("admin"); // Gán vai trò
                adminService.save(admin);
            }

            ra.addFlashAttribute("message", "Admin account has been saved successfully.");
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", "Unable to save admin account: " + e.getMessage());
        }
        return "redirect:/ViewAdmins";
    }



    @GetMapping("/ViewAdmins/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, HttpServletRequest request, RedirectAttributes ra) {
        String currentUsername = (String) request.getSession().getAttribute("loggedInUsername");
        String role = (String) request.getSession().getAttribute("role"); // Lấy vai trò từ session

        try {
            Admin admin = adminService.get(id);

            // Nếu không phải tài khoản của chính mình
            if (!admin.getUser().getUsername().equals(currentUsername)) {
                // Nếu không có quyền (không phải admin), chuyển hướng về trang login
                if (role == null || !role.equals("admin")) {
                    ra.addFlashAttribute("message", "You do not have permission to edit other accounts.");
                    return "redirect:/page/login";
                }

                // Có quyền admin, hiển thị thông báo cảnh báo
                ra.addFlashAttribute("message", "You are editing someone else's account.");
            }

            model.addAttribute("admin", admin); // Gửi thông tin tài khoản đến form
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", "Admin account not found with ID: " + id);
            return "redirect:/ViewAdmins"; // Quay lại danh sách admin
        }

        return "admin/admin_form";
    }


    @GetMapping("/ViewAdmins/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Integer id, HttpServletRequest request, RedirectAttributes ra) {
        String currentUsername = (String) request.getSession().getAttribute("loggedInUsername");
        String role = (String) request.getSession().getAttribute("role");

        try {
            Admin admin = adminService.get(id);

            // Kiểm tra nếu không phải tài khoản của chính mình
            if (!admin.getUser().getUsername().equals(currentUsername)) {
                // Nếu không phải admin, không cho phép xóa
                if (role == null || !role.equals("admin")) {
                    ra.addFlashAttribute("message", "You do not have permission to delete this account.");
                    return "redirect:/page/login"; // Chuyển hướng về trang login
                }
            }

            adminService.delete(id);
            ra.addFlashAttribute("message", "The admin account has been deleted successfully.");
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", "Admin account not found with ID: " + id);
            return "redirect:/ViewAdmins"; // Quay lại danh sách admin
        }

        return "redirect:/ViewAdmins";
    }

}
