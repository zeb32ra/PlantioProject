package com.example.Plantio.controllers;

import com.example.Plantio.model.FertilizerModel;
import com.example.Plantio.model.PlantModel;
import com.example.Plantio.service.FertilizerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/fertilizers")
public class FertilizerController {
    @Autowired
    private FertilizerService fertilizerService;


    @GetMapping("/all")
    public String getAllFertilizers(Model model) {
        model.addAttribute("fertilizers", fertilizerService.getAllFertilizers());
        return "fertilizers";
    }

    @PostMapping("/add")
    public String addFertilizer(@Valid @ModelAttribute("fertilizer") FertilizerModel fertilizerModel, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("fertilizers", fertilizerService.getAllFertilizers());
            return "fertilizers";
        }
        fertilizerService.addFertilizer(fertilizerModel);
        return "redirect:/fertilizers/all";
    }

    @PostMapping("/update/{id}")
    public String updateFertilizer(@Valid @ModelAttribute("fertilizer") FertilizerModel fertilizerModel, BindingResult result, Model model){
        fertilizerService.updateFertilizer(fertilizerModel);
        return "redirect:/fertilizers/all";
    }

    @PostMapping("/delete")
    public String deleteFertilizer(@RequestParam UUID id) {
        fertilizerService.deleteFertilizer(id);
        return "redirect:/fertilizers/all";
    }

    @GetMapping("/all/{id}")
    public String getIdFertilizer(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("fertilizers", fertilizerService.getFertilizerById(id));
        return "fertilizers";
    }
}
