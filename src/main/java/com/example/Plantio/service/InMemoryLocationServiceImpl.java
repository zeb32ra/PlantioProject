package com.example.Plantio.service;

import com.example.Plantio.model.LocationModel;
import com.example.Plantio.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InMemoryLocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;

    public InMemoryLocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<LocationModel> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public LocationModel getLocationById(UUID id) {
        return locationRepository.findById(id).orElse(null);
    }

    @Override
    public LocationModel addLocation(LocationModel location) {
        return locationRepository.save(location);
    }

    @Override
    public LocationModel updateLocation(LocationModel location) {
        if(locationRepository.existsById(location.getId())) {
            return locationRepository.save(location);
        }
        return null;
    }

    @Override
    public void deleteLocation(UUID id) {
        if(locationRepository.existsById(id)) {
            locationRepository.deleteById(id);
        }
    }

    @Override
    public LocationModel getLocationByName(String name, String username) {
        for (LocationModel location : locationRepository.findAll()) {
            if(location.getLocationType().name().equals(name) && location.getUser().getUsername().equals(username)) {
                return location;
            }
        }
        return null;
    }
}
