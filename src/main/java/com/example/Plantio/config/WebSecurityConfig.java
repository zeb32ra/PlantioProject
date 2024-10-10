package com.example.Plantio.config;


import com.example.Plantio.model.LocationEnum;
import com.example.Plantio.model.LocationModel;
import com.example.Plantio.model.RoleEnum;
import com.example.Plantio.model.UserModel;
import com.example.Plantio.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createDefaultUser() {
        if(!userRepository.existsByUsername("admin")) {
            UserModel user = new UserModel();
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("Admin"));
            user.setActive(true);
            user.setRoles(Collections.singleton(RoleEnum.ADMIN));
            user.setLocations(Collections.emptyList());
            user.setPlants(Collections.emptyList());
            userRepository.save(user);
        }
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
                    UserModel user = userRepository.findByUsername(username);
                    if (user == null) {
                        throw new UsernameNotFoundException("Такой пользователь не существует!");
                    }
                    return new org.springframework.security.core.userdetails.User(
                            user.getUsername(),
                            user.getPassword(),
                            user.isActive(),
                            true,
                            true,
                            true,
                            user.getRoles()
                    );
                }
        ).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/login", "/registration").permitAll()
                                .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/").permitAll())
                .logout(logout -> logout.permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());

        return http.build();

    }


}
