package com.example.Plantio.service;

import com.example.Plantio.model.WateringScheduleModel;
import com.example.Plantio.repository.WateringScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryWateringScheduleServiceImpl implements WateringScheduleService {
    private final WateringScheduleRepository wateringScheduleRepository;

    public InMemoryWateringScheduleServiceImpl(WateringScheduleRepository wateringScheduleRepository) {
        this.wateringScheduleRepository = wateringScheduleRepository;
    }

    @Override
    public List<WateringScheduleModel> getAllWateringSchedules() {
        return wateringScheduleRepository.findAll();
    }

    @Override
    public WateringScheduleModel getWateringScheduleById(UUID id) {
        return wateringScheduleRepository.findById(id).orElse(null);
    }

    @Override
    public WateringScheduleModel addWateringSchedule(WateringScheduleModel wateringScheduleModel) {
        return wateringScheduleRepository.save(wateringScheduleModel);
    }

    @Override
    public WateringScheduleModel updateWateringSchedule(WateringScheduleModel wateringScheduleModel) {
        if(wateringScheduleRepository.findById(wateringScheduleModel.getId()).isPresent()) {
            return wateringScheduleRepository.save(wateringScheduleModel);
        }
        return null;
    }

    @Override
    public void deleteWateringSchedule(UUID id) {
        if(wateringScheduleRepository.findById(id).isPresent()) {
            wateringScheduleRepository.deleteById(id);
        }
    }
}
