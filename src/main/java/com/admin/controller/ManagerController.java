package com.admin.controller;

import com.admin.model.Manager; // Changed from Stylist to Manager
import com.admin.exception.ManagerNotFoundException; // Changed from StylistNotFoundException
import com.admin.service.ManagerService; // Changed from StylistService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
