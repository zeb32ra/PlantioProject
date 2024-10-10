package com.example.Plantio.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "fertilizer_applications")
public class FertilizerApplicationModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String date;
    private String amount;

    @ManyToOne
    @JoinColumn(name = "plant_id")
    private PlantModel plant;

    @ManyToOne
    @JoinColumn(name = "fertilizer_id")
    private FertilizerModel fertilizer;

    public FertilizerApplicationModel() {

    }

    public FertilizerApplicationModel(UUID id, String date, String amount, PlantModel plant, FertilizerModel fertilizer) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.plant = plant;
        this.fertilizer = fertilizer;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public PlantModel getPlant() {
        return plant;
    }

    public void setPlant(PlantModel plant) {
        this.plant = plant;
    }

    public FertilizerModel getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(FertilizerModel fertilizer) {
        this.fertilizer = fertilizer;
    }
}
