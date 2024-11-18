package com.admin.model;

import javax.persistence.*;
import java.time.LocalDateTime;
<<<<<<< HEAD

import java.time.LocalDate;

import java.util.List;

=======
import java.time.LocalDate;
>>>>>>> 32e447fcc978edb38f93a4f083615b0194cc3b4e
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

<<<<<<< HEAD

=======
>>>>>>> 32e447fcc978edb38f93a4f083615b0194cc3b4e
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
<<<<<<< HEAD

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;  // Enum Status

    @Column(name = "total_price")
    private Double totalPrice;


    // Getters and Setters
    public Integer getId() {
        return id;
=======

    public void setStatus(Status status) {
        this.status = status;
>>>>>>> 32e447fcc978edb38f93a4f083615b0194cc3b4e
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