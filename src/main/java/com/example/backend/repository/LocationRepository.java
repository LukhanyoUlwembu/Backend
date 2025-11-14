package com.example.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.backend.model.Location;

public interface LocationRepository extends JpaRepository<Location,Long>{
public Location findBylocationId(long id);

}