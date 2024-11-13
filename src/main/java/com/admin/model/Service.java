package com.admin.model;
import javax.persistence.*;

@Entity
@Table(name = "services")

public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Tên của dịch vụ, ví dụ: "Cắt tóc nam".
    private String description; // Mô tả chi tiết về dịch vụ.
    private Double price; // Giá của dịch vụ.


    public Service() {}


    public Service(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}


