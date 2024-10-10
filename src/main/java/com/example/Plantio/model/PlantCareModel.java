package com.example.Plantio.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "plant_care")
public class PlantCareModel {
    @Id
    @GeneratedValue
    private UUID id;
    private ActionsEnum action;
    private String frequency;
    private String last_action_date;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private PlantModel plant;

    public PlantCareModel() {

    }

    public PlantCareModel(UUID id, ActionsEnum action, String frequency, String last_action_date, PlantModel plant) {
        this.id = id;
        this.action = action;
        this.frequency = frequency;
        this.last_action_date = last_action_date;
        this.plant = plant;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ActionsEnum getAction() {
        return action;
    }

    public void setAction(ActionsEnum action) {
        this.action = action;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getLast_action_date() {
        return last_action_date;
    }

    public void setLast_action_date(String last_action_date) {
        this.last_action_date = last_action_date;
    }

    public PlantModel getPlant() {
        return plant;
    }

    public void setPlant(PlantModel plant) {
        this.plant = plant;
    }
}

