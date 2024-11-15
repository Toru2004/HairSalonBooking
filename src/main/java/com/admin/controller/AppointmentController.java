package com.admin.controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admin.exception.AppointmentNotFoundException;
import com.admin.model.Appointment;
import com.admin.model.Care;
import com.admin.model.Customer;
import com.admin.model.Stylist;
import com.admin.service.AppointmentService;
import com.admin.service.CareService;
import com.admin.service.CustomerService;
import com.admin.service.StylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;




import java.util.List;

@Controller
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private StylistService stylistService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CareService careService;

    // Hiển thị danh sách tất cả Appointment
    @GetMapping("/manageAppointments")
    public String showAppointmentList(Model model) {
        // Gọi phương thức listAll() để lấy danh sách tất cả các cuộc hẹn
        List<Appointment> listAppointments = appointmentService.listAll();
        model.addAttribute("listAppointments", listAppointments);
        return "admin/manageAppointments"; // Trả về view manageAppointments.html
    }
    @GetMapping("/manageAppointments/new")
    public String showNewAppointmentForm(Model model) {
        Appointment appointment = new Appointment();

        // Lấy dữ liệu từ service
        List<Customer> customers = customerService.listAll();  // Lấy danh sách khách hàng
        List<Stylist> stylists = stylistService.listAll();  // Lấy danh sách stylist
        List<Care> cares = careService.listAll();  // Lấy danh sách dịch vụ

        // Kiểm tra nếu danh sách không rỗng
        if (customers.isEmpty()) {
            System.out.println("Customer list is empty");
        }
        if (stylists.isEmpty()) {
            System.out.println("Stylist list is empty");
        }
        if (cares.isEmpty()) {
            System.out.println("Care list is empty");
        }

        // Truyền dữ liệu vào model
        model.addAttribute("appointment", appointment);
        model.addAttribute("customerList", customers);  // Truyền customer list vào model
        model.addAttribute("stylistList", stylists);  // Truyền stylist list vào model
        model.addAttribute("careList", cares);  // Truyền care list vào model
        model.addAttribute("pageTitle", "Add New Appointment");

        return "admin/appointment_form"; // Trả về view appointment_form.html
    }
    // Lưu thông tin Appointment
    @PostMapping("/manageAppointments/save")
    public String saveAppointment(Appointment appointment, RedirectAttributes ra) {
        if (appointment.getId() != null) {
            // Gọi phương thức update nếu có ID
            appointmentService.update(appointment);
            ra.addFlashAttribute("message", "The appointment has been updated successfully.");
        } else {
            // Gọi phương thức save nếu không có ID (tạo mới)
            appointmentService.save(appointment);
            ra.addFlashAttribute("message", "The appointment has been added successfully.");
        }
        return "redirect:/manageAppointments"; // Chuyển hướng về trang danh sách appointment
    }

    // Hiển thị form để chỉnh sửa thông tin Appointment
    @GetMapping("/manageAppointments/edit/{id}")
    public String showEditAppointmentForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Appointment appointment = appointmentService.get(id);
            model.addAttribute("appointment", appointment); // Truyền appointment vào model
            model.addAttribute("stylistList", stylistService.listAll());
            model.addAttribute("customerList", customerService.listAll());
            model.addAttribute("careList", careService.listAll());
            model.addAttribute("pageTitle", "Edit Appointment (ID: " + id + ")");
            return "admin/appointment_form"; // Trả về view appointment_form.html để chỉnh sửa appointment
        } catch (AppointmentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageAppointments"; // Chuyển hướng về trang danh sách appointment
        }
    }


    // Xóa Appointment
    @GetMapping("/manageAppointments/delete/{id}")
    public String deleteAppointment(@PathVariable("id") Integer id, RedirectAttributes ra) {
        try {
            appointmentService.delete(id); // Gọi phương thức xóa appointment
            ra.addFlashAttribute("message", "The appointment has been deleted successfully.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Error: " + e.getMessage());
        }
        return "redirect:/manageAppointments"; // Chuyển hướng về trang danh sách appointment
    }
}
