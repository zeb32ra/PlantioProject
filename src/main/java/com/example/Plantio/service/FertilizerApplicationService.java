package com.example.Plantio.service;

import com.example.Plantio.model.FertilizerApplicationModel;

import java.util.List;
import java.util.UUID;

public interface FertilizerApplicationService {
    public List<FertilizerApplicationModel> getAllFertilizerApplications();
    public FertilizerApplicationModel getFertilizerApplicationById(UUID id);
    public FertilizerApplicationModel addFertilizerApplication(FertilizerApplicationModel fertilizerApplicationModel);
    public FertilizerApplicationModel updateFertilizerApplication(FertilizerApplicationModel fertilizerApplicationModel);
    public void deleteFertilizerApplication(UUID id);
}
