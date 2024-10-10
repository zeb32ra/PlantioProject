package com.example.Plantio.controllers;


import com.example.Plantio.model.LocationEnum;
import com.example.Plantio.model.LocationModel;
import com.example.Plantio.model.RoleEnum;
import com.example.Plantio.model.UserModel;
import com.example.Plantio.repository.LocationRepository;
import com.example.Plantio.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    LocationRepository locationRepository;

    @GetMapping("/registration")
    public String registrationView(Model model) {
        try{
            var userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            if(userDetails != null) {
                if(userDetails.getAuthorities().contains(RoleEnum.ADMIN)) {
                    model.addAttribute("isAdmin", true);
                }
                else{
                    model.addAttribute("isAdmin", false);
                }
            }
        }catch (Exception e){
            model.addAttribute("roles", RoleEnum.values());
            model.addAttribute("isAdmin", false);
            return "regis";
        }

        model.addAttribute("roles", RoleEnum.values());
        return "regis";
    }

    @PostMapping("/registration")
    public String registrationUser(@Valid UserModel user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("message", "Password should be at least 3 symbols and contain a-z and 0-9");
            model.addAttribute("roles", RoleEnum.values());
            return "regis";
        }
        if (userRepository.existsByUsername(user.getUsername())){
            model.addAttribute("message", "Пользователь уже существует");
            model.addAttribute("roles", RoleEnum.values());
            return "regis";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(RoleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        List<LocationModel> locations = new ArrayList<>();
        for (LocationEnum location : LocationEnum.values()) {
            LocationModel locationModel = new LocationModel();
            locationModel.setLocationType(location);
            locations.add(locationModel);
        }
        user.setLocations(locations);
        userRepository.save(user);

        for (LocationModel locationModel : locations) {
            locationModel.setUser(user);
            locationRepository.save(locationModel);
        }
        return "redirect:/login";
    }

    @PostMapping("/registrationByAdmin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String registrationAdminOrManager(@Valid UserModel user, BindingResult bindingResult, Model model, @RequestParam String role) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("message", "Password should be at least 3 symbols and contain a-z and 0-9");
            model.addAttribute("roles", RoleEnum.values());
            model.addAttribute("isAdmin", true);
            return "regis";
        }
        if (userRepository.existsByUsername(user.getUsername())){
            model.addAttribute("message", "Пользователь уже существует");
            model.addAttribute("roles", RoleEnum.values());
            model.addAttribute("isAdmin", true);
            return "regis";
        }
        user.setActive(true);
        if (role.equals("ADMIN")){
            user.setRoles(Collections.singleton(RoleEnum.ADMIN));
        } else if (role.equals("MANAGER")) {
            user.setRoles(Collections.singleton(RoleEnum.MANAGER));
        }
        else{
            user.setRoles(Collections.singleton(RoleEnum.USER));
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/login";
    }

}

