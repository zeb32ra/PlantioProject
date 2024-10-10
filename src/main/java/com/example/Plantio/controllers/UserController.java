package com.example.Plantio.controllers;

import com.example.Plantio.model.RoleEnum;
import com.example.Plantio.model.UserModel;
import com.example.Plantio.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String userView(Model model) {
        model.addAttribute("user_list", userRepository.findAll());
        return "indexUser";
    }

    @GetMapping("/users/{id}")
    public String detailView(@PathVariable UUID id, Model model) {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Пользователя не существует:" + id));
        model.addAttribute("user_object", user);
        return "info";
    }

    @GetMapping("/users/{id}/update")
    public String updateView(@PathVariable UUID id, Model model) {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Пользователя не существует:" + id));
        model.addAttribute("user_object", user);
        model.addAttribute("roles", RoleEnum.values());
        return "update";
    }

    @PostMapping("/users/{id}/update")
    public String updateUser(@PathVariable UUID id,
                             @RequestParam String username,
                             @RequestParam(name = "roles[]", required = false) String[] roles) {
        UserModel user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Пользователя не существует:" + id));
        user.setUsername(username);
        user.getRoles().clear();
        if (roles != null) {
            for (String role : roles) {
                user.getRoles().add(RoleEnum.valueOf(role));
            }
        }
        userRepository.save(user);
        return "redirect:/admin/users/" + id;
    }

}
