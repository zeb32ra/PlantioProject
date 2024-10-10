package com.example.Plantio.repository;

import com.example.Plantio.model.PlantCareModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlantCareRepository extends JpaRepository<PlantCareModel, UUID> {
}
