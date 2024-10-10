package com.example.Plantio.service;

import com.example.Plantio.model.PlantCareModel;

import java.util.List;
import java.util.UUID;

public interface PlantCareService {
    public List<PlantCareModel> getAllPlantCares();
    public PlantCareModel getPlantCareById(UUID id);
    public PlantCareModel addPlantCare(PlantCareModel plantCareModel);
    public PlantCareModel updatePlantCare(PlantCareModel plantCareModel);
    public void deletePlantCare(UUID id);
}
