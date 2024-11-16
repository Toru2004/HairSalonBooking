package com.admin.model;

import javax.persistence.*;

// This class represents a customer entity in the database with a "customers" table
@Entity
@Table(name = "customers")
public class Customer {

    // Define the primary key column with auto-incrementing value
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Define a one-to-one relationship with the User entity, which holds information such as username, email, etc.
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    // Getter and Setter for 'id'
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getter and Setter for 'user'
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // A flag to indicate whether the customer is active (enabled)
    private boolean enabled;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", user id=" + user.getId() +
                ", username=" + user.getUsername() + '\'' +
                ", email='" + user.getEmail() + '\'' +
                ", phone number='" + user.getPhoneNumber() + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
