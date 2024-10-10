package com.example.Plantio.repository;

import com.example.Plantio.model.LocationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LocationRepository extends JpaRepository<LocationModel, UUID> {
}
