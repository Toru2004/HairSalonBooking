package com.admin.controller;

import com.admin.exception.DichVuNotFoundException;
import com.admin.model.DichVu;
import com.admin.service.DichVuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/manageDichVus")
public class DichVuController {
    @Autowired
    private DichVuService dichVuService;

    // Hiển thị danh sách tất cả dịch vụ
    @GetMapping
    public String showDichVuList(Model model) {
        List<DichVu> listDichVus = dichVuService.listAll();
        model.addAttribute("listDichVus", listDichVus);
        return "manageDichVus";
    }

    // Hiển thị form để thêm dịch vụ mới
    @GetMapping("/new")
    public String showNewDichVuForm(Model model) {
        model.addAttribute("dichVu", new DichVu());
        model.addAttribute("pageTitle", "Add New Service");
        return "dichvu_form";
    }

    // Lưu thông tin dịch vụ
    @PostMapping("/save")
    public String saveDichVu(DichVu dichVu, RedirectAttributes ra) {
        if (dichVu.getId() != null) {
            // Gọi phương thức update nếu có ID
            dichVuService.update(dichVu);
            ra.addFlashAttribute("message", "The service has been updated successfully.");
        } else {
            // Gọi phương thức save nếu không có ID (tạo mới)
            dichVuService.save(dichVu);
            ra.addFlashAttribute("message", "The service has been added successfully.");
        }
        return "redirect:/manageDichVus"; // Chuyển hướng về trang danh sách dịch vụ
    }







    // Hiển thị form để chỉnh sửa thông tin dịch vụ
    @GetMapping("/edit/{id}")
    public String showEditDichVuForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) throws DichVuNotFoundException {
        try {
            DichVu dichVu = dichVuService.get(id);
            model.addAttribute("dichVu", dichVu); // Truyền dịch vụ vào model
            model.addAttribute("pageTitle", "Edit Service (ID: " + id + ")");
            return "dichvu_form"; // Trả về view form để chỉnh sửa
        } catch (DichVuNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageDichVus";
        }
    }



    // Xóa dịch vụ
    @GetMapping("/delete/{id}")
    public String deleteDichVu(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            dichVuService.delete(id); // Gọi phương thức xóa dịch vụ
            ra.addFlashAttribute("message", "The service has been deleted successfully.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Error: " + e.getMessage());
        }
        return "redirect:/manageDichVus"; // Chuyển hướng về trang danh sách dịch vụ
    }




}
