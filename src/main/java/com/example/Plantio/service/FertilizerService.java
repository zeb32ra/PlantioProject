package com.example.Plantio.service;

import com.example.Plantio.model.FertilizerModel;

import java.util.List;
import java.util.UUID;

public interface FertilizerService {
    public List<FertilizerModel> getAllFertilizers();
    public FertilizerModel getFertilizerById(UUID id);
    public FertilizerModel addFertilizer(FertilizerModel fertilizer);
    public FertilizerModel updateFertilizer(FertilizerModel fertilizer);
    public void deleteFertilizer(UUID id);
}
