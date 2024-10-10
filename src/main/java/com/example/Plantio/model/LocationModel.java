package com.example.Plantio.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "locations")
public class LocationModel {
    @Id
    @GeneratedValue
    private UUID id;

    private LocationEnum locationType;

    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel user;

    @OneToMany(mappedBy = "location")
    private List<PlantModel> plants;

    public LocationModel(){

    }

    public LocationModel(UUID id, LocationEnum locationType, String description, UserModel user, List<PlantModel> plants) {
        this.id = id;
        this.locationType = locationType;
        this.description = description;
        this.user = user;
        this.plants = plants;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocationEnum getLocationType() {
        return locationType;
    }

    public void setLocationType(LocationEnum locationType) {
        this.locationType = locationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<PlantModel> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantModel> plants) {
        this.plants = plants;
    }
}
