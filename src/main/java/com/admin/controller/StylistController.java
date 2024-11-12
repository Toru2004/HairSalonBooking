package com.admin.controller;

import com.admin.model.Stylist;
import com.admin.service.StylistService;
import com.admin.service.UserService;
import com.admin.exception.StylistNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class StylistController {
    @Autowired
    private StylistService stylistService;
    @Autowired
    private UserService userService;

    // Hiển thị danh sách tất cả stylists
    @GetMapping("/manageStylists")
    public String showStylistList(Model model) {
        model.addAttribute("listStylists", stylistService.listAll());  // Truyền danh sách stylists vào model
        return "manageStylists"; // Trả về view manageStylists.html
    }

    // Hiển thị form để thêm stylist mới
    @GetMapping("/manageStylists/new")
    public String showNewStylistForm(Model model) {
        model.addAttribute("stylist", new Stylist()); // Tạo đối tượng Stylist mới cho form
        model.addAttribute("pageTitle", "Add New Stylist");
        return "stylist_form"; // Trả về view stylist_form.html để thêm stylist
    }

    // Lưu stylist vào cơ sở dữ liệu
    @PostMapping("/manageStylists/save")
    public String saveStylist(Stylist stylist, RedirectAttributes ra) {
        // Kiểm tra email và password
        if (stylist.getUser().getEmail() == null || stylist.getUser().getEmail().isEmpty()) {
            ra.addFlashAttribute("message", "Email cannot be null or empty.");
            return "redirect:/manageStylists/new"; // Quay lại form nếu email thiếu
        }
        if (stylist.getUser().getPassword() == null || stylist.getUser().getPassword().isEmpty()) {
            ra.addFlashAttribute("message", "Password cannot be null or empty.");
            return "redirect:/manageStylists/new"; // Quay lại form nếu password thiếu
        }

        stylistService.save(stylist); // Lưu stylist vào cơ sở dữ liệu
        ra.addFlashAttribute("message", "The stylist has been saved successfully.");
        return "redirect:/manageStylists"; // Chuyển hướng về trang danh sách stylist
    }


    // Hiển thị form để chỉnh sửa stylist
    @GetMapping("/manageStylists/edit/{id}")
    public String showEditStylistForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Stylist stylist = stylistService.get(id); // Lấy thông tin stylist theo ID
            model.addAttribute("stylist", stylist);
            model.addAttribute("pageTitle", "Edit Stylist (ID: " + id + ")");
            return "stylist_form"; // Trả về view stylist_form.html để chỉnh sửa stylist
        } catch (StylistNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Thông báo lỗi nếu không tìm thấy stylist
            return "redirect:/manageStylists"; // Chuyển hướng về trang danh sách stylist
        }
    }

    // Xóa stylist theo ID
    @GetMapping("/manageStylists/delete/{id}")
    public String deleteStylist(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            stylistService.delete(id); // Xóa stylist theo ID
            ra.addFlashAttribute("message", "The stylist ID " + id + " has been deleted.");
        } catch (StylistNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Thông báo lỗi nếu không tìm thấy stylist
        }
        return "redirect:/manageStylists"; // Chuyển hướng về trang danh sách stylist
    }
}
