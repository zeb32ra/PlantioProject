package com.example.Plantio.repository;

import com.example.Plantio.model.EnvironmentalConditionsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;
import java.util.UUID;

public interface EnvironmentalConditionsRepository extends JpaRepository<EnvironmentalConditionsModel, UUID> {
}
