package com.example.Plantio.repository;

import com.example.Plantio.model.FertilizerApplicationModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FertilizerApplicationRepository extends JpaRepository<FertilizerApplicationModel, UUID> {
}
