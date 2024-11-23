package com.admin.controller;

import com.admin.exception.AdminNotFoundException;
import com.admin.model.Admin;
import com.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Display the admin list
    @GetMapping("/ViewAdmins")
    public String showAdminList(Model model, HttpServletRequest request) {
        List<Admin> listAdmins = adminService.listAll();
        model.addAttribute("listAdmins", listAdmins);

        // Retrieve username from session (after login)
        String currentUsername = (String) request.getSession().getAttribute("username");
        model.addAttribute("currentUsername", currentUsername);

        return "admin/ViewAdmins";
    }

    // Display the form to add a new admin
    @GetMapping("/ViewAdmins/new")
    public String showNewAdminForm(Model model) {
        model.addAttribute("admin", new Admin());
        model.addAttribute("canEditEnabledOnly", false); // No restrictions when creating a new admin
        model.addAttribute("pageTitle", "Add New Admin");
        return "admin/admin_form";
    }

    // Save admin information
    @PostMapping("/ViewAdmins/save")
    public String saveAdmin(Admin admin, RedirectAttributes ra, HttpServletRequest request) {
        String currentUsername = (String) request.getSession().getAttribute("username");

        try {
            if (admin.getId() != null) { // If editing an existing admin
                Admin existingAdmin = adminService.get(admin.getId());

                // Ensure that users can only edit their own accounts
                if (!existingAdmin.getUser().getUsername().equals(currentUsername)) {
                    ra.addFlashAttribute("message", "You can only edit your own account.");
                    return "redirect:/ViewAdmins";
                }

                // Only update the `enabled` field
                existingAdmin.getUser().setEnabled(admin.getUser().isEnabled());
                adminService.save(existingAdmin);
            } else {
                // Create a new admin
                admin.getUser().setRole("admin"); // Set default role for new admin
                adminService.save(admin);
            }

            ra.addFlashAttribute("message", "The account has been saved successfully.");
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", "Unable to save the account: " + e.getMessage());
        }

        return "redirect:/ViewAdmins";
    }

    // Display the edit form
    @GetMapping("/ViewAdmins/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, HttpServletRequest request, RedirectAttributes ra) {
        String currentUsername = (String) request.getSession().getAttribute("username");

        try {
            Admin admin = adminService.get(id);

            // Prevent access if the account is not their own
            if (!admin.getUser().getUsername().equals(currentUsername)) {
                ra.addFlashAttribute("message", "You can only edit your own account.");
                return "redirect:/ViewAdmins";
            }

            // Send admin data and allow editing only `enabled` field
            model.addAttribute("admin", admin);
            model.addAttribute("canEditEnabledOnly", true); // Flag to limit editing
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", "Admin account not found with ID: " + id);
            return "redirect:/ViewAdmins";
        }

        return "admin/admin_form";
    }

    // Update the `enabled` status of an admin
    @PostMapping("/ViewAdmins/updateEnabled/{id}")
    public String updateEnabled(@PathVariable("id") Integer id,
                                @RequestParam boolean enabled,
                                RedirectAttributes ra,
                                HttpServletRequest request) {
        String currentUsername = (String) request.getSession().getAttribute("username");

        try {
            Admin admin = adminService.get(id);

            // Allow updating the `enabled` status only for the user's own account
            if (!admin.getUser().getUsername().equals(currentUsername)) {
                ra.addFlashAttribute("message", "You can only update the status of your own account.");
                return "redirect:/ViewAdmins";
            }

            admin.getUser().setEnabled(enabled);
            adminService.save(admin); // Save the update
            ra.addFlashAttribute("message", "Account status updated successfully.");
        } catch (AdminNotFoundException e) {
            ra.addFlashAttribute("message", "Admin account not found with ID: " + id);
        }

        return "redirect:/ViewAdmins";
    }

}
