package com.example.Plantio.controllers;

import com.example.Plantio.model.FertilizerApplicationModel;
import com.example.Plantio.service.FertilizerApplicationService;
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

    @GetMapping("/all")
    public String getAllFertilizerApplications(Model model) {
        model.addAttribute("fertilizerApplications", fertilizerApplicationService.getAllFertilizerApplications());
        return "fertilizerApplications";
    }

    @PostMapping("/add")
    public String addFertilizerApplication(@Valid @ModelAttribute("fertilizerApplication") FertilizerApplicationModel fertilizerModel, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("fertilizerApplications", fertilizerApplicationService.getAllFertilizerApplications());
            return "fertilizerApplications";
        }
        fertilizerApplicationService.addFertilizerApplication(fertilizerModel);
        return "redirect:/fertilizerApplications/all";
    }

    @PostMapping("/update")
    public String updateFertilizerApplication(@Valid @ModelAttribute("fertilizerApplication") FertilizerApplicationModel fertilizerModel, BindingResult result, Model model){
        fertilizerApplicationService.updateFertilizerApplication(fertilizerModel);
        return "redirect:/fertilizerApplications/all";
    }

    @PostMapping("/delete")
    public String deleteFertilizerApplication(@RequestParam UUID id) {
        fertilizerApplicationService.deleteFertilizerApplication(id);
        return "redirect:/fertilizerApplications/all";
    }

    @GetMapping("/all/{id}")
    public String getIdFertilizerApplication(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("fertilizerApplications", fertilizerApplicationService.getFertilizerApplicationById(id));
        return "fertilizerApplications";
    }
}
