package com.admin.controller;

import com.admin.model.Appointment;
import com.admin.model.Care;
import com.admin.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@Controller
public class RevenueController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/manager/RevenueOverview")
    public String getRevenueOverview(Model model) {
        // Lấy tất cả các cuộc hẹn
        List<Appointment> listAppointments = appointmentService.listAll();

        // Tạo danh sách chi tiết dịch vụ
        List<Map<String, Object>> serviceDetails = new ArrayList<>();
        double totalRevenue = 0.0;

        int stt = 1;
        for (Appointment appointment : listAppointments) {
            Map<String, Object> entry = new HashMap<>();
            entry.put("stt", stt++);

            // Lấy danh sách các dịch vụ (Care) từ cuộc hẹn
            StringBuilder services = getStringBuilder(appointment);
            entry.put("serviceName", services.toString());

            // Định dạng ngày đặt lịch
            entry.put("appointmentDate", appointment.getAppointmentDate().toLocalDate());

            // Tổng giá tiền
            entry.put("totalPrice", String.format("%,.0f VND", appointment.getTotalPrice()));

            serviceDetails.add(entry);

            // Tính tổng doanh thu
            totalRevenue += appointment.getTotalPrice();
        }

        // Thêm dữ liệu vào model
        model.addAttribute("serviceDetails", serviceDetails);
        model.addAttribute("totalRevenue", String.format("%,.0f VND", totalRevenue));

        return "manager/RevenueOverview"; // Tên file HTML template
    }

    private static StringBuilder getStringBuilder(Appointment appointment) {
        StringBuilder services = new StringBuilder();
        List<Care> cares = appointment.getCares(); // Giả sử Appointment có liên kết với Care
        if (cares != null && !cares.isEmpty()) {
            for (int i = 0; i < cares.size(); i++) {
                services.append(cares.get(i).getName());
                if (i < cares.size() - 1) {
                    services.append(", ");
                }
            }
        } else {
            services.append("N/A");
        }
        return services;
    }
}
