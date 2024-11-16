package com.admin.model;

import javax.persistence.*;

@Entity
@Table(name = "staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)  // Cascade ALL để tự động persist, merge
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;

    @ManyToOne(cascade = CascadeType.ALL)  // Cascade ALL để tự động persist, merge
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", manager=" + manager.getId() +
                ", user=" + user.getUsername() +
                ", email='" + user.getEmail() + '\'' +
                ", phone number='" + user.getPhoneNumber() + '\'' +
                ", available=" + user.isEnabled() +
                '}';
    }
}
