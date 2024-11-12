package com.admin.model;

import javax.persistence.*;

// Đánh dấu lớp này là một thực thể trong cơ sở dữ liệu với tên bảng là "stylists"
@Entity
@Table(name = "stylists")
public class Stylist {

    // Định nghĩa cột khóa chính với giá trị tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Tạo liên kết một-một với bảng User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // Thuộc tính bổ sung
    private String specialties;
    private boolean available;

    // Getters và Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSpecialties() {
        return specialties;
    }

    public void setSpecialties(String specialties) {
        this.specialties = specialties;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Stylist{" +
                "id=" + id +
                ", user id=" + user.getId() +
                ", user=" + user.getUsername() +
                ", specialties='" + specialties + '\'' +
                ", available=" + available +
                '}';
    }
}
