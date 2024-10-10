package com.example.Plantio.repository;

import com.example.Plantio.model.WateringScheduleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WateringScheduleRepository extends JpaRepository<WateringScheduleModel, UUID>{
}
