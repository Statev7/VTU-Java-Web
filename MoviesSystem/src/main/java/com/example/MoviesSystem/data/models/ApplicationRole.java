package com.example.MoviesSystem.data.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "roles")
public class ApplicationRole extends BaseModel{
    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<ApplicationUser> users = new HashSet<>();
}
