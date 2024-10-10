package com.example.Plantio.controllers;

import com.example.Plantio.model.WateringScheduleModel;
import com.example.Plantio.service.WateringScheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/wateringSchedules")
public class WateringScheduleController {
    @Autowired
    private WateringScheduleService wateringScheduleService;

    @GetMapping("/all")
    public String getAllWateringSchedules(Model model) {
        model.addAttribute("wateringSchedules", wateringScheduleService.getAllWateringSchedules());
        return "wateringSchedules";
    }

    @PostMapping("/add")
    public String addWateringSchedule(@Valid @ModelAttribute("wateringSchedule") WateringScheduleModel fertilizerModel, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("wateringSchedules", wateringScheduleService.getAllWateringSchedules());
            return "wateringSchedules";
        }
        wateringScheduleService.addWateringSchedule(fertilizerModel);
        return "redirect:/wateringSchedules/all";
    }

    @PostMapping("/update")
    public String updateWateringSchedule(@Valid @ModelAttribute("wateringSchedule") WateringScheduleModel fertilizerModel, BindingResult result, Model model){
        wateringScheduleService.updateWateringSchedule(fertilizerModel);
        return "redirect:/wateringSchedules/all";
    }

    @PostMapping("/delete")
    public String deleteWateringSchedule(@RequestParam UUID id) {
        wateringScheduleService.deleteWateringSchedule(id);
        return "redirect:/wateringSchedules/all";
    }

    @GetMapping("/all/{id}")
    public String getIdWateringSchedule(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("wateringSchedules", wateringScheduleService.getWateringScheduleById(id));
        return "wateringSchedules";
    }
}
