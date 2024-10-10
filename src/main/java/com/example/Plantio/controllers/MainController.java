package com.example.Plantio.controllers;

import com.example.Plantio.model.RoleEnum;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model) {
        try{
            var userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            if(userDetails != null) {
                if(userDetails.getAuthorities().contains(RoleEnum.ADMIN)) {
                    model.addAttribute("isAdmin", true);
                }
                else if(userDetails.getAuthorities().contains(RoleEnum.MANAGER)) {
                    model.addAttribute("isManager", true);
                }
                else{
                    model.addAttribute("isUser", true);
                }
            }
        }catch (Exception e){
            model.addAttribute("roles", RoleEnum.values());
            model.addAttribute("isAdmin", false);
            model.addAttribute("isManager", false);
            model.addAttribute("isUser", false);
            return "home";
        }
        return "home";
    }
}
