package com.example.Plantio.repository;

import com.example.Plantio.model.FertilizerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FertilizerRepository extends JpaRepository<FertilizerModel, UUID> {
}
