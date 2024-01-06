package com.example.MoviesSystem.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Genres")
public class Genre extends BaseModel {

    public Genre(String name)
    {
        this.name = name;
    }

    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.REMOVE)
    private Set<Movie> movies = new HashSet<>();
}
