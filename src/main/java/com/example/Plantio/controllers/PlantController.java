package com.example.Plantio.controllers;

import com.example.Plantio.model.*;
import com.example.Plantio.repository.UserRepository;
import com.example.Plantio.service.LocationService;
import com.example.Plantio.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/plants")
public class PlantController {
    @Autowired
    private PlantService plantService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/all")
    public String getAllPlants(Model model) {
        model.addAttribute("plants", plantService.getAllPlants());
        model.addAttribute("plant", new PlantModel());
        model.addAttribute("speciesList", PlantSpecies.values());
        model.addAttribute("conditionsList", ConditionEnum.values());
        model.addAttribute("locationList", locationService.getAllLocations());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User userPrincipal) {
            String username = userPrincipal.getUsername();
            UserModel user = userRepository.findByUsername(username);
            model.addAttribute("user", user);
        }
        return "plants";
    }

    @PostMapping("/add")
    public String addPlant(@ModelAttribute("plant") PlantModel plantModel, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("plants", plantService.getAllPlants());
            model.addAttribute("plant", new PlantModel());
            model.addAttribute("speciesList", PlantSpecies.values());
            model.addAttribute("conditionsList", ConditionEnum.values());
            model.addAttribute("locationList", locationService.getAllLocations());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof User userPrincipal) {
                String username = userPrincipal.getUsername();
                UserModel user = userRepository.findByUsername(username);
                model.addAttribute("user", user);
            }
            return "plants";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User userPrincipal) {
            String username = userPrincipal.getUsername();
            UserModel user = userRepository.findByUsername(username);
            plantModel.setUser(user);
        }
        plantService.addPlant(plantModel);
        return "redirect:/plants/all";
    }

    @PostMapping("/update")
    public String updatePlant(@Valid @ModelAttribute("plant") PlantModel plantModel, BindingResult result, Model model){
        if(result.hasErrors()) {
            model.addAttribute("plants", plantService.getAllPlants());
            model.addAttribute("plant", new PlantModel());
            model.addAttribute("speciesList", PlantSpecies.values());
            model.addAttribute("conditionsList", ConditionEnum.values());
            model.addAttribute("locationList", locationService.getAllLocations());
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication.getPrincipal() instanceof User userPrincipal) {
                String username = userPrincipal.getUsername();
                UserModel user = userRepository.findByUsername(username);
                model.addAttribute("user", user);
            }
            return "plants";
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User userPrincipal) {
            String username = userPrincipal.getUsername();
            UserModel user = userRepository.findByUsername(username);
            plantModel.setUser(user);
        }
        plantService.updatePlant(plantModel);
        return "redirect:/plants/all";
    }

    @PostMapping("/delete")
    public String deletePlant(@RequestParam UUID id) {
        plantService.deletePlant(id);
        return "redirect:/plants/all";
    }

    @GetMapping("/all/{id}")
    public String getIdPlant(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("plant", plantService.getPlantById(id));
        model.addAttribute("plantCare", new PlantCareModel());
        model.addAttribute("environmentalCondition", new EnvironmentalConditionsModel());
        model.addAttribute("wateringSchedule", new WateringScheduleModel());
        model.addAttribute("actionsList", ActionsEnum.values());
        return "plantsActions";
    }
}
