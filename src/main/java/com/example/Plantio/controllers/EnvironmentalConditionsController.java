package com.example.Plantio.controllers;

import com.example.Plantio.model.EnvironmentalConditionsModel;
import com.example.Plantio.service.EnvironmentalConditionsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/environmentalConditions")
public class EnvironmentalConditionsController {
    @Autowired
    private EnvironmentalConditionsService environmentalConditionsService;

    @GetMapping("/all")
    public String getAllEnvironmentalConditions(Model model) {
        model.addAttribute("environmentalConditions", environmentalConditionsService.getAllEnvironmentalConditions());
        return "environmentalConditions";
    }

    @PostMapping("/add")
    public String addEnvironmentalCondition(@Valid @ModelAttribute("environmentalCondition") EnvironmentalConditionsModel environmentalConditionsModel, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("environmentalConditions", environmentalConditionsService.getAllEnvironmentalConditions());
            return "environmentalConditions";
        }
        environmentalConditionsService.addEnvironmentalCondition(environmentalConditionsModel);
        return "redirect:/environmentalConditions/all";
    }

    @PostMapping("/update")
    public String updateEnvironmentalCondition(@Valid @ModelAttribute("environmentalCondition") EnvironmentalConditionsModel environmentalConditionsModel, BindingResult result, Model model){
        environmentalConditionsService.updateEnvironmentalCondition(environmentalConditionsModel);
        return "redirect:/environmentalConditions/all";
    }

    @PostMapping("/delete")
    public String deleteEnvironmentalCondition(@RequestParam UUID id) {
        environmentalConditionsService.deleteEnvironmentalCondition(id);
        return "redirect:/environmentalConditions/all";
    }

    @GetMapping("/all/{id}")
    public String getIdEnvironmentalCondition(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("environmentalConditions", environmentalConditionsService.getEnvironmentalConditionById(id));
        return "environmentalConditions";
    }

}