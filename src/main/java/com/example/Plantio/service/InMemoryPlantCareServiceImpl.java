package com.example.Plantio.service;

import com.example.Plantio.model.PlantCareModel;
import com.example.Plantio.repository.PlantCareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryPlantCareServiceImpl implements PlantCareService {
    private final PlantCareRepository plantCareRepository;

    public InMemoryPlantCareServiceImpl(PlantCareRepository plantCareRepository) {
        this.plantCareRepository = plantCareRepository;
    }

    @Override
    public List<PlantCareModel> getAllPlantCares() {
        return plantCareRepository.findAll();
    }

    @Override
    public PlantCareModel getPlantCareById(UUID id) {
        return plantCareRepository.findById(id).orElse(null);
    }

    @Override
    public PlantCareModel addPlantCare(PlantCareModel plantCareModel) {
        return plantCareRepository.save(plantCareModel);
    }

    @Override
    public PlantCareModel updatePlantCare(PlantCareModel plantCareModel) {
        if(plantCareRepository.existsById(plantCareModel.getId())) {
            return plantCareRepository.save(plantCareModel);
        }
        return null;
    }

    @Override
    public void deletePlantCare(UUID id) {
        if(plantCareRepository.existsById(id)) {
            plantCareRepository.deleteById(id);
        }
    }
}
