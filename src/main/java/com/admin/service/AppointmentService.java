package com.admin.service;

import com.admin.exception.AppointmentNotFoundException;
import com.admin.model.Appointment;
import com.admin.repository.AppointmentRepository;
import com.admin.utils.TimePeriodHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.ArrayList;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    public Map<String, Object> getRevenueDataByFilter(String filter) {
        Map<String, Object> revenueData = new HashMap<>();

        LocalDateTime startDate = TimePeriodHelper.getStartDateForPeriod(filter);
        LocalDateTime endDate = TimePeriodHelper.getEndDateForPeriod(filter);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);


        return revenueData;
    }





    public List<Appointment> getAllAppointments() {
        // Truy vấn tất cả các appointments từ cơ sở dữ liệu
        return appointmentRepository.findAll();
    }
    public Map<String, Double> calculateMonthlyRevenue(List<Appointment> appointments) {
        Map<String, Double> revenueByMonth = new HashMap<>();

        // Nhóm doanh thu theo tháng
        for (Appointment appointment : appointments) {
            String month = appointment.getDate().getMonth().toString(); // Chuyển ngày thành tháng
            Double revenue = appointment.getTotalPrice();

            // Cộng doanh thu vào từng tháng
            revenueByMonth.merge(month, revenue, Double::sum);
        }

        return revenueByMonth;
    }

    // Phương thức lấy danh sách tất cả các cuộc hẹn
    public List<Appointment> listAll() {
        return (List<Appointment>) appointmentRepository.findAll();
    }

    // Phương thức lưu một cuộc hẹn
    public void save(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    // Phương thức cập nhật một cuộc hẹn
    public void update(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    // Phương thức lấy một cuộc hẹn theo ID
    public Appointment get(Integer id) throws AppointmentNotFoundException {
        Optional<Appointment> result = appointmentRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new AppointmentNotFoundException("Could not find appointment with ID " + id);
    }

    // Phương thức xóa một cuộc hẹn
    public void delete(Integer id) throws AppointmentNotFoundException {
        Optional<Appointment> result = appointmentRepository.findById(id);
        if (result.isPresent()) {
            appointmentRepository.deleteById(id);
        } else {
            throw new AppointmentNotFoundException("Could not find appointment with ID " + id);
        }
    }


}
