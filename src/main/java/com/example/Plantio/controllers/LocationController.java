package com.example.Plantio.controllers;

import com.example.Plantio.model.LocationEnum;
import com.example.Plantio.model.LocationModel;
import com.example.Plantio.model.UserModel;
import com.example.Plantio.service.LocationService;
import com.example.Plantio.service.LocationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @GetMapping("/all")
    public String getAllLocations(Model model) {
        model.addAttribute("locations", LocationEnum.values());
        return "locations";
    }

    @PostMapping("/add")
    public String addLocation(@Valid @ModelAttribute("location") LocationModel fertilizerModel, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("locations", locationService.getAllLocations());
            return "locations";
        }
        locationService.addLocation(fertilizerModel);
        return "redirect:/locations/all";
    }

    @PostMapping("/update")
    public String updateLocation(@Valid @ModelAttribute("location") LocationModel fertilizerModel, BindingResult result, Model model){
        locationService.updateLocation(fertilizerModel);
        return "redirect:/locations/all";
    }

    @PostMapping("/delete")
    public String deleteLocation(@RequestParam UUID id) {
        locationService.deleteLocation(id);
        return "redirect:/locations/all";
    }

    @GetMapping("/all/{id}")
    public String getIdLocation(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("locations", locationService.getLocationById(id));
        return "locations";
    }

    @GetMapping("/{name}")
    public String getNameLocation(@PathVariable("name") String name, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("Authenticated: " + authentication.isAuthenticated());
            System.out.println("Principal: " + authentication.getPrincipal());
            if (authentication.getPrincipal() instanceof User userPrincipal) {
                String username = userPrincipal.getUsername();
                model.addAttribute("location", locationService.getLocationByName(name, username));
            } else {
                System.out.println("Principal is not of type UserModel.");
            }
        } else {
            System.out.println("No authentication found.");
        }

        model.addAttribute("name", name);
        return "locationDetails";
    }

}
