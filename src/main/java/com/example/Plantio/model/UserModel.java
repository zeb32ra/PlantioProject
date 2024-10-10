package com.example.Plantio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserModel {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;

    @Size(min = 3, message = "Username must be at least 3 characters")
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d).+$")
    private String password;

    private boolean active;

    @ElementCollection(targetClass = RoleEnum.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<RoleEnum> roles;

    @OneToMany(mappedBy = "user")
    private List<LocationModel> locations;

    @OneToMany(mappedBy = "user")
    private List<PlantModel> plants;


    public UserModel() {

    }

    public UserModel(String username, String password, boolean active, Set<RoleEnum> roles, List<LocationModel> locations, List<PlantModel> plants) {
        this.username = username;
        this.password = password;
        this.active = active;
        this.roles = roles;
        this.locations = locations;
        this.plants = plants;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public @Size(min = 3, message = "Username must be at least 3 characters") @NotBlank(message = "Password cannot be empty") @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d).+$") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 3, message = "Username must be at least 3 characters") @NotBlank(message = "Password cannot be empty") @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d).+$") String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RoleEnum> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEnum> roles) {
        this.roles = roles;
    }

    public List<LocationModel> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationModel> locations) {
        this.locations = locations;
    }

    public List<PlantModel> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantModel> plants) {
        this.plants = plants;
    }
}
