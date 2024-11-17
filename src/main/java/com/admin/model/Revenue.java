package com.admin.model;

import javax.persistence.*;

@Entity
@Table(name = "Revenue")
public class Revenue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double totalRevenue;
    private String serviceType;
    private String timePeriod;

    // Constructors, Getters and Setters
}
