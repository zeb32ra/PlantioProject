package com.example.Plantio.service;

import com.example.Plantio.model.LocationModel;

import java.util.List;
import java.util.UUID;

public interface LocationService {
    public List<LocationModel> getAllLocations();
    public LocationModel getLocationById(UUID id);
    public LocationModel addLocation(LocationModel location);
    public LocationModel updateLocation(LocationModel location);
    public void deleteLocation(UUID id);
    LocationModel getLocationByName(String name, String username);
}
