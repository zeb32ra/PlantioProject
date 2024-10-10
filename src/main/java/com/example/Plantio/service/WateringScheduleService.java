package com.example.Plantio.service;

import com.example.Plantio.model.WateringScheduleModel;

import java.util.List;
import java.util.UUID;

public interface WateringScheduleService {
    public List<WateringScheduleModel> getAllWateringSchedules();
    public WateringScheduleModel getWateringScheduleById(UUID id);
    public WateringScheduleModel addWateringSchedule(WateringScheduleModel wateringScheduleModel);
    public WateringScheduleModel updateWateringSchedule(WateringScheduleModel wateringScheduleModel);
    public void deleteWateringSchedule(UUID id);
}
