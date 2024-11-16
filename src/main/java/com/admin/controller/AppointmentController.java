package com.admin.controller;

import com.admin.model.Appointment;
import com.admin.exception.AppointmentNotFoundException;
import com.admin.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    // Hiển thị danh sách các lịch hẹn
    @GetMapping("/manageAppointments")
    public String showAppointmentList(Model model) {
        List<Appointment> listAppointments = service.listAll();
        model.addAttribute("listAppointments", listAppointments);
        return "manageAppointments"; // Hiển thị danh sách
    }

    // Hiển thị form thêm mới lịch hẹn
    @GetMapping("/manageAppointments/new")
    public String showNewForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("pageTitle", "Add New Appointment");
        return "appointment_form"; // Chuyển đến form thêm mới
    }

    // Lưu lịch hẹn
    @PostMapping("/manageAppointments/save")
    public String saveAppointment(Appointment appointment, RedirectAttributes ra) {
        service.save(appointment); // Lưu lịch hẹn vào cơ sở dữ liệu
        ra.addFlashAttribute("message", "The appointment has been saved successfully.");
        return "redirect:/manageAppointments"; // Quay lại trang danh sách
    }

    // Hiển thị form chỉnh sửa lịch hẹn
    @GetMapping("/manageAppointments/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Appointment appointment = service.get(id); // Lấy lịch hẹn từ ID
            model.addAttribute("appointment", appointment);
            model.addAttribute("pageTitle", "Edit Appointment (ID: " + id + ")");
            return "appointment_form"; // Chuyển đến form chỉnh sửa
        } catch (AppointmentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Xử lý khi không tìm thấy lịch hẹn
            return "redirect:/manageAppointments"; // Quay lại danh sách
        }
    }

    // Xóa lịch hẹn
    @GetMapping("/manageAppointments/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            service.delete(id); // Xóa lịch hẹn khỏi cơ sở dữ liệu
            ra.addFlashAttribute("message", "The appointment ID " + id + " has been deleted.");
        } catch (AppointmentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage()); // Xử lý khi không tìm thấy lịch hẹn để xóa
        }
        return "redirect:/manageAppointments"; // Quay lại danh sách
    }
}
