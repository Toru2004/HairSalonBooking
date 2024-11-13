package com.admin.controller;

import com.admin.model.Stylist;
import com.admin.exception.StylistNotFoundException;
import com.admin.service.StylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StylistController {

    @Autowired
    private StylistService stylistService;

   
    // Hiển thị danh sách tất cả stylist
    @GetMapping("/manageStylists")
    public String showStylistList(Model model) {
        List<Stylist> listStylists = stylistService.listAll();
        model.addAttribute("listStylists", listStylists);
        return "manageStylists";
    }

    // Hiển thị form thêm mới stylist
    @GetMapping("/manageStylists/new")
    public String showNewForm(Model model) {
        model.addAttribute("stylist", new Stylist());
//        model.addAttribute("pageTitle", "Add New Stylist");
        return "stylist_form";
    }

    // Lưu thông tin stylist
    @PostMapping("/manageStylists/save")
    public String saveStylist(Stylist stylist, RedirectAttributes ra) {
        stylistService.save(stylist);
        ra.addFlashAttribute("message", "The stylist has been saved successfully.");
        return "redirect:/manageStylists";
    }

    // Hiển thị form chỉnh sửa thông tin stylist
    @GetMapping("/manageStylists/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Stylist stylist = stylistService.get(id);
            model.addAttribute("stylist", stylist);
            model.addAttribute("pageTitle", "Edit Stylist (ID: " + id + ")");
            return "stylist_form";
        } catch (StylistNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageStylists";
        }
    }

    // Xóa stylist theo ID
    @GetMapping("/manageStylists/delete/{id}")
    public String deleteStylist(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            stylistService.delete(id);
            ra.addFlashAttribute("message", "The stylist ID " + id + " has been deleted.");
        } catch (StylistNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/manageStylists";
    }
}
