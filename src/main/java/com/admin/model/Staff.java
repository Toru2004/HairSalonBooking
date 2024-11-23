package com.admin.model;

import javax.persistence.*;

@Entity
@Table(name = "staffs")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;

    @OneToOne // No cascade here, User already exists in the users table
    @JoinColumn(name = "user_id", nullable = false) // Ensure user_id is not null
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
                ", manager=" + (manager != null ? manager.getId() : "No Manager") +
                ", user=" + user.getUsername() +
                ", email='" + user.getEmail() + '\'' +
                ", phoneNumber='" + user.getPhoneNumber() + '\'' +
                ", enabled=" + user.isEnabled() +
                '}';
    }
}

