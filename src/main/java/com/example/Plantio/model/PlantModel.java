package com.example.Plantio.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "plants")
public class PlantModel {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private PlantSpecies species;
    private String date_of_planting;
    private ConditionEnum condition;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private LocationModel location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToMany(mappedBy = "plant")
    private List<PlantCareModel> plantCares;

    @OneToMany(mappedBy = "plant")
    private List<EnvironmentalConditionsModel> environmentalConditions;

    @OneToMany(mappedBy = "plant")
    private List<WateringScheduleModel> wateringSchedules;

    @OneToMany(mappedBy = "plant")
    private List<FertilizerApplicationModel> fertilizerApplications;

    public PlantModel(){

    }

    public PlantModel(String name, PlantSpecies species, String date_of_planting, ConditionEnum condition, LocationModel location, UserModel user, List<PlantCareModel> plantCares, List<EnvironmentalConditionsModel> environmentalConditions, List<WateringScheduleModel> wateringSchedules, List<FertilizerApplicationModel> fertilizerApplications) {
        this.name = name;
        this.species = species;
        this.date_of_planting = date_of_planting;
        this.condition = condition;
        this.location = location;
        this.user = user;
        this.plantCares = plantCares;
        this.environmentalConditions = environmentalConditions;
        this.wateringSchedules = wateringSchedules;
        this.fertilizerApplications = fertilizerApplications;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlantSpecies getSpecies() {
        return species;
    }

    public void setSpecies(PlantSpecies species) {
        this.species = species;
    }

    public String getDate_of_planting() {
        return date_of_planting;
    }

    public void setDate_of_planting(String date_of_planting) {
        this.date_of_planting = date_of_planting;
    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionEnum condition) {
        this.condition = condition;
    }

    public LocationModel getLocation() {
        return location;
    }

    public void setLocation(LocationModel location) {
        this.location = location;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<PlantCareModel> getPlantCares() {
        return plantCares;
    }

    public void setPlantCares(List<PlantCareModel> plantCares) {
        this.plantCares = plantCares;
    }

    public List<EnvironmentalConditionsModel> getEnvironmentalConditions() {
        return environmentalConditions;
    }

    public void setEnvironmentalConditions(List<EnvironmentalConditionsModel> environmentalConditions) {
        this.environmentalConditions = environmentalConditions;
    }

    public List<WateringScheduleModel> getWateringSchedules() {
        return wateringSchedules;
    }

    public void setWateringSchedules(List<WateringScheduleModel> wateringSchedules) {
        this.wateringSchedules = wateringSchedules;
    }

    public List<FertilizerApplicationModel> getFertilizerApplications() {
        return fertilizerApplications;
    }

    public void setFertilizerApplications(List<FertilizerApplicationModel> fertilizerApplications) {
        this.fertilizerApplications = fertilizerApplications;
    }
}
