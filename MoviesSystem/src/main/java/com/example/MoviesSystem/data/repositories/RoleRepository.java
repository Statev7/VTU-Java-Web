package com.example.MoviesSystem.data.repositories;

import com.example.MoviesSystem.data.models.ApplicationRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<ApplicationRole, Long> {
    ApplicationRole findByName(String name);
}
