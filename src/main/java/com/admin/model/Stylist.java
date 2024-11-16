package com.admin.model;

import javax.persistence.*;

// Đánh dấu lớp này là một thực thể trong cơ sở dữ liệu với tên bảng là "stylists"
@Entity
@Table(name = "stylists")
public class Stylist  {

    // Định nghĩa cột khóa chính với giá trị tự động tăng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    // Thiết lập quan hệ một-một với thực thể User, tạo khóa ngoại "user_id" tham chiếu đến cột "id" của bảng User
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;  // Khóa ngoại liên kết đến bảng



    // Getter và Setter cho thuộc tính 'user'
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // Getter và Setter cho thuộc tính 'id'
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    // Định nghĩa cột 'enabled' để lưu trạng thái kích hoạt của tài khoản (true = kích hoạt)
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



    // Các thuộc tính riêng biệt khác của Stylist
    private boolean available;
    private String specialization;

    @Lob
    @Column(name = "profile_picture", columnDefinition = "LONGBLOB")
    private byte[] profilePicture;


    private String description; // Mô tả chi tiết của stylist


    // Getter và Setter cho các thuộc tính khác của Stylist
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        this.profilePicture = profilePicture;
    }

    // Getter và Setter cho thuộc tính 'description'
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    // Phương thức toString để hiển thị thông tin Stylist dưới dạng chuỗi
    @Override
    public String toString() {
        return "Stylist{" +
                "id=" + id +
                ", user id=" + user.getId() +
                ", username=" + user.getUsername() + '\'' +
                ", email='" + user.getEmail() + '\'' +
                ", phone number='" + user.getPhoneNumber() + '\'' +
                ", available=" + available +
                ", specialization='" + specialization + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
