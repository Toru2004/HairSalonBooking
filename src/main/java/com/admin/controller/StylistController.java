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
// Được sử dụng để xử lý các tệp tải lên từ client (người dùng gửi lên server)
import org.springframework.web.multipart.MultipartFile;

// Được sử dụng để xử lý ngoại lệ khi làm việc với các tệp tin (như khi lưu file)
import java.io.IOException;

// Được sử dụng trong các phương thức controller để nhận dữ liệu từ form, hoặc truyền dữ liệu vào đối tượng model
import org.springframework.web.bind.annotation.ModelAttribute;

// Được sử dụng trong các phương thức controller để nhận các tham số từ form (như tệp tải lên)
import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class StylistController {

    @Autowired
    private StylistService stylistService;


    // Hiển thị danh sách tất cả stylist
    @GetMapping("/manageStylists")
    public String showStylistList(Model model) {
        List<Stylist> listStylists = stylistService.listAll();
        model.addAttribute("listStylists", listStylists);
        return "admin/manageStylists";//trả về trang html
    }

    // Hiển thị form thêm mới stylist
    @GetMapping("/manageStylists/new")
    public String showNewForm(Model model) {
        model.addAttribute("stylist", new Stylist());
        model.addAttribute("pageTitle", "Add New Stylist");
        return "admin/stylist_form";//trả về form stylist
    }

//    Để hiển thị ảnh, bạn cần một endpoint riêng để xuất byte mảng dưới dạng hình ảnh.
    @GetMapping("/manageStylists/image/{id}")
    public byte[] getStylistImage(@PathVariable("id") Integer id) throws StylistNotFoundException {
        Stylist stylist = stylistService.get(id);
        return stylist.getProfilePicture(); // Trả về byte[] của ảnh
    }


    // Lưu thông tin stylist
    @PostMapping("/manageStylists/save")
    public String saveStylist(
            @ModelAttribute Stylist stylist,
            @RequestParam("imageFile") MultipartFile imageFile,
            RedirectAttributes ra) {
        try {
            if (!imageFile.isEmpty()) {
                stylist.setProfilePicture(imageFile.getBytes()); // Lưu ảnh dưới dạng byte[]
            }
        } catch (IOException e) {
            ra.addFlashAttribute("message", "Error uploading image: " + e.getMessage());
            return "redirect:/manageStylists";
        }

        stylistService.save(stylist); // Lưu stylist vào cơ sở dữ liệu
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
            return "admin/stylist_form";
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
