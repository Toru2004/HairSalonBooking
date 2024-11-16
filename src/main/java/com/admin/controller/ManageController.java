package com.admin.controller;

import com.admin.model.Manage;
import com.admin.exception.ManageNotFoundException;
import com.admin.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ManageController {

    @Autowired
    private ManageService manageService;

    // Hiển thị danh sách tất cả manages
    @GetMapping("/manageManages")
    public String showManageList(Model model) {
        List<Manage> listManages = manageService.listAll();
        model.addAttribute("listManages", listManages);
        return "admin/manageManages"; //trả về trang html
    }

    // Hiển thị form thêm mới manage
    @GetMapping("/manageManages/new")
    public String showNewForm(Model model) {
        model.addAttribute("manage", new Manage());
        model.addAttribute("pageTitle", "Add New Manage");
        return "admin/manage_form"; //trả về form manage
    }

    // Lưu thông tin manage
    @PostMapping("/manageManages/save")
    public String saveManage(Manage manage, RedirectAttributes ra) {
        manageService.save(manage);
        ra.addFlashAttribute("message", "The manage has been saved successfully.");
        return "redirect:/manageManages";
    }

    // Hiển thị form chỉnh sửa thông tin manage
    @GetMapping("/manageManages/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Manage manage = manageService.get(id);
            model.addAttribute("manage", manage);
            model.addAttribute("pageTitle", "Edit Manage (ID: " + id + ")");
            return "admin/manage_form";
        } catch (ManageNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageManages";
        }
    }

    // Xóa manage theo ID
    @GetMapping("/manageManages/delete/{id}")
    public String deleteManage(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            manageService.delete(id);
            ra.addFlashAttribute("message", "The manage ID " + id + " has been deleted.");
        } catch (ManageNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/manageManages";
    }
}