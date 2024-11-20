package com.admin.model;

import javax.persistence.*;

// Đánh dấu lớp này là một thực thể trong cơ sở dữ liệu với tên bảng là "customers"
@Entity
@Table(name = "customers")
public class Customer  {

    // Định nghĩa cột khóa chính với giá trị tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Thiết lập quan hệ một-một với thực thể User, tạo khóa ngoại "user_id" tham chiếu đến cột "id" của bảng User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;  // Khóa ngoại liên kết đến bảng User

    // Getter và Setter cho thuộc tính 'id'
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter và Setter cho thuộc tính 'user'
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Phương thức toString để hiển thị thông tin Customer dưới dạng chuỗi
    @Override
    public String toString() {
       return "Customer{" +
                "id=" + id +
                ", user id=" + user.getId() +
                ", username=" + user.getUsername() +
                ", email='" + user.getEmail() + '\'' +
                ", phone number='" + user.getPhoneNumber() + '\'' +
                ", enabled=" + user.isEnabled() +
                '}';
    }
}
