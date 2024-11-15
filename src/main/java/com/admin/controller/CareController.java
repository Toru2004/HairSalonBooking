package com.admin.controller;

import com.admin.exception.CareNotFoundException;
import com.admin.model.Care;
import com.admin.service.CareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CareController {
    @Autowired
    private CareService careService;

    // Hiển thị danh sách tất cả care
    @GetMapping("/manageCares")
    public String showCareList(Model model) {
        List<Care> listCares = careService.listAll();
        model.addAttribute("listCares", listCares);
        return "admin/manageCares"; // Trả về view manageCares.html
    }

    // Hiển thị form để thêm care mới
    @GetMapping("/manageCares/new")
    public String showNewCareForm(Model model) {
        model.addAttribute("care", new Care());
        model.addAttribute("pageTitle", "Add New Service");
        return "admin/care_form"; // Trả về view care_form.html
    }

    // Lưu thông tin care
    @PostMapping("/manageCares/save")
    public String saveCare(Care care, RedirectAttributes ra) {
        if (care.getId() != null) {
            // Gọi phương thức update nếu có ID
            careService.update(care);
            ra.addFlashAttribute("message", "The care has been updated successfully.");
        } else {
            // Gọi phương thức save nếu không có ID (tạo mới)
            careService.save(care);
            ra.addFlashAttribute("message", "The care has been added successfully.");
        }
        return "redirect:/manageCares"; // Chuyển hướng về trang danh sách care
    }

    // Hiển thị form để chỉnh sửa thông tin care
    @GetMapping("/manageCares/edit/{id}")
    public String showEditCareForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Care care = careService.get(id);
            model.addAttribute("care", care); // Truyền care vào model
            model.addAttribute("pageTitle", "Edit Service (ID: " + id + ")");
            return "admin/care_form"; // Trả về view care_form.html để chỉnh sửa care
        } catch (CareNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageCares"; // Chuyển hướng về trang danh sách care
        }
    }

    // Xóa care
    @GetMapping("/manageCares/delete/{id}")
    public String deleteCare(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            careService.delete(id); // Gọi phương thức xóa care
            ra.addFlashAttribute("message", "The care has been deleted successfully.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Error: " + e.getMessage());
        }
        return "redirect:/manageCares"; // Chuyển hướng về trang danh sách care
    }
}
