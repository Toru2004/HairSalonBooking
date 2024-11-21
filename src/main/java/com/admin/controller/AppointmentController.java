package com.admin.controller;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

import org.springframework.ui.Model;
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

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

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
    @GetMapping("/revenue")
    public String showRevenueByMonth(@RequestParam(value = "year", defaultValue = "2024") int year,
                                     Model model) {
        // Lấy doanh thu theo tháng từ Service
        List<Object[]> revenueData = appointmentService.getRevenueByMonth(year);

        // Kiểm tra dữ liệu trả về
        if (revenueData != null) {
            for (Object[] data : revenueData) {
                System.out.println("Month: " + data[0] + ", Revenue: " + data[1]);
            }
        } else {
            System.out.println("No data found for year " + year);
        }

        // Chuyển dữ liệu doanh thu thành các danh sách riêng biệt cho Chart.js
        List<Integer> months = new ArrayList<>();
        List<Double> revenues = new ArrayList<>();

        for (Object[] data : revenueData) {
            months.add((Integer) data[0]);
            revenues.add((Double) data[1]);
        }

        // Truyền dữ liệu vào model
        model.addAttribute("months", months);
        model.addAttribute("revenues", revenues);
        model.addAttribute("selectedYear", year);


        return "manager/managerDashboard"; // Trả về View để hiển thị
    }


    @GetMapping("/manageAppointments/byMonth")
    public String showAppointmentsByMonth(@RequestParam("year") int year, @RequestParam("month") int month, HttpServletRequest request, Model model) {
        // Get appointments by year and month
        List<Appointment> listAppointments = appointmentService.listAll();
        List<Appointment> appointments = appointmentService.getAppointmentsByMonth(year, month);

        // Calculate the total price for the month
        double totalPriceMonth = appointments.stream()
                .mapToDouble(Appointment::getTotalPrice)
                .sum();

        model.addAttribute("listAppointments", appointments);
        model.addAttribute("statuses", Arrays.asList(Appointment.Status.values()));
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedMonth", month);
        model.addAttribute("totalPriceMonth", totalPriceMonth);  // Add total price to the model
        // Lấy vai trò từ session
        String role = (String) request.getSession().getAttribute("role");

        // Kiểm tra nếu không phải staff thì chuyển hướng
        if (role == null || !role.equals("manager")) {
            return "redirect:/page/login"; // Chuyển hướng đến trang Access Denied
        }

        return "admin/appointmentFilterForm";
    }





    @GetMapping("/manageAppointments")
    public String showManageAppointmentsPage(Model model) {
        // Lấy danh sách tất cả các cuộc hẹn
        List<Appointment> listAppointments = appointmentService.listAll();

        // Lấy ngày tháng năm hiện tại
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();
        // Thêm thông tin vào model
        model.addAttribute("listAppointments", listAppointments);
        model.addAttribute("statuses", Arrays.asList(Appointment.Status.values())); // Thêm danh sách trạng thái
        model.addAttribute("currentYear", currentYear);
        model.addAttribute("currentMonth", currentMonth);

        // Trả về view manageAppointments.html
        return "admin/manageAppointments";
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


    @GetMapping("/appointments")
    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = appointmentService.getAllAppointments();

        // Đảm bảo tất cả các appointment đều có trạng thái, nếu không gán là PENDING
        appointments.forEach(appointment -> {
            if (appointment.getStatus() == null) {
                appointment.setStatus(Appointment.Status.PENDING);
            }
        });

        return appointments;
    }


}
