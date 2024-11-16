package com.admin.controller;
import java.util.Arrays;
import java.util.stream.Collectors;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.admin.model.Appointment.Status; // Đảm bảo đã import enum Status

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
    public String showAppointments(Model model) {
        List<Appointment> appointments = appointmentService.getAllAppointments();
        model.addAttribute("appointments", appointments);
        return "appointments";  // Trả về trang danh sách cuộc hẹn
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

    @PostMapping("/manageAppointments/save")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment, RedirectAttributes ra) {
        try {
            if (appointment.getId() != null) {
                appointmentService.update(appointment); // Cập nhật nếu có ID
                ra.addFlashAttribute("message", "The appointment has been updated successfully.");
            } else {
                appointmentService.save(appointment); // Lưu mới nếu không có ID
                ra.addFlashAttribute("message", "The appointment has been added successfully.");
            }
            return "redirect:/manageAppointments"; // Chuyển hướng về danh sách appointment
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Error while saving appointment: " + e.getMessage());
            return "redirect:/manageAppointments/new"; // Quay lại form nếu có lỗi
        }
    }


    // Hiển thị form để chỉnh sửa thông tin Appointment
    // Hiển thị form để chỉnh sửa thông tin Appointment
    @GetMapping("/manageAppointments/edit/{id}")
    public String showEditAppointmentForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            // Lấy cuộc hẹn
            Appointment appointment = appointmentService.get(id);
            model.addAttribute("appointment", appointment);

            // Các danh sách khác
            model.addAttribute("stylistList", stylistService.listAll());
            model.addAttribute("customerList", customerService.listAll());
            model.addAttribute("careList", careService.listAll());

            // Truyền danh sách trạng thái dưới dạng enum
            model.addAttribute("statuses", Arrays.asList(Appointment.Status.values()));

            model.addAttribute("pageTitle", "Edit Appointment (ID: " + id + ")");
            return "admin/appointment_form";
        } catch (AppointmentNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/manageAppointments";
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

    // Xử lý cập nhật trạng thái khi form được submit
    @PostMapping("/manageAppointments/updateStatus")
    public String updateAppointmentStatus(@RequestParam("appointmentId") Integer appointmentId,
                                          @RequestParam("status") String status,
                                          RedirectAttributes ra) {
        try {
            // Lấy Appointment theo ID
            Appointment appointment = appointmentService.get(appointmentId);

            // Cập nhật trạng thái cho Appointment
            Appointment.Status newStatus = Appointment.Status.valueOf(status);
            appointment.changeStatus(newStatus);  // Gọi phương thức changeStatus từ class Appointment

            // Lưu lại vào database
            appointmentService.update(appointment);

            ra.addFlashAttribute("message", "Appointment status updated successfully.");
        } catch (Exception e) {
            ra.addFlashAttribute("message", "Error while updating status: " + e.getMessage());
        }

        // Quay lại trang danh sách cuộc hẹn
        return "redirect:/manageAppointments";
    }


}
