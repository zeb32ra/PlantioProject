package com.example.Plantio.repository;

import com.example.Plantio.model.PlantModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PlantRepository extends JpaRepository<PlantModel, UUID> {
}
