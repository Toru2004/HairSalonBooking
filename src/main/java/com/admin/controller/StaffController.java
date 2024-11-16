package com.admin.controller;

import com.admin.model.Staff;
import com.admin.service.StaffService;
import com.admin.exception.StaffNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class StaffController {
    @Autowired
    private StaffService staffService;  // Dịch vụ quản lý nhân viên

    // Hiển thị danh sách tất cả nhân viên
    @GetMapping("/manageStaff")
    public String showStaffList(Model model) {
        List<Staff> listStaff = staffService.listAll(); // Lấy danh sách nhân viên từ dịch vụ
        model.addAttribute("listStaff", listStaff); // Đưa danh sách vào model để hiển thị trong view

        return "admin/manageStaff"; // Trả về view manageStaff.html
    }

    // Hiển thị form để thêm nhân viên mới
    @GetMapping("/manageStaff/new")
    public String showNewForm(Model model) {
        model.addAttribute("staff", new Staff()); // Tạo đối tượng Staff mới cho form
        model.addAttribute("pageTitle", "Add New Staff"); // Thiết lập tiêu đề trang
        return "admin/staff_form"; // Trả về view staff_form.html để thêm nhân viên
    }

    // Lưu thông tin nhân viên
    @PostMapping("/manageStaff/save")
    public String saveStaff(Staff staff, RedirectAttributes ra) {
        staffService.save(staff); // Lưu nhân viên vào cơ sở dữ liệu
        ra.addFlashAttribute("message", "The staff has been saved successfully."); // Thông báo lưu thành công
        return "redirect:/manageStaff"; // Chuyển hướng về trang danh sách nhân viên
    }

    // Hiển thị form để chỉnh sửa thông tin nhân viên
    @GetMapping("/manageStaff/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Staff staff = staffService.get(id); // Lấy thông tin nhân viên theo ID
            model.addAttribute("staff", staff); // Đưa thông tin nhân viên vào model để hiển thị trong form
            model.addAttribute("pageTitle", "Edit Staff (ID: " + id + ")"); // Thiết lập tiêu đề trang
            return "staff_form"; // Trả về view staff_form.html để chỉnh sửa nhân viên
        } catch (StaffNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Thông báo lỗi nếu không tìm thấy nhân viên
            return "redirect:/manageStaff"; // Chuyển hướng về trang danh sách nhân viên
        }
    }

    // Xóa nhân viên theo ID
    @GetMapping("/manageStaff/delete/{id}")
    public String deleteStaff(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            staffService.delete(id); // Xóa nhân viên theo ID
            ra.addFlashAttribute("message", "The staff ID " + id + " has been deleted."); // Thông báo xóa thành công
        } catch (StaffNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Thông báo lỗi nếu không tìm thấy nhân viên
        }
        return "redirect:/manageStaff"; // Chuyển hướng về trang danh sách nhân viên
    }
}
