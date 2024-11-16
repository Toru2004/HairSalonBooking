package com.admin.model;

import javax.persistence.*;

@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", user id=" + user.getId() +
                ", username=" + user.getUsername() + '\'' +
                ", email='" + user.getEmail() + '\'' +
                ", phone number='" + user.getPhoneNumber() + '\'' +
                ", available=" + user.isEnabled() +  // Access available via the user object
                '}';
    }
}

