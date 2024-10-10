package com.example.Plantio.service;

import com.example.Plantio.model.PlantModel;
import com.example.Plantio.repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryPlantServiceImpl implements PlantService {
    private final PlantRepository plantRepository;

    public InMemoryPlantServiceImpl(PlantRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public List<PlantModel> getAllPlants() {
        return plantRepository.findAll();
    }

    @Override
    public PlantModel getPlantById(UUID id) {
        return plantRepository.findById(id).orElse(null);
    }

    @Override
    public PlantModel addPlant(PlantModel plantModel) {
        return plantRepository.save(plantModel);
    }

    @Override
    public PlantModel updatePlant(PlantModel plantModel) {
        if(plantRepository.existsById(plantModel.getId())) {
            return plantRepository.save(plantModel);
        }
        return null;
    }

    @Override
    public void deletePlant(UUID id) {
        if(plantRepository.existsById(id)) {
            plantRepository.deleteById(id);
        }
    }
}
