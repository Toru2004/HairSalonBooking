package com.admin.model;

import javax.persistence.*;

@Entity
@Table(name = "staff")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;  // Mỗi Staff liên kết với một User (kế thừa từ bảng users)

    @ManyToOne
    @JoinColumn(name = "manage_id", referencedColumnName = "id", nullable = false)
    private Manage manage;  // Mỗi Staff thuộc về một Manage

    // Getters and Setters
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

    public Manage getManage() {
        return manage;
    }

    public void setManage(Manage manage) {
        this.manage = manage;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", manage id=" + manage.getId() + // Nếu `manage` là đối tượng đại diện cho bản ghi trong bảng `manage`
                ", user id=" + user.getId() + // Liên kết đến bảng `users`
                ", username=" + user.getUsername() + '\'' +
                ", email='" + user.getEmail() + '\'' +
                ", phone number='" + user.getPhoneNumber() + '\'' +
                '}';
    }

}
