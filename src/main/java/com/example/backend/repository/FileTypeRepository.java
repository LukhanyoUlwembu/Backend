package com.example.backend.repository;

import com.example.backend.model.FileType;
import com.example.backend.model.FileOwnerType;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileTypeRepository extends JpaRepository<FileType, Long> {
    FileType findByName(String name);
    List<FileType> findByOwnerType(FileOwnerType ownerType);
}

