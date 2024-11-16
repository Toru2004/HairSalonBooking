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

    // Phương thức kiểm tra trạng thái cuộc hẹn có thể thay đổi hay không
    public boolean canEdit() {
        return !(status == Status.CANCELLED || status == Status.COMPLETED || status == Status.IN_PROGRESS);
    }

    // Phương thức kiểm tra trạng thái cuộc hẹn có thể xóa hay không
    public boolean canDelete() {
        return status != Status.IN_PROGRESS;
    }

    public void changeStatus(Status newStatus) {
        if (canChangeStatus(newStatus)) {
            this.status = newStatus;
        } else {
            throw new IllegalStateException("Cannot change to the new status from the current one.");
        }
    }


    // Phương thức kiểm tra có thể chuyển sang trạng thái mới hay không
    public boolean canChangeStatus(Status newStatus) {
        if (this.status == Status.CANCELLED || this.status == Status.COMPLETED || this.status == Status.IN_PROGRESS) {
            return false;  // Không thể chuyển lại từ trạng thái đã huỷ, đã hoàn thành hoặc đang thực hiện
        }
        if (this.status == Status.PENDING && newStatus == Status.APPROVED) {
            return true;  // Từ Pending có thể chuyển sang Approved
        }
        if (this.status == Status.APPROVED && newStatus == Status.IN_PROGRESS) {
            return true;  // Từ Approved có thể chuyển sang In Progress
        }
        if (this.status == Status.IN_PROGRESS && newStatus == Status.COMPLETED) {
            return true;  // Từ In Progress có thể chuyển sang Completed
        }
        return false;  // Các trạng thái khác không hợp lệ
    }


}
