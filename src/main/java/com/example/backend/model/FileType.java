package com.example.backend.model;

import jakarta.persistence.*;

@Entity
public class FileType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FileOwnerType ownerType;

    public FileType() {}

    public FileType(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileOwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(FileOwnerType ownerType) {
        this.ownerType = ownerType;
    }
}

