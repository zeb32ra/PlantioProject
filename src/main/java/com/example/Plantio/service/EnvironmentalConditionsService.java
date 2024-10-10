package com.example.Plantio.service;

import com.example.Plantio.model.EnvironmentalConditionsModel;

import java.util.List;
import java.util.UUID;

public interface EnvironmentalConditionsService {
    public List<EnvironmentalConditionsModel> getAllEnvironmentalConditions();
    public EnvironmentalConditionsModel getEnvironmentalConditionById(UUID id);
    public EnvironmentalConditionsModel addEnvironmentalCondition(EnvironmentalConditionsModel environmentalConditionsModel);
    public EnvironmentalConditionsModel updateEnvironmentalCondition(EnvironmentalConditionsModel environmentalConditionsModel);
    public void deleteEnvironmentalCondition(UUID id);
}
