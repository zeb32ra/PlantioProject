package com.example.Plantio.service;

import com.example.Plantio.model.FertilizerApplicationModel;
import com.example.Plantio.repository.FertilizerApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryFertilizerApplicationServiceImpl implements FertilizerApplicationService {
    private final FertilizerApplicationRepository fertilizerApplicationRepository;

    public InMemoryFertilizerApplicationServiceImpl(FertilizerApplicationRepository fertilizerApplicationRepository) {
        this.fertilizerApplicationRepository = fertilizerApplicationRepository;
    }

    @Override
    public List<FertilizerApplicationModel> getAllFertilizerApplications() {
        return fertilizerApplicationRepository.findAll();
    }

    @Override
    public FertilizerApplicationModel getFertilizerApplicationById(UUID id) {
        return fertilizerApplicationRepository.findById(id).orElse(null);
    }

    @Override
    public FertilizerApplicationModel addFertilizerApplication(FertilizerApplicationModel fertilizerApplicationModel) {
        return fertilizerApplicationRepository.save(fertilizerApplicationModel);
    }

    @Override
    public FertilizerApplicationModel updateFertilizerApplication(FertilizerApplicationModel fertilizerApplicationModel) {
        if(fertilizerApplicationRepository.existsById(fertilizerApplicationModel.getId())) {
            return fertilizerApplicationRepository.save(fertilizerApplicationModel);
        }
        return null;
    }

    @Override
    public void deleteFertilizerApplication(UUID id) {
        if(fertilizerApplicationRepository.existsById(id)) {
            fertilizerApplicationRepository.deleteById(id);
        }
    }
}
