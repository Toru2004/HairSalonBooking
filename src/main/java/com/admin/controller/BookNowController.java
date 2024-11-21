package com.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.admin.model.Appointment;
import com.admin.model.Stylist;
import com.admin.model.Care;
import com.admin.service.AppointmentService;
import com.admin.service.StylistService;
import com.admin.service.CareService;
import org.springframework.ui.Model;

import java.util.List;

@RestController
@RequestMapping("/api/booknow")
public class BookNowController {

    @Autowired
    private StylistService stylistService;

    @Autowired
    private CareService careService;

    @Autowired
    private AppointmentService appointmentService; // Dịch vụ để xử lý Appointment

    // Lấy danh sách stylist
    @GetMapping("/stylists")
    public List<Stylist> getStylists() {
        return stylistService.listAll();
    }

    // Lấy danh sách care services
    @GetMapping("/cares")
    public ResponseEntity<List<Care>> getCares() {
        List<Care> cares = careService.getAllCares();
        return ResponseEntity.ok(cares);
    }

    @GetMapping("/booknow")
    public String showBookNowForm(Model model) {
        Appointment appointment = new Appointment();
        model.addAttribute("appointment", appointment);
        model.addAttribute("stylistList", stylistService.listAll()); // Truyền danh sách stylist
        model.addAttribute("careList", careService.listAll()); // Truyền danh sách dịch vụ
        return "booknow"; // Trả về view
    }

    @PostMapping("/booknow/save")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
        // Xử lý lưu lịch hẹn ở đây
        return "redirect:/success"; // Chuyển hướng sau khi lưu thành công
    }




}

