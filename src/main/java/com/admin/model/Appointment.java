package com.admin.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
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

    @ManyToOne
    @JoinColumn(name = "care_id")
    private Care care;

    @Column(name = "appointment_date")
    private LocalDateTime appointmentDate;

    private LocalDate date; // Nếu là LocalDate, hoặc có thể là Date hoặc Timestamp.

    public LocalDate getDate() {
        return date;
    }

    // Getter and Setter
    public Integer getId() {
        return id;
    }
    public enum Status {
        SCHEDULED, COMPLETED, CANCELLED
    }

    private Status status;  // Enum Status

    // Getters and Setters

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    private Double totalPrice;

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
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

    public Care getCare() {
        return care;
    }

    public void setCare(Care care) {
        this.care = care;
    }

    public LocalDateTime getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDateTime appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", stylist=" + stylist +
                ", customer=" + customer +
                ", care=" + care +
                ", appointmentDate=" + appointmentDate +
                '}';
    }
}
