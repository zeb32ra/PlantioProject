package com.example.Plantio.service;

import com.example.Plantio.model.PlantModel;

import java.util.List;
import java.util.UUID;

public interface PlantService {
    public List<PlantModel> getAllPlants();
    public PlantModel getPlantById(UUID id);
    public PlantModel addPlant(PlantModel plantModel);
    public PlantModel updatePlant(PlantModel plantModel);
    public void deletePlant(UUID id);
}
