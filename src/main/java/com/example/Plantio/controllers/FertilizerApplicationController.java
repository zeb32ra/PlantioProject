package com.example.Plantio.controllers;

import com.example.Plantio.model.FertilizerApplicationModel;
import com.example.Plantio.service.FertilizerApplicationService;
import com.example.Plantio.service.PlantService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/fertilizerApplications")
public class FertilizerApplicationController {
    @Autowired
    private FertilizerApplicationService fertilizerApplicationService;

    @Autowired
    private PlantService plantService;

    @GetMapping("/all")
    public String getAllFertilizerApplications(Model model) {
        model.addAttribute("fertilizerApplications", fertilizerApplicationService.getAllFertilizerApplications());
        return "fertilizerApplications";
    }

    @PostMapping("/add/{id}")
    public String addFertilizerApplication(@Valid @ModelAttribute("fertilizerApplication") FertilizerApplicationModel fertilizerModel, @PathVariable("id") UUID id, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("fertilizerApplications", fertilizerApplicationService.getAllFertilizerApplications());
            return "plantsActions";
        }
        fertilizerModel.setPlant(plantService.getPlantById(id));
        fertilizerApplicationService.addFertilizerApplication(fertilizerModel);
        return "redirect:/plants/all/{id}";
    }

    @PostMapping("/update/{id}")
    public String updateFertilizerApplication(@Valid @ModelAttribute("fertilizerApplication") FertilizerApplicationModel fertilizerModel, BindingResult result, Model model){
        fertilizerApplicationService.updateFertilizerApplication(fertilizerModel);
        return "redirect:/fertilizerApplications/all";
    }

    @PostMapping("/delete/{id}")
    public String deleteFertilizerApplication(@RequestParam UUID id, @PathVariable("id") UUID plant_id, Model model) {
        fertilizerApplicationService.deleteFertilizerApplication(id);
        return "plantCares";
    }

    @GetMapping("/all/{id}")
    public String getIdFertilizerApplication(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("fertilizerApplications", fertilizerApplicationService.getFertilizerApplicationById(id));
        return "fertilizerApplications";
    }
}
