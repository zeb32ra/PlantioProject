package com.example.Plantio.service;

import com.example.Plantio.model.FertilizerModel;
import com.example.Plantio.repository.FertilizerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryFertilizerServiceImpl implements FertilizerService {
    private final FertilizerRepository fertilizerRepository;

    public InMemoryFertilizerServiceImpl(FertilizerRepository fertilizerRepository) {
        this.fertilizerRepository = fertilizerRepository;
    }

    @Override
    public List<FertilizerModel> getAllFertilizers() {
        return fertilizerRepository.findAll();
    }

    @Override
    public FertilizerModel getFertilizerById(UUID id) {
        return fertilizerRepository.findById(id).orElse(null);
    }

    @Override
    public FertilizerModel addFertilizer(FertilizerModel fertilizer) {
        return fertilizerRepository.save(fertilizer);
    }

    @Override
    public FertilizerModel updateFertilizer(FertilizerModel fertilizer) {
        if(fertilizerRepository.existsById(fertilizer.getId())) {
            return fertilizerRepository.save(fertilizer);
        }
        return null;
    }

    @Override
    public void deleteFertilizer(UUID id) {
        if(fertilizerRepository.existsById(id)) {
            fertilizerRepository.deleteById(id);
        }
    }
}
