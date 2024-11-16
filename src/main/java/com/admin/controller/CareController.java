package com.admin.controller;

import com.admin.exception.CareNotFoundException;
import com.admin.model.Care;
import com.admin.service.CareService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;



// Được sử dụng trong các phương thức controller để nhận dữ liệu từ form, hoặc truyền dữ liệu vào đối tượng model
import org.springframework.web.bind.annotation.ModelAttribute;

// Được sử dụng trong các phương thức controller để nhận các tham số từ form (như tệp tải lên)
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("care", new Care()); // Tạo đối tượng Care mới
        model.addAttribute("pageTitle", "Add New Service");
        return "admin/care_form"; // Trả về template care_form.html
    }


    @GetMapping("/manageCares/image/{id}")
    public byte[] getCareImage(@PathVariable("id") Integer id) throws CareNotFoundException {
        Care care = careService.get(id);
        return care.getProfilePicture(); // Trả về byte[] của ảnh
    }

    // Lưu thông tin care

    @PostMapping("/manageCares/save")
    public String saveCares(
            @ModelAttribute Care care,
            @RequestParam("imageFile") MultipartFile imageFile,
            RedirectAttributes ra) {
        try {
            if (!imageFile.isEmpty()) {
                care.setProfilePicture(imageFile.getBytes()); // Lưu ảnh dưới dạng byte[]
            }
        } catch (IOException e) {
            ra.addFlashAttribute("message", "Error uploading image: " + e.getMessage());
            return "redirect:/manageCares";
        }
        careService.save(care); // Lưu đối tượng Care
        ra.addFlashAttribute("message", "The service has been saved successfully.");
        return "redirect:/manageCares";
    }


    // Hiển thị form để chỉnh sửa thông tin care
    @GetMapping("/manageCares/edit/{id}")
    public String showEditCareForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Care care = careService.get(id); // Lấy đối tượng Care theo ID
            model.addAttribute("care", care); // Truyền Care vào Model
            model.addAttribute("pageTitle", "Edit Service (ID: " + id + ")");
            return "admin/care_form"; // Trả về template care_form.html
        } catch (CareNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageCares"; // Chuyển hướng nếu không tìm thấy Care
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
