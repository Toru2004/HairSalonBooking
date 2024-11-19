package com.admin.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "stylist_id")
    private Stylist stylist;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany
    @JoinTable(
            name = "appointment_care",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "care_id")
    )
    private List<Care> cares;  // Thay đổi từ @ManyToOne thành @ManyToMany

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;  // Enum Status

    @Column(name = "total_price")
    private Double totalPrice;

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Stylist getStylist() {
        return stylist;
    }

    public void setStylist(Stylist stylist) {
        this.stylist = stylist;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Care> getCares() {
        return cares;
    }

    public void setCares(List<Care> cares) {
        this.cares = cares;
        calculateTotalPrice();  // Tính toán lại tổng giá mỗi khi danh sách cares thay đổi
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public Status getStatus() {
        return status;
    }





    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Tính toán tổng giá dựa trên các dịch vụ
    private void calculateTotalPrice() {
        if (cares != null) {
            totalPrice = cares.stream().mapToDouble(Care::getPrice).sum();
        } else {
            totalPrice = 0.0;
        }
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", stylist=" + stylist +
                ", customer=" + customer +
                ", cares=" + cares +
                ", appointmentDate=" + appointmentDate +
                ", status=" + status +
                ", totalPrice=" + totalPrice +
                '}';
    }

    // Enum Status cho các trạng thái cuộc hẹn
    public enum Status {
        PENDING,       // Đang đợi duyệt
        APPROVED,      // Đã duyệt
        IN_PROGRESS,   // Đang thực hiện
        CANCELLED,     // Đã huỷ
        COMPLETED;     // Đã thực hiện xong

        @Override
        public String toString() {
            switch (this) {
                case PENDING: return "Pending";
                case APPROVED: return "Approved";
                case IN_PROGRESS: return "In Progress";
                case CANCELLED: return "Cancelled";
                case COMPLETED: return "Completed";
                default: return "Unknown";
            }
        }
    }



}