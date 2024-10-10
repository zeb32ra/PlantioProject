package com.example.Plantio.repository;


import com.example.Plantio.model.UserModel;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserRepository extends CrudRepository<UserModel, UUID> {
    UserModel findByUsername(String username);
    boolean existsByUsername(String username);
}
