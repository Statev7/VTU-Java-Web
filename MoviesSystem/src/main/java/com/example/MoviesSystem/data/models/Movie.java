package com.example.MoviesSystem.data.models;

import com.example.MoviesSystem.data.models.BaseModel;
import com.example.MoviesSystem.data.models.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Movies")
public class Movie extends BaseModel {
    private String name;
    private String description;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;
}
