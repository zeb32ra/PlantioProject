package com.example.Plantio.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "watering_schedule")
public class WateringScheduleModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String watering_time;
    private String frequency;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private PlantModel plant;

    public WateringScheduleModel() {

    }

    public WateringScheduleModel(UUID id, String watering_time, String frequency, PlantModel plant) {
        this.id = id;
        this.watering_time = watering_time;
        this.frequency = frequency;
        this.plant = plant;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getWatering_time() {
        return watering_time;
    }

    public void setWatering_time(String watering_time) {
        this.watering_time = watering_time;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public PlantModel getPlant() {
        return plant;
    }

    public void setPlant(PlantModel plant) {
        this.plant = plant;
    }
}
