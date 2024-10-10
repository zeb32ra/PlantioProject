package com.example.Plantio.service;

import com.example.Plantio.model.EnvironmentalConditionsModel;
import com.example.Plantio.repository.EnvironmentalConditionsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryEnvironmentalConditionsServiceImpl implements EnvironmentalConditionsService {
    private final EnvironmentalConditionsRepository environmentalConditionsRepository;

    public InMemoryEnvironmentalConditionsServiceImpl(EnvironmentalConditionsRepository environmentalConditionsRepository) {
        this.environmentalConditionsRepository = environmentalConditionsRepository;
    }
    @Override
    public List<EnvironmentalConditionsModel> getAllEnvironmentalConditions() {
        return environmentalConditionsRepository.findAll();
    }

    @Override
    public EnvironmentalConditionsModel getEnvironmentalConditionById(UUID id) {
        return environmentalConditionsRepository.findById(id).orElse(null);
    }

    @Override
    public EnvironmentalConditionsModel addEnvironmentalCondition(EnvironmentalConditionsModel environmentalConditionsModel) {
        return environmentalConditionsRepository.save(environmentalConditionsModel);
    }

    @Override
    public EnvironmentalConditionsModel updateEnvironmentalCondition(EnvironmentalConditionsModel environmentalConditionsModel) {
        if(environmentalConditionsRepository.existsById(environmentalConditionsModel.getId())) {
            return environmentalConditionsRepository.save(environmentalConditionsModel);
        }
        return null;
    }

    @Override
    public void deleteEnvironmentalCondition(UUID id) {
        if(environmentalConditionsRepository.existsById(id)) {
            environmentalConditionsRepository.deleteById(id);
        }
    }
}
