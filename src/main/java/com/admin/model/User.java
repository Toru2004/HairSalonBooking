package com.admin.model;

import javax.persistence.*;

// Đánh dấu lớp này là một thực thể trong cơ sở dữ liệu với tên bảng là "users"
@Entity
@Table(name = "users")
public class User {
    // Định nghĩa cột khóa chính với giá trị tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    // Định nghĩa cột 'username' với độ dài tối đa là 45, không cho phép null
    @Column(length = 45, nullable = false, name = "username")
    private String username;

    // Định nghĩa cột 'email' với độ dài tối đa là 45, không cho phép null và đảm bảo giá trị là duy nhất
    @Column(nullable = false, unique = true, length = 45)
    private String email;

    // Định nghĩa cột 'phoneNumber' với độ dài tối đa là 15, không cho phép null và đảm bảo giá trị là duy nhất
    @Column(nullable = false, unique = true, length = 15)
    private String phoneNumber;

    // Định nghĩa cột 'password' với độ dài tối đa là 15, không cho phép null
    @Column(length = 15, nullable = false)
    private String password;

    @Column(length = 15, nullable = false)
    private String role;  // Thêm thuộc tính role

    // Định nghĩa cột 'enabled' để lưu trạng thái kích hoạt của tài khoản (true = kích hoạt)
    private boolean enabled;

    // Thiết lập quan hệ một-một với thực thể Customer, liên kết ngược bằng khóa ngoại trong Customer với tên thuộc tính 'user'
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Customer customer;

    // Quan hệ một-một với Stylist
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Stylist stylist;

    //Quan hệ 1-1 với admin
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Admin admin;

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
        if (admin != null) {
            admin.setUser(this);
        }
    }



    // Getter và Setter cho từng thuộc tính của User
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Customer getCustomer() {
        return customer;
    }

    // Khi thiết lập Customer, đồng thời gán đối tượng User cho thuộc tính 'user' trong Customer
    public void setCustomer(Customer customer) {
        this.customer = customer;
        if (customer != null) {
            customer.setUser(this);
        }
    }
    public Stylist getStylist() {
        return stylist;
    }

    public void setStylist(Stylist stylist) {
        this.stylist = stylist;
        if (stylist != null) {
            stylist.setUser(this);
        }
    }

    // Phương thức toString để hiển thị thông tin User dưới dạng chuỗi
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
