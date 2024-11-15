package com.admin.controller;

/*
import com.admin.model.Appointment;
import com.admin.model.Service;
import com.admin.service.AppointmentService;
import com.admin.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private ServiceService serviceService;

    @GetMapping("/manager/revenue")
    public String showRevenuePage() {
        return "managerRevenue";
    }

    @GetMapping("/manager/revenueData")
    public String getRevenueData(@RequestParam String period, Model model) {

        List<Service> services = serviceService.getAllServices();
        List<Appointment> appointments = appointmentService.getAppointmentsByPeriod(period);

        model.addAttribute("services", services);
        model.addAttribute("appointments", appointments);

        return "managerRevenue";
    }

    @GetMapping("/manager/staff")
    public String showManagedStaff(Model model) {

        model.addAttribute("staff",);
        return "manageStaff";
    }
}
*/