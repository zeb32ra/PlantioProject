package com.example.Plantio.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "environmental_conditions")
public class EnvironmentalConditionsModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String humidity;
    private String temperature;
    private String light_intensity;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private PlantModel plant;

    public EnvironmentalConditionsModel() {

    }

    public EnvironmentalConditionsModel(UUID id, String humidity, String temperature, String light_intensity, PlantModel plant) {
        this.id = id;
        this.humidity = humidity;
        this.temperature = temperature;
        this.light_intensity = light_intensity;
        this.plant = plant;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getLight_intensity() {
        return light_intensity;
    }

    public void setLight_intensity(String light_intensity) {
        this.light_intensity = light_intensity;
    }

    public PlantModel getPlant() {
        return plant;
    }

    public void setPlant(PlantModel plant) {
        this.plant = plant;
    }
}
