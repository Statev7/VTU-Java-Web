package com.example.MoviesSystem.data.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity(name = "roles")
public class ApplicationRole extends BaseModel{

    public ApplicationRole(String name){
        this.name = name;
    }

    private String name;

    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    private Set<ApplicationUser> users = new HashSet<>();
}
