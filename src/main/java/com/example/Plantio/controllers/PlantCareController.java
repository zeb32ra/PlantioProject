package com.example.Plantio.controllers;

import com.example.Plantio.model.ActionsEnum;
import com.example.Plantio.model.PlantCareModel;
import com.example.Plantio.model.PlantModel;
import com.example.Plantio.service.PlantCareService;
import com.example.Plantio.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/plantCares")
public class PlantCareController {
    @Autowired
    private PlantCareService plantCareService;

    @Autowired
    private PlantService plantService;

    @GetMapping("/all")
    public String getAllPlantCares(Model model) {
        model.addAttribute("plantCares", plantCareService.getAllPlantCares());
        return "plantCares";
    }

    @PostMapping("/add/{id}")
    public String addPlantCare(@Valid @ModelAttribute("plantCare") PlantCareModel plantCareModel, BindingResult result, @PathVariable("id") UUID id, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("plantCares", plantCareService.getAllPlantCares());
            return "plantCares";
        }
        plantCareModel.setPlant(plantService.getPlantById(id));
        plantCareService.addPlantCare(plantCareModel);
        return "redirect:/plants/all/{id}";
    }

    @PostMapping("/update/{id}")
    public String updatePlantCare(@Valid @ModelAttribute("plantCare") PlantCareModel plantCareModel, BindingResult result, Model model){
        plantCareService.updatePlantCare(plantCareModel);
        return "redirect:/plantCares/all"; //change
    }

    @PostMapping("/delete/{id}")
    public String deletePlantCare(@RequestParam UUID id, @PathVariable("id") UUID plant_id) {
        plantCareService.deletePlantCare(id);
        return "redirect:/plants/all/{id}";
    }

    @GetMapping("/all/{id}")
    public String getIdPlantCare(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("plantCares", plantCareService.getPlantCareById(id));
        return "plantCares";
    }
}
