package com.admin.controller;

import com.admin.exception.StylistNotFoundException;
import com.admin.model.Appointment;
import com.admin.model.Stylist;
import com.admin.service.AppointmentService;
import com.admin.service.StylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import com.admin.repository.StylistRepository;

import com.admin.repository.AppointmentRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/stylist") // Đặt tiền tố URL cho Stylist
public class StylistController {

    @Autowired
    private AppointmentRepository appointmentRepository;


    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private StylistRepository stylistRepository;

    @Autowired
    private StylistService stylistService;

    // Dashboard của stylist
    @GetMapping("/stylistDashboard")
    public String openDashboard() {
        return "stylist/stylistDashboard";
    }

    /**
     * Lấy danh sách tất cả các cuộc hẹn liên quan đến stylist đang đăng nhập.
     * URL: /stylist/stylistAppointments
     */
    @GetMapping("/stylistAppointments")
    public String getAllAppointmentsForStylist(HttpServletRequest request, Model model) {
        // Lấy stylist từ session (username hoặc id của stylist)
        String username = (String) request.getSession().getAttribute("username");

        if (username == null) {
            // Nếu không tìm thấy username trong session, chuyển hướng đến trang login
            return "redirect:/page/login";
        }

        // Lấy Stylist dựa trên username
        Stylist stylist = stylistService.findByUsername(username);
        if (stylist == null) {
            // Nếu không tìm thấy stylist, thông báo lỗi và quay lại Dashboard
            model.addAttribute("errorMessage", "Stylist not found. Please contact admin.");
            return "stylist/stylistDashboard";
        }

        // Lấy danh sách các Appointment liên quan đến Stylist
        List<Appointment> listAppointments = appointmentService.findByStylist(stylist);

        // Đưa danh sách Appointment vào model
        model.addAttribute("listAppointments", listAppointments);
        model.addAttribute("stylistName", stylist.getUser().getUsername());

        return "stylist/stylistAppointment"; // Trả về trang quản lý Appointment
    }

    /**
     * Hiển thị danh sách tất cả stylist (chỉ dành cho Admin).
     * URL: /manageStylists
     */
    @GetMapping("/manageStylists")
    public String showStylistList(HttpServletRequest request, Model model) {
        // Lấy vai trò từ session
        String role = (String) request.getSession().getAttribute("role");

        // Kiểm tra nếu không phải admin thì chuyển hướng
        if (role == null || !role.equals("admin")) {
            return "redirect:/page/login";
        }

        // Lấy danh sách stylist
        List<Stylist> listStylists = stylistService.listAll();
        model.addAttribute("listStylists", listStylists);

        return "admin/manageStylists"; // Trả về trang quản lý stylist
    }

    /**
     * Hiển thị danh sách stylist cho khách hàng.
     * URL: /page/stylists
     */
    @GetMapping("/page/stylists")
    public String listStylists(Model model) {
        List<Stylist> listStylists = stylistService.findEnabledStylists();
        model.addAttribute("listStylists", listStylists);
        return "view/pages/stylists";
    }

    /**
     * Hiển thị trang đặt lịch hẹn (dành cho khách hàng).
     * URL: /page/bookNow
     */
    @GetMapping("/page/bookNow")
    public String showBookNowPage() {
        return "view/pages/bookNow";
    }

    /**
     * Hiển thị form thêm mới stylist.
     * URL: /manageStylists/new
     */
    @GetMapping("/manageStylists/new")
    public String showNewForm(Model model) {
        model.addAttribute("stylist", new Stylist());
        model.addAttribute("pageTitle", "Add New Stylist");
        return "admin/stylist_form";
    }

    /**
     * Lấy ảnh profile của stylist.
     * URL: /manageStylists/image/{id}
     */
    @GetMapping("/manageStylists/image/{id}")
    @ResponseBody
    public byte[] getStylistImage(@PathVariable("id") Integer id) throws StylistNotFoundException {
        Stylist stylist = stylistService.get(id);
        return stylist.getProfilePicture(); // Trả về ảnh dưới dạng byte[]
    }

    /**
     * Lưu thông tin stylist (thêm mới hoặc cập nhật).
     * URL: /manageStylists/save
     */
    @PostMapping("/manageStylists/save")
    public String saveStylist(
            @ModelAttribute Stylist stylist,
            @RequestParam(value = "imageFile", required = false) MultipartFile imageFile,
            Model model) {
        try {
            if (!imageFile.isEmpty()) {
                stylist.setProfilePicture(imageFile.getBytes()); // Lưu ảnh nếu có
            }
            stylist.getUser().setRole("stylist"); // Đặt vai trò stylist
            stylistService.save(stylist);
            model.addAttribute("message", "The stylist has been saved successfully.");
        } catch (Exception e) {
            model.addAttribute("message", "Error saving stylist: " + e.getMessage());
        }
        return "redirect:/manageStylists";
    }

    @GetMapping("/stylistAppointments/byStylist/{username}")
    public String viewStylistAppointmentsByUsername(@PathVariable String username, Model model) {
        // Tìm stylist theo username
        Stylist stylist = stylistRepository.findByUserUsername(username);

        // Lấy danh sách appointment thuộc stylist
        List<Appointment> appointments = stylist != null ? appointmentRepository.findByStylist(stylist) : new ArrayList<>();

        model.addAttribute("listAppointments", appointments);
        return "stylist/stylistAppointments";
    }

    /**
     * Hiển thị form chỉnh sửa stylist.
     * URL: /manageStylists/edit/{id}
     */
    @GetMapping("/manageStylists/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        try {
            Stylist stylist = stylistService.get(id);
            model.addAttribute("stylist", stylist);
            model.addAttribute("pageTitle", "Edit Stylist (ID: " + id + ")");
            return "admin/stylist_form";
        } catch (StylistNotFoundException e) {
            model.addAttribute("message", e.getMessage());
            return "redirect:/manageStylists";
        }
    }

    /**
     * Xóa stylist theo ID.
     * URL: /manageStylists/delete/{id}
     */
    @GetMapping("/manageStylists/delete/{id}")
    public String deleteStylist(@PathVariable("id") Integer id, Model model) {
        try {
            stylistService.delete(id);
            model.addAttribute("message", "The stylist ID " + id + " has been deleted.");
        } catch (StylistNotFoundException e) {
            model.addAttribute("message", e.getMessage());
        }
        return "redirect:/manageStylists";
    }
}
