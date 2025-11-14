package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class JobStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;
    private int orderIndex;

    public int getOrderIndex() {
       return this.orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
       this.orderIndex = orderIndex;
    }

    public JobStatus() {}

    public JobStatus(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

