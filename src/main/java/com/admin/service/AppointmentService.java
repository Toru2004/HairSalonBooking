package com.admin.service;

import com.admin.exception.AppointmentNotFoundException;
import com.admin.model.Appointment;
import com.admin.repository.AppointmentRepository;
import com.admin.utils.TimePeriodHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.Month;
import java.time.LocalDateTime;
import java.util.List;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;


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


    // Phương thức trả về danh sách cuộc hẹn trong tháng cụ thể
    public List<Appointment> getAppointmentsByMonth(int year, int month) {
        // Lấy ngày đầu và ngày cuối của tháng
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime endDate = startDate.plusMonths(1).minusDays(1).withHour(23).withMinute(59).withSecond(59);

        return appointmentRepository.findAppointmentsByAppointmentDateBetween(startDate, endDate);
    }


    // Phương thức này trả về tất cả các Appointment
    public List<Appointment> getAllAppointments() {
        // Truy vấn tất cả các appointments từ cơ sở dữ liệu
        return appointmentRepository.findAll();
    }

    public Map<String, Double> calculateMonthlyRevenue(List<Appointment> appointments) {
        Map<String, Double> revenueByMonth = new HashMap<>();

        // Nhóm doanh thu theo tháng
        for (Appointment appointment : appointments) {
            // Lấy tháng từ ngày hẹn
            String month = appointment.getAppointmentDate().getMonth().toString();
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

    // Cập nhật Appointment
    public void update(Appointment appointment) {
        appointmentRepository.save(appointment);  // Sử dụng save để cập nhật
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
    public Map<String, Double> getMonthlyRevenue() {
        Map<String, Double> revenueByMonth = new HashMap<>();
        List<Appointment> appointments = appointmentRepository.findAll();  // Lấy tất cả các cuộc hẹn

        for (Appointment appointment : appointments) {
            String month = appointment.getAppointmentDate().getMonth().toString();  // Lấy tháng từ ngày hẹn
            double revenue = appointment.getTotalPrice();  // Lấy tổng doanh thu từ cuộc hẹn
            revenueByMonth.merge(month, revenue, Double::sum);  // Cộng doanh thu vào tháng
        }

        return revenueByMonth;  // Trả về dữ liệu doanh thu theo tháng
    }

    public double calculateTotalRevenueByMonth(int year, int month) {
        // Lấy ngày bắt đầu và kết thúc của tháng
        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0, 0);  // Ngày đầu tháng
        LocalDateTime endDate = startDate.withDayOfMonth(startDate.toLocalDate().lengthOfMonth()).withHour(23).withMinute(59);  // Ngày cuối tháng

        // Truy vấn tổng doanh thu trong khoảng thời gian đó
        return appointmentRepository.findTotalRevenueByTimePeriodNative(startDate, endDate);
    }
    public List<Object[]> getRevenueByMonth(int year) {
        return appointmentRepository.findRevenueByMonth(year);
    }

    public List<Appointment> findAll() {

        return List.of();
    }
}