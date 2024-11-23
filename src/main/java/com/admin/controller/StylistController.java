package com.admin.controller;

import com.admin.exception.StylistNotFoundException;
import com.admin.exception.UserNotFoundException;
import com.admin.model.Appointment;
import com.admin.model.Stylist;
import com.admin.model.User;
import com.admin.service.AppointmentService;
import com.admin.service.StylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;





@Controller
public class StylistController {
    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private StylistService stylistService;//Service để lấy dữ liệu từ DB

    @GetMapping("/stylist/stylistDashboard")
    public String openDashboard() {
        return "stylist/stylistDashboard";
    }


    @GetMapping("/stylist/stylistAppointment")
    public String getAllAppointmentsForStylist(Model model) {
        // Lấy toàn bộ các cuộc hẹn trong hệ thống
//        List<Appointment> allAppointments = appointmentService.findAll();
//
//        // Đưa danh sách vào model
//        model.addAttribute("listAppointments", allAppointments);

        return "stylist/stylistAppointment";
    }




    // Hiển thị danh sách tất cả stylist
    @GetMapping("/manageStylists")
    public String showStylistList(HttpServletRequest request, Model model) {
        List<Stylist> listStylists = stylistService.listAll();
        model.addAttribute("listStylists", listStylists);

        // Lấy vai trò từ session
        String role = (String) request.getSession().getAttribute("role");

        // Kiểm tra nếu không phải staff thì chuyển hướng
        if (role == null || !role.equals("admin")) {
            return "redirect:/page/login"; // Chuyển hướng đến trang Access Denied
        }

        return "admin/manageStylists";//trả về trang html
    }

    // Hiển thị danh sách stylist cho khách hàng
    @GetMapping("/page/stylists")
    public String listStylists(Model model) {
        List<Stylist> listStylists = stylistService.findEnabledStylists();
        model.addAttribute("listStylists", listStylists);
        return "view/pages/stylists";
    }

    @GetMapping("/page/bookNow")
    public String showBookNowPage() {
        return "view/pages/bookNow"; // Đường dẫn đến file bookNow.html
    }

    // Hiển thị form thêm mới stylist
    @GetMapping("/manageStylists/new")
    public String showNewForm(Model model) {
        model.addAttribute("stylist", new Stylist());
        model.addAttribute("pageTitle", "Add New Stylist");
        return "admin/stylist_form";//trả về form stylist
    }

    @GetMapping("/manageStylists/image/{id}")
    public ResponseEntity<byte[]> getStylistImage(@PathVariable("id") Integer id) throws StylistNotFoundException {/// Lấy stylist từ service
        Stylist stylist = stylistService.get(id);

        // Lấy byte[] của ảnh từ stylist
        byte[] image = stylist.getProfilePicture();

        // Kiểm tra nếu ảnh không tồn tại
        if (image == null) {
            throw new StylistNotFoundException("Image not found for stylist with ID " + id);
        }

        // Thiết lập header cho Content-Type của ảnh
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG); // Hoặc MediaType.IMAGE_JPEG nếu ảnh là JPG

        // Trả về ảnh dưới dạng ResponseEntity với header thích hợp
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }



    // Lưu thông tin stylist
    @PostMapping("/manageStylists/save")
    public String saveStylist(
            @ModelAttribute Stylist stylist,
            @RequestParam("imageFile") MultipartFile imageFile,
            RedirectAttributes ra) {
        try {
            if (stylist.getId() != null) { // Nếu có ID thì thực hiện cập nhật
                Stylist existingStylist = stylistService.get(stylist.getId());
                existingStylist.getUser().setUsername(stylist.getUser().getUsername());
                existingStylist.getUser().setEmail(stylist.getUser().getEmail());
                existingStylist.getUser().setPhoneNumber(stylist.getUser().getPhoneNumber());
                existingStylist.getUser().setPassword(stylist.getUser().getPassword());
                existingStylist.getUser().setEnabled(stylist.getUser().isEnabled());
//                existingStylist.setUser(stylist.getUser());

                if (!imageFile.isEmpty()) {
                    existingStylist.setProfilePicture(imageFile.getBytes()); // Cập nhật ảnh profile
                }

                stylistService.save(existingStylist); // Lưu stylist đã chỉnh sửa
            } else {
                // Nếu không có ID, thêm mới
                if (!imageFile.isEmpty()) {
                    stylist.setProfilePicture(imageFile.getBytes());
                }
                stylist.getUser().setRole("stylist");
                stylistService.save(stylist);
            }
            ra.addFlashAttribute("message", "The stylist has been saved successfully.");
        } catch (IOException e) {
            ra.addFlashAttribute("message", "Error uploading image: " + e.getMessage());
        } catch (StylistNotFoundException e) {
            ra.addFlashAttribute("message", "Failed to save the stylist: " + e.getMessage());
        }
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

