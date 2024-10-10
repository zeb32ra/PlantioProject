package com.example.Plantio.controllers;

import com.example.Plantio.model.PlantCareModel;
import com.example.Plantio.service.PlantCareService;
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

    @GetMapping("/all")
    public String getAllPlantCares(Model model) {
        model.addAttribute("plantCares", plantCareService.getAllPlantCares());
        return "plantCares";
    }

    @PostMapping("/add")
    public String addPlantCare(@Valid @ModelAttribute("plantCare") PlantCareModel fertilizerModel, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("plantCares", plantCareService.getAllPlantCares());
            return "plantCares";
        }
        plantCareService.addPlantCare(fertilizerModel);
        return "redirect:/plantCares/all";
    }

    @PostMapping("/update")
    public String updatePlantCare(@Valid @ModelAttribute("plantCare") PlantCareModel fertilizerModel, BindingResult result, Model model){
        plantCareService.updatePlantCare(fertilizerModel);
        return "redirect:/plantCares/all";
    }

    @PostMapping("/delete")
    public String deletePlantCare(@RequestParam UUID id) {
        plantCareService.deletePlantCare(id);
        return "redirect:/plantCares/all";
    }

    @GetMapping("/all/{id}")
    public String getIdPlantCare(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("plantCares", plantCareService.getPlantCareById(id));
        return "plantCares";
    }
}
